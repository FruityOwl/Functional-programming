package eu.glowacki.utp.assignment02.employee;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {

	private LocalDate employmentDate = null;
	private BigDecimal bonus = null;

	public Worker(String firstName) {
		super(firstName);
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public LocalDate getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(LocalDate employmentDate) {
		this.employmentDate = employmentDate;
	}

	public BigDecimal getBonus() {
		return bonus;
	}
}