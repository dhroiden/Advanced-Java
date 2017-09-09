package trng.imcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import trng.imcs.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public Employee getEmpByEmpId(int id) {
		ResponseEntity<Employee> empResponseEntity = 
				restTemplate.getForEntity(baseUrl+"/"+Integer.toString(id), Employee.class);
		
		if (empResponseEntity.getStatusCode() == HttpStatus.OK) {
			return empResponseEntity.getBody();
		}
		return null;
	}

	@Override
	public boolean addEmp(Employee employee) {
		HttpEntity<Employee> empRequest = new HttpEntity<>(employee);
		ResponseEntity<Employee> empResponseEntity = 
				restTemplate.postForEntity(baseUrl+"/add", empRequest, Employee.class);
		
		if(empResponseEntity.getStatusCode() == HttpStatus.OK) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateEmp(Employee employee) {
		HttpEntity<Employee> empRequest = new HttpEntity<>(employee);
		ResponseEntity<Employee> empResponseEntity = 
				restTemplate.exchange(baseUrl+"/update", HttpMethod.PUT, empRequest, Employee.class);
		if(empResponseEntity.getStatusCode() == HttpStatus.OK) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteEmp(int id) {
		ResponseEntity<Employee> empResponseEntity = 
				restTemplate.exchange(baseUrl+"/"+Integer.toString(id), HttpMethod.DELETE, null, Employee.class);
		if(empResponseEntity.getStatusCode() == HttpStatus.OK) {
			return true;
		}
		return false;
		//restTemplate.delete(baseUrl+"/"+Integer.toString(id));
	}

	@Override
	public boolean validateEmp(int id, String pwd) {
		Employee employee = new Employee();
		employee.setEmpId(id);
		employee.setEmpPwd(pwd);
		HttpEntity<Employee> empRequest = new HttpEntity<>(employee);
		ResponseEntity<Employee> empResponseEntity = 
				restTemplate.exchange(baseUrl+"/login", HttpMethod.PUT, empRequest, Employee.class);
		if(empResponseEntity.getStatusCode() == HttpStatus.OK) {
			return true;
		}
		return false;
	}

}
