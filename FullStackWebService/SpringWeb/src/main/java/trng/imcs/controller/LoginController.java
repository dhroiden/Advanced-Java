package trng.imcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import trng.imcs.model.Employee;
import trng.imcs.service.EmployeeService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	EmployeeService empService;
	
	@RequestMapping(value = "/validateEmp", method = RequestMethod.POST)
	public ModelAndView validateEmployee(@ModelAttribute("updateEmp") Employee employee) {
		ModelAndView modelAndView = new ModelAndView("login");
		
		if (empService.validateEmp(employee.getEmpId(), employee.getEmpPwd())) {
			modelAndView.addObject("msg", "login sucess");
			modelAndView.addObject("employee", employee);
			return modelAndView;
		}
		modelAndView.addObject("msg", "login failed");
		modelAndView.addObject("employee", null);
		return modelAndView;
	}
}
