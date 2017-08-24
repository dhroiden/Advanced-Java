package trng.imcs.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import trng.imcs.model.*;
import trng.imcs.dao.*;
import trng.imcs.service.*;

public class EmployeeServiceImplementation implements EmployeeService {
	final Logger logger = Logger.getLogger(EmployeeServiceImplementation.class);
	EmployeeDAOImplementations dao = new EmployeeDAOImplementations();

	public List<Employee> loadFromFile() {
		logger.info("Loading file from resource");
		List<Employee> empList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("..//EmployeeLibrary//Resource//employeeData.txt"))) {
			String line;
			String[] d = null;
			int num = 0;
			while ((line = br.readLine()) != null) {
				d = line.split(",");
				String jDate = d[2];
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(jDate);
				String bDate = d[3];
				Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(bDate);
				Employee e = new Employee(Integer.parseInt(d[0]), Integer.parseInt(d[1]), new java.sql.Date(date1.getTime()), 
						new java.sql.Date(date2.getTime()), Float.parseFloat(d[4]), Integer.parseInt(d[5]));
				empList.add(e);
			}
		} catch (FileNotFoundException e1) {
			logger.error(e1.getMessage());
		} catch (IOException e1) {
			logger.error(e1.getMessage());
		} catch (ParseException e1) {
			logger.error(e1.getMessage());
		}
		return empList;
	}

	public List<Employee> getAllEmployeeDetails(int deptNo) throws SQLException {
		if(deptNo%2==0) {
			return dao.getAll(deptNo, "dob");
		}
		return dao.getAll(deptNo, "doj");
		
	}
}
