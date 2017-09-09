package trng.imcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import trng.imcs.model.Department;
import trng.imcs.model.Employee;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public Department getDepById(int id) {
		ResponseEntity<Department> depResponseEntity = 
				restTemplate.getForEntity(baseUrl+"/"+Integer.toString(id), Department.class);
		
		if (depResponseEntity.getStatusCode() == HttpStatus.OK) {
			return depResponseEntity.getBody();
		}
		return null;
	}

	@Override
	public Employee[] getAllEmpByDepId(int id) {
		ResponseEntity<Employee[]> depResponseEntity = 
				restTemplate.getForEntity(baseUrl+"/employees/"+Integer.toString(id), Employee[].class);
		
		if (depResponseEntity.getStatusCode() == HttpStatus.OK) {
			return depResponseEntity.getBody();
		}
		return null;
	}

}
