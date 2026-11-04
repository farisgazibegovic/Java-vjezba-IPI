import java.time.LocalDate;
import java.util.Comparator;

public class Employee implements Comparable<Employee> {
    private String name;
    private int id;
    private double salary;
    private LocalDate hireDate;

    public Employee(String name, int id, double salary) {
        this(name, id, salary, LocalDate.now());
    }

    public Employee(String name, int id, double salary, LocalDate hireDate) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public double getSalary() { return salary; }
    public LocalDate getHireDate() { return hireDate; }

    public void work() {
        System.out.println(name + " is working.");
    }

    public String getDetails() {
        return "Name: " + name + ", ID: " + id + ", Salary: " + salary + ", Hired: " + hireDate;
    }

    // Prirodni poredak: po ID-u
    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    public static final Comparator<Employee> BY_SALARY = new Comparator<Employee>() {
        @Override
        public int compare(Employee a, Employee b) {
            return Double.compare(a.getSalary(), b.getSalary());
        }
    };

    public static final Comparator<Employee> BY_HIRE_DATE = new Comparator<Employee>() {
        @Override
        public int compare(Employee a, Employee b) {
            return a.getHireDate().compareTo(b.getHireDate());
        }
    };
}