package nl.playground.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import nl.playground.java8.model.Employee;

public class LamdaSorting {

	private static List<Employee> getEmployees() {
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
		return employees;
	}

	private static void print(List<Employee> employeeList) {
		System.out.println();
		System.out.println(employeeList);
	}

	public static void main(String[] args) {
		List<Employee> employeeList = getEmployees();

		// sort all employees by age:
		employeeList.sort(Comparator.comparing(e -> e.getAge()));
		print(employeeList);

		// sort employees by lastname with different syntax:
		employeeList.sort(Comparator.comparing(Employee::getLastName));
		print(employeeList);

		// in reverse order:
		Comparator<Employee> comparator = Comparator.comparing(Employee::getLastName);
		employeeList.sort(comparator.reversed());
		print(employeeList);

		// sort on multiple fields, Group By
		Comparator<Employee> groupByComparator = Comparator.comparing(Employee::getLastName).thenComparing(
				Employee::getFirstName);
		employeeList.sort(groupByComparator);
		print(employeeList);

		// parallel sorting, multiple threads:
		Employee[] employeesArray = employeeList.toArray(new Employee[employeeList.size()]);
		Arrays.parallelSort(employeesArray, groupByComparator);
		print(Arrays.asList(employeesArray));

	}
}
