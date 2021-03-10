package eu.glowacki.utp.assignment02.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import eu.glowacki.utp.assignment02.employee.*;
import eu.glowacki.utp.assignment02.payroll.PayrollEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.glowacki.utp.assignment02.HumanResourcesStatistics;

public class HumanResourcesStatisticsTest {
	
	private List<Employee> allEmployees;

	@Before
	public void before(){
		allEmployees = new ArrayList<>();

		Manager Boss = new Manager("Boss");
		Boss.setSurname("Boss");
		Boss.setBirthDate(LocalDate.of(1990,11,21));
		Boss.setBonus(BigDecimal.valueOf(1000));
		Boss.setEmploymentDate(LocalDate.of(1999,11,21));
		Boss.setSalary(BigDecimal.valueOf(10000));
		allEmployees.add(Boss);
		List<Employee> arr = Boss.getAllSubordinates();

		Manager man1 = new Manager("Man1");
		man1.setManager(Boss);
		man1.setSurname("Boss");
		man1.setBirthDate(LocalDate.of(2011,11,21));
		man1.setBonus(BigDecimal.valueOf(1));
		man1.setEmploymentDate(LocalDate.of(2010,11,21));
		man1.setSalary(BigDecimal.valueOf(10000));
		allEmployees.add(man1);

		Manager man2 = new Manager("Man2");
		man2.setManager(Boss);
		man2.setSurname("Boss");
		man2.setBirthDate(LocalDate.of(2010,11,21));
		man2.setBonus(BigDecimal.valueOf(1));
		man2.setEmploymentDate(LocalDate.of(2010,11,21));
		man2.setSalary(BigDecimal.valueOf(10000));
		allEmployees.add(man2);

		List<Worker> test = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			test.add(new Worker("Worker" + i));
			test.get(i).setSurname("HELP" + i);
			test.get(i).setBirthDate(LocalDate.of(2000 + i, 1 + i,12 + i));
			test.get(i).setEmploymentDate(LocalDate.of(2000 + i, 1 + i,12 + i));

			if(i % 2 == 0){

				test.get(i).setManager(man1);
				test.get(i).setSalary(BigDecimal.valueOf(i*100));
				test.get(i).setBonus(BigDecimal.valueOf(20*i));

			}else{

				test.get(i).setManager(man2);
				test.get(i).setSalary(BigDecimal.valueOf(i*110));
				test.get(i).setBonus(BigDecimal.valueOf(10*i));

			}
			allEmployees.add(test.get(i));
		}

		for (int i = 0; i < 10; i++) {
			test.add(new Worker("Trainee" + i));
			test.get(i).setSurname("AAAAAAAAA" + i);
			test.get(i).setBirthDate(LocalDate.of(2000 + i, 1 + i,12 + i));
			test.get(i).setEmploymentDate(LocalDate.of(2000 + i, 1 + i,12 + i));

			if(i % 2 == 0){
				test.get(i).setManager(man1);
				man1.addSub(test.get(i));
				test.get(i).setSalary(BigDecimal.valueOf(i*100));
			}else{
				test.get(i).setManager(man2);
				man2.addSub(test.get(i));
				test.get(i).setSalary(BigDecimal.valueOf(i*110));
			}
			allEmployees.add(test.get(i));
		}

		Boss.addSub(man1);
		Boss.addSub(man2);

		Assert.assertEquals(Boss, allEmployees.get(0));
		Assert.assertEquals(man1, allEmployees.get(1));
		Assert.assertEquals(man2, allEmployees.get(2));
	}

	@Test
	public void payroll() {
		List<PayrollEntry> check = HumanResourcesStatistics.payroll(allEmployees);
		Assert.assertFalse(check.isEmpty());
	}

	@Test
	public void subordinatesPayroll() {
		List<PayrollEntry> check = HumanResourcesStatistics.subordinatesPayroll(((Manager)allEmployees.get(0)));
		Assert.assertFalse(check.isEmpty());
	}

	@Test
	public void bonusTotal() {
		BigDecimal total = HumanResourcesStatistics.bonusTotal(allEmployees);
		Assert.assertEquals(new BigDecimal("2302"), total);
	}

	@Test
	public void oldestEmployee() {
		Employee emp = HumanResourcesStatistics.oldestEmployee(allEmployees);
		Assert.assertEquals(((Worker)allEmployees.get(0)).getEmploymentDate(), ((Worker)emp).getEmploymentDate());
	}

	@Test
	public void highestSalary() {
		BigDecimal total = HumanResourcesStatistics.highestSalary(allEmployees);
		Assert.assertEquals(total, allEmployees.get(0).getSalary());
	}

	@Test
	public void highestSalaryBonus() {
		BigDecimal emp = HumanResourcesStatistics.highestSalaryBonus(allEmployees);
		Assert.assertEquals(emp, (((Worker)(allEmployees.get(0))).getBonus()).add(allEmployees.get(0).getSalary()));
	}

	@Test
	public void search() {
		List<Employee> total = HumanResourcesStatistics.search((Manager)allEmployees.get(1));
		List<Employee> check = new ArrayList<>();
		List<Employee> subs = ((Manager) allEmployees.get(1)).getSubordinates();

		for (int i = 0; i < subs.size(); i++) {
			if(subs.get(i).getSurname().startsWith("A")){
				check.add(subs.get(i));
			}
		}
		Assert.assertEquals(check, total);
	}

	@Test
	public void searchSalary() {
		List<Employee> emp = HumanResourcesStatistics.searchSalary(allEmployees);
		List<Employee> check = new ArrayList<>();
		for (Employee allEmployee : allEmployees) {
			BigDecimal sum = BigDecimal.valueOf(0);
			if(allEmployee instanceof Trainee){
				sum = sum.add(allEmployee.getSalary());
			}else{
				sum = sum.add(allEmployee.getSalary()).add(((Worker)allEmployee).getBonus());
			}

			if (sum.compareTo(BigDecimal.valueOf(1000)) > 0) {
				check.add(allEmployee);
			}
		}
		Assert.assertEquals(emp, check);
	}
}