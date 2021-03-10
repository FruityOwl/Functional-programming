package eu.glowacki.utp.assignment02.payroll;
import java.math.BigDecimal;
import eu.glowacki.utp.assignment02.employee.Employee;

public final class PayrollEntry {

	private final Employee _employee;
	private final BigDecimal _salaryPlusBonus;
	
	public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
		_employee = employee;
		if(salary == null){
			_salaryPlusBonus = bonus;
		}else if(bonus == null) {
			_salaryPlusBonus = salary;
		}else if(salary == null && bonus == null){
			_salaryPlusBonus = null;
		}else {
			_salaryPlusBonus = salary.add(bonus); // validate whether salary and bonus are not null
		}
	}

	public Employee get_employee() {
		return _employee;
	}

	public BigDecimal get_salaryPlusBonus() {
		return _salaryPlusBonus;
	}
}