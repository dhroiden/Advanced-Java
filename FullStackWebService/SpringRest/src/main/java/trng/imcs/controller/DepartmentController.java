package trng.imcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trng.imcs.model.Department;
import trng.imcs.model.Employee;
import trng.imcs.service.DepartmentService;
import trng.imcs.service.EmployeeService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	DepartmentService depService;
	@Autowired
	EmployeeService empService;
	
	@RequestMapping(value="/{depId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDepById(@PathVariable int depId){
		Department department = depService.getDepById(depId);
		if(department != null){
			return new ResponseEntity<>(department, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value="/employees/{depId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllEmpByDepId(@PathVariable int depId){
		List<Employee> employees = empService.getAllEmpByDepId(depId);
		if(!employees.isEmpty()){
			return new ResponseEntity<>(employees, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
