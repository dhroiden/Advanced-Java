package trng.imcs.service;

import trng.imcs.model.Department;
import trng.imcs.model.Employee;

public interface DepartmentService {
	String baseUrl = "http://localhost:8081/SpringRest/department";
	
	public Department getDepById(int id);
	public Employee[] getAllEmpByDepId(int id);
}
