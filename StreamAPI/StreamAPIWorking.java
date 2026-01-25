import java.util.*;
import java.util.stream.*;

class Employee {
    String name;
    String dept;
    double salary;

    Employee(String n, String d, double s) { name = n; dept = d; salary = s; }
    public String getName() { return name; }
    public String getDept() { return dept; }
    public double getSalary() { return salary; }
}

public class StreamAPIWorking {
    public static  void main(String args[]){
        List<Employee> emps = Arrays.asList(
                new Employee("Alice", "Tech", 60000),
                new Employee("Bob", "HR", 45000),
                new Employee("Charlie", "Tech", 40000),
                new Employee("Kiran", "Tech", 80000),
                new Employee("Raju", "Tech", 60000),
                new Employee("Mohan", "Tech", 70000)
        );


        //Stream Operations example

        List<String> techstars = emps.stream()
                .filter(e -> e.getDept().equals("Tech"))
                .filter(e-> e.getSalary() > 50000)
                .sorted(Comparator.comparing(Employee::getName))
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println(techstars);
    }
}
