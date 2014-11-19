package nl.playground.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nl.playground.java8.model.Employee;

public class PredicateMain {

	private static Predicate<Employee> isAdultMale() {
		Predicate<Employee> result = p -> p.getAge() > 21 && p.getGender().equalsIgnoreCase("M");
		return result;
	}

	public static Predicate<Employee> isAdultFemale() {
		return p -> p.getAge() > 18 && p.getGender().equalsIgnoreCase("F");
	}

	public static Predicate<Employee> isAgeMoreThan(Integer age) {
		return p -> p.getAge() > age;
	}

	public static List<Employee> filterEmployees(List<Employee> employees, Predicate<Employee> predicate) {
		return employees.stream().filter(predicate).collect(Collectors.<Employee> toList());
	}

	public static void main(String[] args) {
		Employee e1 = new Employee(1, 23, "M", "Rick", "Beethovan");
		Employee e2 = new Employee(2, 13, "F", "Martina", "Hengis");
		Employee e3 = new Employee(3, 43, "M", "Ricky", "Martin");
		Employee e4 = new Employee(4, 26, "M", "Jon", "Lowman");
		Employee e5 = new Employee(5, 19, "F", "Cristine", "Maria");
		Employee e6 = new Employee(6, 15, "M", "David", "Feezor");
		Employee e7 = new Employee(7, 68, "F", "Melissa", "Roy");
		Employee e8 = new Employee(8, 79, "M", "Alex", "Gussin");
		Employee e9 = new Employee(9, 15, "F", "Neetu", "Singh");
		Employee e10 = new Employee(10, 45, "M", "Naveen", "Jain");

		List<Employee> employees = new ArrayList<Employee>();
		employees.addAll(Arrays.asList(new Employee[] { e1, e2, e3, e4, e5, e6, e7, e8, e9, e10 }));

		System.out.println(filterEmployees(employees, isAdultMale()));

		System.out.println(filterEmployees(employees, isAdultFemale()));

		System.out.println(filterEmployees(employees, isAgeMoreThan(35)));

		// Employees other than above collection of "isAgeMoreThan(35)" can be
		// get using negate()
		System.out.println(filterEmployees(employees, isAgeMoreThan(35).negate()));

	}

}
