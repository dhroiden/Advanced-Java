package trng.imcs.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import trng.imcs.model.*;

public interface EmployeeService {
	public List<Employee> loadFromFile() throws FileNotFoundException, IOException, ParseException;
	public List<Employee> getAllEmployeeDetails(int deptNo) throws SQLException;
}
