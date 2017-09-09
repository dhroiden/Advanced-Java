package trng.imcs.model;

import java.util.Date;

public class Employee {
	int empId;
	String empPwd;
	String empName;
	Date empDob;
	int depId;
	
	public Employee() {
		
	}

	public Employee(int empId, String empPwd, String empName, Date empDob, int depId) {
		super();
		this.empId = empId;
		this.empPwd = empPwd;
		this.empName = empName;
		this.empDob = empDob;
		this.depId = depId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getEmpDob() {
		return empDob;
	}

	public void setEmpDob(Date empDob) {
		this.empDob = empDob;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}
}
