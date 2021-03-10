package eu.glowacki.utp.assignment02.employee;
import java.math.BigDecimal;

public abstract class Employee extends Person {

	private BigDecimal salary;
	private Person manager = null;

	Employee(String firstName) {
		super(firstName);
	}

	public void setSalary(BigDecimal _salary){
		salary = _salary;
	}

	public BigDecimal getSalary(){
		return salary;
	}

	public Person getManager(){
		if(manager == null){
			return null;
		}
			return manager;
	}

	public void setManager(Manager mng){
		manager = mng;
		mng.addSub(Employee.this);
	}
}