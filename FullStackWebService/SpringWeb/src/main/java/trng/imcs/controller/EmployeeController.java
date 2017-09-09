package trng.imcs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import trng.imcs.model.Employee;
import trng.imcs.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getEmpPage() {
		ModelAndView modelAndView = new ModelAndView("employee");
		return modelAndView;
	}
	
	@RequestMapping(value = "/searchEmp", method = RequestMethod.POST)
	public ModelAndView getEmployee(@RequestParam("empId") int empId) {
		ModelAndView modelAndView = new ModelAndView("employee");
		Employee employee = empService.getEmpByEmpId(empId);
		if (null != employee) {
			modelAndView.addObject("msg", "Employee found");
			modelAndView.addObject("employee", employee);
			return modelAndView;
		}
		modelAndView.addObject("msg", "Employee not found for Id : "+empId);
		modelAndView.addObject("employee", null);
		return modelAndView;
	}
	
	@RequestMapping(value = "/addEmp", method = RequestMethod.POST)
	public ModelAndView addEmployee(@ModelAttribute("addEmp") Employee employee) {
		ModelAndView modelAndView = new ModelAndView("employee");
		
		if(empService.addEmp(employee)) {
			modelAndView.addObject("msg", "Employee added");
			modelAndView.addObject("employeeAdd", employee);
			return modelAndView;
		}
		modelAndView.addObject("msg", "Unable to add the employee");
		modelAndView.addObject("employeeAdd", null);
		return modelAndView;
	}
	
	@RequestMapping(value = "/updateEmp", method = RequestMethod.POST)
	public ModelAndView updateEmployee(@ModelAttribute("updateEmp") Employee employee) {
		ModelAndView modelAndView = new ModelAndView("employee");
		
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
		ModelAndView modelAndView = new ModelAndView("employee");
		
		if(empService.deleteEmp(empId)) {
			modelAndView.addObject("msg", "Employee deleted");
			modelAndView.addObject("employee", null);
			return modelAndView;
		}
		modelAndView.addObject("msg", "Unable to add the employee");
		modelAndView.addObject("employee",null);
		return modelAndView;
	}
}
