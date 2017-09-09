package trng.imcs.test;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import trng.imcs.model.Department;
import trng.imcs.model.Employee;
import trng.imcs.service.DepartmentService;
import trng.imcs.service.DepartmentServiceImpl;
import trng.imcs.service.EmployeeService;
import trng.imcs.service.EmployeeServiceImpl;

public class TestApp {
	static ApplicationContext context = new AnnotationConfigApplicationContext("trng.imcs");
	static EmployeeService empService = context.getBean(EmployeeServiceImpl.class);
	static DepartmentService depService = context.getBean(DepartmentServiceImpl.class);
	
	public static void main(String[] args) {
		
		//addEmp();
		//updateEmp();
		//getEmpByEmpId(2);
		//getAllEmpByDepId(202);
		//deleteEmp(2);
		//validateEmp(1, "pwd");
		//getDep(101);
	}

	static void addEmp() {
		Employee employee = new Employee();
		employee.setEmpId(2);
		employee.setEmpName("bcd");
		employee.setEmpPwd("lkj");
		employee.setDepId(107);
		employee.setEmpDob(new Date());
		if(empService.addEmp(employee)) {
			System.out.println("sucess");
		} else {
			System.out.println("Failure");
		}
	}
	
	static void updateEmp() {
		Employee employee = new Employee();
		employee.setEmpId(7);
		employee.setEmpName("hgf");
		employee.setEmpPwd("mnb");
		employee.setDepId(407);
		employee.setEmpDob(new Date());
		if(empService.updateEmp(employee)) {
			System.out.println("sucess");
		} else {
			System.out.println("Failure");
		}
	}
	
	static void getEmpByEmpId(int id) {
		Employee employee = empService.getEmpByEmpId(id);
		if(employee!=null) {
			System.out.println("id:"+employee.getEmpId()+" pwd:"+employee.getEmpPwd()+" name:"+employee.getEmpName()+
					" dob:"+employee.getEmpDob()+" depId:"+employee.getDepId());
		} else {
			System.out.println("Failure");
		}
	}
	
	static void getAllEmpByDepId(int id) {
		List<Employee> employees = empService.getAllEmpByDepId(id);
		if(!employees.isEmpty()) {
			for(Employee employee: employees) {
					System.out.println("id:"+employee.getEmpId()+" pwd:"+employee.getEmpPwd()+" name:"+employee.getEmpName()+
							" dob:"+employee.getEmpDob()+" depId:"+employee.getDepId());
				
			}
		} else {
			System.out.println("Failure");
		}
	}
	
	static void deleteEmp(int id) {
		if(empService.deleteEmp(id)) {
			System.out.println("sucess");
		} else {
			System.out.println("Failure");
		}
	}
	
	static void validateEmp(int id, String pwd) {
		if(empService.validateEmp(id, pwd)) {
			System.out.println("sucess");
		} else {
			System.out.println("Failure");
		}
	}
	
	static void getDep(int id) {
		Department department = depService.getDepById(id);
		if(department!=null) {
			System.out.println("id:"+department.getDepId()+" name:"+department.getDepName());
		} else {
			System.out.println("Failure");
		}
	}
}
