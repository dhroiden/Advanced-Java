package trng.imcs.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;

import ass.imcs.dao.EmployeeDAO;
import trng.imcs.model.*;
import trng.imcs.service.*;
import trng.imcs.dao.*;

public class Client {
	final static Logger logger = Logger.getLogger(Client.class);

	public static void main(String[] args) {
		logger.info("starting program");
		BonusDAO bDAO = new BonusDAOImplementation();
		BonusService bs = new BonusServiceImplementation();
		EmployeeService es = new EmployeeServiceImplementation();
		EmployeeDAO eDao = new EmployeeDAOImplementations();
		List<Bonus> bonusList = null;

		try {
			logger.info("loading data from local resource file");
			bonusList = bs.loadFromFile();
			logger.info("adding bonus data to database from local resource file");
			bDAO.add(bonusList);
			logger.info("adding employee data to database from local resource file");
			eDao.add(es.loadFromFile());
			logger.info("allocating employee bonus to database");
			bs.allocateBonus();
		} catch (FileNotFoundException e1) {
			logger.error(e1.getMessage());
		} catch (IOException e1) {
			logger.error(e1.getMessage());
		} catch (ParseException e1) {
			logger.error(e1.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		logger.info("program terminated");
	}
}