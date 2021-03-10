package eu.glowacki.utp.assignment02.employee;
import java.time.LocalDate;

public abstract class Person {

	private final String firstName;// backing field
	private String surname;
	private LocalDate birthDate;

	protected Person(String _firstName) {
		firstName = _firstName;
	}

	public String getFirstName() { // getter
		return firstName;
	}

	public void setSurname(String _surname){
		surname = _surname;
	}

	public String getSurname() { // getter
		return surname;
	}

	public void setBirthDate(LocalDate _birthDate){
		birthDate = _birthDate;
	}

	public int getAge() {
		LocalDate curYear = LocalDate.now();
		return curYear.getYear() - birthDate.getYear();
	}

}