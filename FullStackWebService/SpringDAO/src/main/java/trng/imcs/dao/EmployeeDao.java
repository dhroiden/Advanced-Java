package trng.imcs.dao;

import java.util.List;

import trng.imcs.model.Employee;

public interface EmployeeDao {
	Employee getEmpByEmpId(int id);
	List<Employee> getAllEmpByDepId(int id);
	boolean addEmp(Employee employee);
	boolean updateEmp(Employee employee);
	boolean deleteEmp(int id);
	boolean validate(int id, String pwd);
}
