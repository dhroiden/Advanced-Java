package trng.imcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trng.imcs.dao.DepartmentDao;
import trng.imcs.model.Department;
import trng.imcs.model.Employee;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentDao depDao;

	@Override
	public Department getDepById(int id) {
		return depDao.getDepById(id);
	}

}
