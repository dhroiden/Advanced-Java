package trng.imcs.test;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import trng.imcs.dao.DepartmentDao;
import trng.imcs.dao.DepartmentDaoImpl;
import trng.imcs.dao.EmployeeDao;
import trng.imcs.dao.EmployeeDaoImpl;
import trng.imcs.model.Department;
import trng.imcs.model.Employee;

public class TestApp {
	static ApplicationContext context = new AnnotationConfigApplicationContext("trng.imcs");
	static EmployeeDao empDao = context.getBean(EmployeeDaoImpl.class);
	static DepartmentDao depDao = context.getBean(DepartmentDaoImpl.class);
	
	public static void main(String[] args) {
		
		//addEmp();
		//updateEmp();
		getEmpByEmpId(2);
		//getAllEmpByDepId(207);
		//deleteEmp(2);
		//validateEmp(1, "pwd");
		//getDep(102);
	}

	static void addEmp() {
		Employee employee = new Employee();
		employee.setEmpId(5);
		employee.setEmpName("zxc");
		employee.setEmpPwd("asd");
		employee.setDepId(202);
		employee.setEmpDob(new Date());
		if(empDao.addEmp(employee)) {
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
		if(empDao.updateEmp(employee)) {
			System.out.println("sucess");
		} else {
			System.out.println("Failure");
		}
	}
	
	static void getEmpByEmpId(int id) {
		Employee employee = empDao.getEmpByEmpId(id);
		if(employee!=null) {
			System.out.println("id:"+employee.getEmpId()+" pwd:"+employee.getEmpPwd()+" name:"+employee.getEmpName()+
					" dob:"+employee.getEmpDob()+" depId:"+employee.getDepId());
		} else {
			System.out.println("Failure");
		}
	}
	
	static void getAllEmpByDepId(int id) {
		List<Employee> employees = empDao.getAllEmpByDepId(id);
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
		if(empDao.deleteEmp(id)) {
			System.out.println("sucess");
		} else {
			System.out.println("Failure");
		}
	}
	
	static void validateEmp(int id, String pwd) {
		if(empDao.validate(id, pwd)) {
			System.out.println("sucess");
		} else {
			System.out.println("Failure");
		}
	}
	
	static void getDep(int id) {
		Department department = depDao.getDepById(id);
		if(department!=null) {
			System.out.println("id:"+department.getDepId()+" name:"+department.getDepName());
		} else {
			System.out.println("Failure");
		}
	}
}

