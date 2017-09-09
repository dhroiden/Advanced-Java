package trng.imcs.service;

import java.util.List;

import trng.imcs.model.Employee;

public interface EmployeeService {
	public Employee getEmpByEmpId(int id);
	public List<Employee> getAllEmpByDepId(int id);
	public boolean addEmp(Employee employee);
	public boolean updateEmp(Employee employee);
	public boolean deleteEmp(int id);
	public boolean validateEmp(int id, String pwd);
}
