package trng.imcs.model;

public class Bonus {
	int depNo;
	float amount;
	float remainigAmount;
	
	public Bonus(int deptNo, float amount, float remainigAmount) {
		super();
		this.depNo = deptNo;
		this.amount = amount;
		this.remainigAmount = remainigAmount;
	}

	public Bonus() {
	}

	public int getDepNo() {
		return this.depNo;
	}
	
	public float getAmount() {
		return this.amount;
	}
	
	public float getRemainingAmount() {
		return this.remainigAmount;
	}
	
	public void setDepNo(int depNo) {
		this.depNo = depNo;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public void setRemainingAmount(float remainigAmount) {
		this.remainigAmount = remainigAmount;
	}
}
