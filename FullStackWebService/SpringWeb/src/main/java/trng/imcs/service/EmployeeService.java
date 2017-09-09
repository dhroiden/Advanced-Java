package trng.imcs.service;

import trng.imcs.model.Employee;

public interface EmployeeService {
	String baseUrl = "http://localhost:8081/SpringRest/employee";
	
	public Employee getEmpByEmpId(int id);
	public boolean addEmp(Employee employee);
	public boolean updateEmp(Employee employee);
	public boolean deleteEmp(int id);
	public boolean validateEmp(int id, String pwd);
}
