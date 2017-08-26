package trng.imcs.model;

import java.sql.Date;

public class EmployeeBonus {
	int empNo;
	String status;
	float amount;
	Date dateAllocated;
	
	public EmployeeBonus(int empNo, String status, float amount, Date dateAllocated) {
		super();
		this.empNo = empNo;
		this.status = status;
		this.amount = amount;
		this.dateAllocated = dateAllocated;
	}
	
	public EmployeeBonus() {
	}

	public int getEmpNo() {
		return this.empNo;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public float getAmount() {
		return this.amount;
	}
	
	public Date getDateAllocated() {
		return this.dateAllocated;
	}
	
	public void setEmpNO(int empNo) {
		this.empNo = empNo;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public void setDateAllocated(Date dateAllocated) {
		this.dateAllocated = dateAllocated;
	}
}
