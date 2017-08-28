package trng.imcs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import trng.imcs.exception.*;
import trng.imcs.jdbc.JdbcConnectionFactory;
import trng.imcs.model.*;
import trng.imcs.dao.*;
import trng.imcs.exception.*;

public class EmployeeDAOImplementations implements EmployeeDAO {
	public void add(List<Employee> empList) {

		final String sql = "insert into employee (empNo, depNo, doj, dob, salary, salaryGrade) values (?, ?, ?, ?, ?, ?)";
		try (Connection con = JdbcConnectionFactory.getConnection();
				Statement st = con.createStatement();
				PreparedStatement preparedStatement = con.prepareStatement(sql)) {

			con.setAutoCommit(false);

			st.execute("truncate employee");
			int count = 1;
			boolean hasLeftOverBatchRecords = true;
			for (Employee e : empList) {

				if (e == null)
					break;

				preparedStatement.setInt(1, e.getEmpNo());
				preparedStatement.setInt(2, e.getDepNo());
				java.util.Date d = e.getDoj();
				java.sql.Date doj = new java.sql.Date(d.getYear(), d.getMonth(), d.getDate());
				preparedStatement.setDate(3, doj);
				d = e.getDoj();
				java.sql.Date dob = new java.sql.Date(d.getYear(), d.getMonth(), d.getDate());
				preparedStatement.setDate(4, dob);
				preparedStatement.setFloat(5, e.getSalary());
				preparedStatement.setInt(6, e.getSalaryGrade());

				preparedStatement.addBatch();

				if (count++ % 4 == 0) {
					int[] updateCount = preparedStatement.executeBatch();
					hasLeftOverBatchRecords = false;
				} else {
					hasLeftOverBatchRecords = true;
				}
			}

			if (hasLeftOverBatchRecords) {
				int[] updateCount = preparedStatement.executeBatch();
			}

			con.commit();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public List<Employee> getAll(int deptId, String orderBy) {
		List<Employee> empList = new ArrayList<>();

		ResultSet rs = null;
		String sql = "select empNo, depNo, doj, dob, salary, salaryGrade from employee where depNo=? order by "
				+ orderBy;
		try (Connection con = JdbcConnectionFactory.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {

			st.setInt(1, deptId);
			rs = st.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setEmpNo(rs.getInt(1));
				e.setDepNo(rs.getInt(2));
				e.setDoj(rs.getDate(3));
				e.setDob(rs.getDate(4));
				e.setSalary(rs.getFloat(5));
				e.setSalaryGrade(rs.getInt(6));
				empList.add(e);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return empList;
	}

	@Override
	public List<Employee> getAll() {
		List<Employee> empList = new ArrayList<Employee>();
		ResultSet rs = null;
		String sql = "select empNo, depNo, doj, dob, salary, salaryGrade  from employee";
		try (Connection con = JdbcConnectionFactory.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			rs = st.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setEmpNo(rs.getInt(1));
				e.setDepNo(rs.getInt(2));
				e.setDoj(rs.getDate(3));
				e.setDob(rs.getDate(4));
				e.setSalary(rs.getFloat(5));
				e.setSalaryGrade(rs.getInt(6));
				empList.add(e);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return empList;
	}

	@Override
	public List<Bonus> getAllEmployeeBonus() throws SQLException {
		List<Bonus> bonusList = new ArrayList<Bonus>();
		ResultSet rs = null;
		String sql = "select * from bonus";
		try (Connection con = JdbcConnectionFactory.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			rs = st.executeQuery();
			while (rs.next()) {
				Bonus e = new Bonus();
				e.setDepNo(rs.getInt(1));
				e.setAmount(rs.getFloat(2));
				e.setRemainingAmount(rs.getFloat(3));
				bonusList.add(e);
			}
		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		return bonusList;
	}

	public boolean addEmployeeBonus(final EmployeeBonus e) {
		List<Employee> empList = getAll();

		final String sql = "insert into employeebonus (empNo, status, amount, dateAllocated) values (?, ?, ?, ?)";
		try (Connection con = JdbcConnectionFactory.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(sql)) {
			preparedStatement.setInt(1, e.getEmpNo());
			preparedStatement.setString(2, e.getStatus());
			preparedStatement.setFloat(3, e.getAmount());
			preparedStatement.setDate(4, e.getDateAllocated());
			final int status = preparedStatement.executeUpdate();
			if (status != 1) {
				con.rollback();
				return false;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return true;

	}

	public Bonus getBonusInfo(int depNo) {
		ResultSet rs = null;
		Bonus b = new Bonus();
		float remainingAmountInDept = 0;
		String sql = "select amount, remainingAmount from bonus where depNo=?";
		try (Connection con = JdbcConnectionFactory.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {

			st.setInt(1, depNo);
			rs = st.executeQuery();
			if (rs.next()) {
				b.setDepNo(depNo);
				b.setAmount(rs.getFloat(1));
				b.setRemainingAmount(rs.getFloat(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean updateBonusInfo(int depNo, Bonus b) {
		final String sql = "update bonus set remainingAmount=? where depNo =?";
		try (Connection con = JdbcConnectionFactory.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(2, depNo);
			st.setFloat(1, b.getRemainingAmount());

			final int status = st.executeUpdate();
			con.close();
			if (status != 1) {
				con.rollback();
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public Employee getEmployeeById(int employeeId) throws SQLException {
		Employee employee = null;
		ResultSet rs = null;
		String sql = "select *  from employee where empNo=?";
		try (Connection con = JdbcConnectionFactory.getConnection();PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, employeeId);
			rs = st.executeQuery();
			while (rs.next()) {
				employee = new Employee(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4), rs.getFloat(5), rs.getInt(6));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Employee> getEmployeeByDepId(int depId) throws SQLException {
		List<Employee> empList = new ArrayList<Employee>();
		ResultSet rs = null;
		String sql = "select *  from employee where depNo=?";
		try (Connection con = JdbcConnectionFactory.getConnection();PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, depId);
			rs = st.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setEmpNo(rs.getInt(1));
				e.setDepNo(rs.getInt(2));
				e.setDoj(rs.getDate(3));
				e.setDob(rs.getDate(4));
				e.setSalary(rs.getFloat(5));
				e.setSalaryGrade(rs.getInt(6));
				empList.add(e);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return empList;
	}

}
