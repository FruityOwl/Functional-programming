package eu.glowacki.utp.assignment02;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import eu.glowacki.utp.assignment02.employee.Employee;
import eu.glowacki.utp.assignment02.employee.Manager;
import eu.glowacki.utp.assignment02.employee.Trainee;
import eu.glowacki.utp.assignment02.employee.Worker;
import eu.glowacki.utp.assignment02.payroll.PayrollEntry;

public final class HumanResourcesStatistics {

	private static boolean isTrainee(Employee e) {
		return e.getClass() == Trainee.class;
	}
	private static boolean isWorker(Employee e) {
		return e.getClass() == Worker.class;
	}

	public static List<PayrollEntry> payroll(List<Employee> employees) {
		return  employees
				.stream()
				.map(e -> new PayrollEntry(
						e,
						e.getSalary(),
						(isTrainee(e) ? new BigDecimal(0) : ((Worker)e).getBonus())))
				.collect(Collectors.toList());
	}

	public static List<PayrollEntry> subordinatesPayroll(Manager manager) {

		return  manager.getSubordinates()
				.stream()
				.map(e -> new PayrollEntry(
						e,
						e.getSalary(),
						(isTrainee(e) ? new BigDecimal(0) : ((Worker)e).getBonus())))
				.collect(Collectors.toList());
	}

	public static BigDecimal bonusTotal(List<Employee> employees) {
		return employees.stream()
				.map(e -> ((Worker)e).getBonus())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public static Employee oldestEmployee(List<Employee> employees){
		Optional<Employee> optionalEmployee = employees
				.stream()
				.filter(emp -> (emp instanceof Worker))
				.min(Comparator.comparing(emp -> ((Worker)emp).getEmploymentDate()));
		return optionalEmployee.get();
	}

	public static BigDecimal highestSalary(List<Employee> employees){
		Optional<Employee> optionalEmployee = employees
				.stream()
				.max(Comparator.comparing(Employee::getSalary));
		return optionalEmployee.get().getSalary();
	}

	public static BigDecimal highestSalaryBonus(List<Employee> employees){
		Optional<Employee> optionalEmployee = employees
				.stream()
				.filter(emp -> (emp instanceof Worker))
				.max(Comparator.comparing(emp -> emp.getSalary().add(((Worker)emp).getBonus())));
		Employee emp = optionalEmployee.get();
		return emp.getSalary().add(((Worker)emp).getBonus());
	}

	public static List<Employee> search(Manager manager){
		return  manager.getSubordinates()
				.stream()
				.filter(emp -> emp.getSurname().startsWith("A"))
				.collect(Collectors.toList());
	}

	public static List<Employee> searchSalary(List<Employee> employees){
		return  employees
				.stream()
				.filter(emp -> isWorker(emp) ?  emp.getSalary().add(((Worker)emp).getBonus()).compareTo(BigDecimal.valueOf(1000)) > 0 :
												emp.getSalary().compareTo(BigDecimal.valueOf(1000)) > 0)
				.collect(Collectors.toList());
	}
}