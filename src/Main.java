import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Alice", 101, 50000);
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
    }
}