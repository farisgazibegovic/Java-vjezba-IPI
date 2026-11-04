import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Alice", 101, 50000, LocalDate.of(2022, 3, 15));
        Manager mgr1 = new Manager("Bob", 102, 80000, 15000);

        // Polymorphism: Employee reference pointing to a Manager object
        Employee emp2 = new Manager("Charlie", 103, 90000, 20000);

        System.out.println(emp1.getDetails());
        emp1.work();

        System.out.println(mgr1.getDetails());
        mgr1.work();

        System.out.println(emp2.getDetails()); // Calls overridden method in Manager
        emp2.work();                           // Demonstrates polymorphism

        // Upis podataka u fajl
        try (FileWriter writer = new FileWriter("employeedata.txt")) {
            writer.write(emp1.getDetails() + "\n");
            writer.write(mgr1.getDetails() + "\n");
            writer.write(emp2.getDetails() + "\n");
            System.out.println("Podaci su upisani u employeedata.txt");
        } catch (IOException e) {
            System.out.println("Greska pri upisu: " + e.getMessage());
        }

        // ===== Zadatak 2: Comparable i Comparator =====
        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(mgr1);
        employees.add(emp2);
        employees.add(new Employee("Dino", 100, 60000, LocalDate.of(2019, 7, 1)));
        employees.add(new Employee("Ema", 104, 70000, LocalDate.of(2024, 1, 10)));

        System.out.println("\n--- Sortirano po ID-u (compareTo) ---");
        Collections.sort(employees);
        for (Employee e : employees) System.out.println(e.getDetails());

        System.out.println("\n--- Sortirano po plati (compare) ---");
        Collections.sort(employees, Employee.BY_SALARY);
        for (Employee e : employees) System.out.println(e.getDetails());

        System.out.println("\n--- Sortirano po datumu zaposlenja (compare) ---");
        Collections.sort(employees, Employee.BY_HIRE_DATE);
        for (Employee e : employees) System.out.println(e.getDetails());
    }
}