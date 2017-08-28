package trng.imcs.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import trng.imcs.model.*;
import trng.imcs.service.BonusService;
import trng.imcs.dao.*;

public class BonusServiceImplementation implements BonusService {
	final Logger logger = Logger.getLogger(BonusServiceImplementation.class);

	public List<Bonus> loadFromFile() {
		logger.info("Loading bonus data from resource");
		List<Bonus> bonusList = new ArrayList<>();
		try (InputStreamReader inputStreamReader = 
				new InputStreamReader(getClass().getClassLoader().getResourceAsStream("bonusData.txt"));
				BufferedReader br = new BufferedReader(inputStreamReader);){
			String line;
			String[] d = null;
			while ((line = br.readLine()) != null) {
				d = line.split(",");
				Bonus e = new Bonus(Integer.parseInt(d[0]), Float.parseFloat(d[1]), Float.parseFloat(d[2]));
				bonusList.add(e);
			}
		} catch (FileNotFoundException e1) {
			logger.error(e1.getMessage());
		} catch (IOException e1) {
			logger.error(e1.getMessage());
		}
		return bonusList;

	}

	public void allocateBonus() {
		EmployeeServiceImplementation es = new EmployeeServiceImplementation();
		BonusDAOImplementation bDAO = new BonusDAOImplementation();
		List<Bonus> bonusList = new ArrayList<>();
		long startTime = new Date().getTime();
		try {
			bonusList = bDAO.getBonus();
			ExecutorService executorService = Executors.newFixedThreadPool(8);
			for (Bonus b : bonusList) {
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						List<Employee> empListWithDeptID = null;
						try {
							empListWithDeptID = es.getAllEmployeeDetails(b.getDepNo());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						for (Employee e : empListWithDeptID) {
							allocatingBonus(b, e);
						}
					}
				});
				thread.join();
				executorService.execute(thread);
			}

			logger.info("Time taken to calculate program:"+(new Date().getTime() - startTime));
			executorService.shutdown();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public void allocatingBonus(Bonus b, Employee e) {
		logger.info("allocating bonus to a employees by department No: " + e.getDepNo());
		EmployeeDAO dao = new EmployeeDAOImplementations();
		EmployeeBonus eb = new EmployeeBonus();
		int salaryGrade = e.getSalaryGrade();
		int deptNo = e.getDepNo();
		int empNo = e.getEmpNo();
		float totalAmount = b.getAmount();
		float remainingAmount = b.getRemainingAmount();
		float bonusAmount = 0;
		if (remainingAmount == 0) {
			eb.setEmpNO(empNo);
			eb.setAmount(0);
			eb.setStatus("INC");
			try {
				dao.addEmployeeBonus(eb);
			} catch (SQLException e1) {
				logger.error(e1.getMessage());
			}
		} else {
			if (remainingAmount < 0.1 * totalAmount) {
				b.setRemainingAmount(0);
				eb.setEmpNO(empNo);
				eb.setAmount(remainingAmount);
				java.util.Date d = new java.util.Date();
				eb.setDateAllocated(new java.sql.Date(d.getYear(), d.getMonth(), d.getDate()));
				eb.setStatus("PAR");
				try {
					dao.addEmployeeBonus(eb);
					dao.updateBonusInfo(deptNo, b);
				} catch (SQLException e1) {
					logger.error(e1.getMessage());
				}

			} else {
				switch (salaryGrade) {
				case 1:
					bonusAmount = (float) (0.1 * totalAmount);
					if (bonusAmount <= remainingAmount) {
						eb.setStatus("COM");
						b.setRemainingAmount(remainingAmount - bonusAmount);
						eb.setAmount(bonusAmount);
					} else {
						eb.setStatus("PAR");
						eb.setAmount(remainingAmount);
						b.setRemainingAmount(0);
					}

					break;
				case 2:
					bonusAmount = (float) (0.15 * totalAmount);
					if (bonusAmount <= remainingAmount) {
						eb.setStatus("COM");
						b.setRemainingAmount(remainingAmount - bonusAmount);
						eb.setAmount(bonusAmount);

					} else {
						eb.setStatus("PAR");
						eb.setAmount(remainingAmount);
						b.setRemainingAmount(0);

					}

					break;
				case 3:
					bonusAmount = (float) (0.2 * totalAmount);
					if (bonusAmount <= remainingAmount) {
						eb.setStatus("COM");
						b.setRemainingAmount(remainingAmount - bonusAmount);
						eb.setAmount(bonusAmount);

					} else {
						eb.setStatus("PAR");
						eb.setAmount(remainingAmount);
						b.setRemainingAmount(0);

					}

					break;
				case 4:
					bonusAmount = (float) (0.25 * totalAmount);
					if (bonusAmount <= remainingAmount) {
						eb.setStatus("COM");
						b.setRemainingAmount(remainingAmount - bonusAmount);
						eb.setAmount(bonusAmount);

					} else {
						eb.setStatus("PAR");
						eb.setAmount(remainingAmount);
						b.setRemainingAmount(0);

					}
					break;

				default:
					break;
				}

				java.util.Date d = new java.util.Date();
				eb.setDateAllocated(new java.sql.Date(d.getYear(), d.getMonth(), d.getDate()));
				eb.setEmpNO(empNo);

				try {
					dao.addEmployeeBonus(eb);
					dao.updateBonusInfo(deptNo, b);
				} catch (SQLException e1) {
					logger.error(e1.getMessage());
				}
			}
		}
	}
}
