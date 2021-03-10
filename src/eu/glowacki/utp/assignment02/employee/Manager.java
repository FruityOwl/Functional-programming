package eu.glowacki.utp.assignment02.employee;
import java.util.ArrayList;
import java.util.List;

public final class Manager extends Worker {

	private List<Employee> Subordinates;
	private List<Employee> allSubs;

	public Manager(String firstName) {
		super(firstName);
		Subordinates = new ArrayList<>();
		allSubs = new ArrayList<>();
	}

	public void addSub(Employee emp){
		Subordinates.add(emp);
		allSubs.add(emp);
		if(emp instanceof Manager){
			allSubs.addAll(Subordinates);
		}
	}

	public List<Employee> getSubordinates() {
		return Subordinates;
	}

	public List<Employee> getAllSubordinates(){
		return allSubs;
	}
}