package trng.imcs.model;

import java.sql.Date;

public class Employee {
	int empNo;
	int depNo;
	Date doj;
	Date dob;
	float salary;
	int salaryGrade;
	
	public Employee() {
	}
	
	public Employee(int empNo, int depNo, Date doj, Date dob, float salary, int salaryGrade) {
		super();
		this.empNo = empNo;
		this.depNo = depNo;
		this.doj = doj;
		this.dob = dob;
		this.salary = salary;
		this.salaryGrade = salaryGrade;
	}

	public int getEmpNo() {
		return this.empNo;
	}
	
	public int getDepNo() {
		return this.depNo;
	}
	
	public Date getDoj() {
		return this.doj;
	}
	
	public Date getDob() {
		return this.dob;
	}
	
	public float getSalary() {
		return this.salary;
	}
	
	public int getSalaryGrade() {
		return this.salaryGrade;
	}
	
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	
	public void setDepNo(int depNo) {
		this.depNo = depNo;
	}
	
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	public void setSalaryGrade(int salaryGrade) {
		this.salaryGrade = salaryGrade;
	}
}
