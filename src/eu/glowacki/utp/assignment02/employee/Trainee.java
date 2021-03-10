package eu.glowacki.utp.assignment02.employee;
import java.time.LocalDate;
import java.util.Calendar;

public class Trainee extends Employee {

	private LocalDate apprStartDate;
	private int apprLength;

	public Trainee(String firstName) {
		super(firstName);
	}

	public void setApprStartDate(LocalDate _apprStartDate){
		apprStartDate = _apprStartDate;
	}

	public LocalDate getApprStartDate(){
		return apprStartDate;
	}

	public void setApprLength(int _apprLength){
		apprLength = _apprLength;
	}

	public int getApprLength(){
		return apprLength;
	}
}