package trng.imcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import trng.imcs.model.Department;
import trng.imcs.model.Employee;
import trng.imcs.service.DepartmentService;
import trng.imcs.service.EmployeeService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService depService;
	@Autowired
	EmployeeService empService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView getDeptPage() {
		ModelAndView modelAndView = new ModelAndView("department");
		return modelAndView;
	}
	
	@RequestMapping(value = "/searchDep", method = RequestMethod.GET)
	public ModelAndView getEmployee(@RequestParam("depId") int depId) {
		ModelAndView modelAndView = new ModelAndView("department");
		Department department = depService.getDepById(depId);
		System.out.println(department);
		if (department != null) {
			modelAndView.addObject("department", department);
			return modelAndView;
		}
		modelAndView.addObject("msg", "Department Info doesnt exists with the given ID");
		modelAndView.addObject("department", null);
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewAllEmps", method = RequestMethod.POST)
	public ModelAndView getAllEmployees(@RequestParam("depId") int depId) {
		ModelAndView modelAndView = new ModelAndView("department");
		Employee[] employees = depService.getAllEmpByDepId(depId);
		System.out.println(employees);
		if (employees!=null && employees.length>0) {
			modelAndView.addObject("employees", employees);
			return modelAndView;
		}
		modelAndView.addObject("msg", "No Employees in that Dept");
		return modelAndView;
	}
	
	@RequestMapping(value = "/updateEmp", method = RequestMethod.POST)
	public ModelAndView updateEmployee(@ModelAttribute("updateEmp") Employee employee) {
		ModelAndView modelAndView = new ModelAndView("department");
		
		if(empService.updateEmp(employee)) {
			modelAndView.addObject("msg", "Employee updated");
			modelAndView.addObject("employee", null);
			return modelAndView;
		}
		modelAndView.addObject("msg", "Unable to update the employee");
		modelAndView.addObject("employee",null);
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteEmp", method = RequestMethod.POST)
	public ModelAndView deleteEmployee(@RequestParam("empId") int empId) {
		ModelAndView modelAndView = new ModelAndView("department");
		
		if(empService.deleteEmp(empId)) {
			modelAndView.addObject("msg", "Employee deleted");
			modelAndView.addObject("employee",null);
			return modelAndView;
		}
		modelAndView.addObject("msg", "Employee delete falied");
		modelAndView.addObject("employee",null);
		return modelAndView;
	}
}
