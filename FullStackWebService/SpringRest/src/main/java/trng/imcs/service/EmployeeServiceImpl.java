package trng.imcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trng.imcs.dao.EmployeeDao;
import trng.imcs.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao empDao;

	@Override
	public Employee getEmpByEmpId(int id) {
		return empDao.getEmpByEmpId(id);
	}

	@Override
	public List<Employee> getAllEmpByDepId(int id) {
		return empDao.getAllEmpByDepId(id);
	}

	@Override
	public boolean addEmp(Employee employee) {
		return empDao.addEmp(employee);
	}

	@Override
	public boolean updateEmp(Employee employee) {
		return empDao.updateEmp(employee);
	}

	@Override
	public boolean deleteEmp(int id) {
		return empDao.deleteEmp(id);
	}

	@Override
	public boolean validateEmp(int id, String pwd) {
		return empDao.validate(id, pwd);
	}

}
