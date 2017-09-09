package trng.imcs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import trng.imcs.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	DataSource dataSource;

	@Override
	public Employee getEmpByEmpId(int id) {
		Employee employee = null;
		String selectQuery = "select * from employee where empId=?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = createPreparedStatement(connection, selectQuery, id); 
				ResultSet rs = preparedStatement.executeQuery()) {
			while (rs.next()) {
				employee = new Employee();
				employee.setDepId(rs.getInt(1));
				employee.setEmpPwd(rs.getString(2));
				employee.setEmpName(rs.getString(3));
				employee.setEmpDob(rs.getDate(4));
				employee.setDepId(rs.getInt(5));
			}
		} catch (SQLException e) {
			System.out.println("An unexpected sql exception occured, while geting an employee.");
		}
		
		return employee;
	}

	@Override
	public List<Employee> getAllEmpByDepId(int id) {
		List<Employee> employees = new ArrayList<>();
		String selectQuery = "select * from employee where depId=?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = createPreparedStatement(connection, selectQuery, id); 
				ResultSet rs = preparedStatement.executeQuery()) {
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setDepId(rs.getInt(1));
				employee.setEmpPwd(rs.getString(2));
				employee.setEmpName(rs.getString(3));
				employee.setEmpDob(rs.getDate(4));
				employee.setDepId(rs.getInt(5));
				employees.add(employee);
			}
		} catch (SQLException e) {
			System.out.println("An unexpected sql exception occured, while getting all employees.");
		}
		
		return employees;
	}

	@Override
	public boolean addEmp(Employee employee) {
		Boolean sucess = false;
		String insertQuery = "insert into employee(empId, empPwd, empName, empDob, depId) values(?, ?, ?, ?, ?)";
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = createPreparedStatement(connection, insertQuery, employee)) {
			if(preparedStatement.executeUpdate()>0) 
				sucess = true;
		} catch (SQLException e) {
			System.out.println("An unexpected sql exception occured, while adding an employee.");
		}
		
		return sucess;
	}

	@Override
	public boolean updateEmp(Employee employee) {
		Boolean sucess = false;
		String updateQuery = "update employee set empId=?, empPwd=?, empName=?, empDob=?, depId=? where empId=?";
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = createPreparedStatement(connection, updateQuery, employee, employee.getEmpId())) {
			if(preparedStatement.executeUpdate()>0) 
				sucess = true;
		} catch (SQLException e) {
			System.out.println("An unexpected sql exception occured, while adding an employee.");
		}
		
		return sucess;
	}

	@Override
	public boolean deleteEmp(int id) {
		Boolean sucess = false;
		String deleteQuery = "delete from employee where empId=?";
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = createPreparedStatement(connection, deleteQuery, id)) {
			if(preparedStatement.executeUpdate()>0) 
				sucess = true;
		} catch (SQLException e) {
			System.out.println("An unexpected sql exception occured, while deletiing an employee.");
		}
		
		return sucess;
	}

	@Override
	public boolean validate(int id, String pwd) {
		Boolean sucess = false;
		String selectQuery = "select * from employee where empId=? and empPwd=?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = createPreparedStatement(connection, selectQuery, id, pwd); 
				ResultSet rs = preparedStatement.executeQuery()) {
			while (rs.next()) {
				sucess = true;
			}
		} catch (SQLException e) {
			System.out.println("An unexpected sql exception occured, while validating an employee.");
		}
		
		return sucess;
	}

	private PreparedStatement createPreparedStatement(Connection connection, String query,int id) throws SQLException {
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    return preparedStatement;
	}

	private PreparedStatement createPreparedStatement(Connection connection, String insertQuery, Employee employee) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	    preparedStatement.setInt(1, employee.getEmpId());
	    preparedStatement.setString(2, employee.getEmpPwd());
	    preparedStatement.setString(3, employee.getEmpName());
	    preparedStatement.setDate(4, new java.sql.Date(employee.getEmpDob().getTime()));
	    preparedStatement.setInt(5, employee.getDepId());
	    return preparedStatement;
	}
	
	private PreparedStatement createPreparedStatement(Connection connection, String insertQuery, Employee employee, int id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	    preparedStatement.setInt(1, employee.getEmpId());
	    preparedStatement.setString(2, employee.getEmpPwd());
	    preparedStatement.setString(3, employee.getEmpName());
	    preparedStatement.setDate(4, new java.sql.Date(employee.getEmpDob().getTime()));
	    preparedStatement.setInt(5, employee.getDepId());
	    preparedStatement.setInt(6, id);
	    return preparedStatement;
	}
	
	private PreparedStatement createPreparedStatement(Connection connection, String selectQuery, int id, String pwd) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	    preparedStatement.setInt(1, id);
	    preparedStatement.setString(2, pwd);
	    return preparedStatement;
	}
}
