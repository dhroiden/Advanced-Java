package trng.imcs.dao;

import java.sql.SQLException;
import java.util.List;

import trng.imcs.model.*;

public interface EmployeeDAO {
	public void add(List<Employee> empList) throws SQLException;
	List<Employee> getAll(int deptId, String orderBy) throws SQLException;
	List<Employee> getAll() throws SQLException;
	List<Bonus> getAllEmployeeBonus() throws SQLException;
	public Bonus getBonusInfo(int deptId) throws SQLException;
	public boolean addEmployeeBonus(final EmployeeBonus e) throws SQLException;
	public boolean updateBonusInfo(int deptId, Bonus bonus) throws SQLException;
}
