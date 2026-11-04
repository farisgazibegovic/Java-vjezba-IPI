package ems;

/**
 * Osnovna klasa za sve zaposlenike u EMS (Employee-Manager System).
 */
public class Employee {
    protected int id;
    protected String name;
    protected double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    /** Ukupna plata - za obicnog zaposlenika je to samo plata. */
    public double getTotalSalary() {
        return salary;
    }

    public void work() {
        System.out.println(name + " radi svoje svakodnevne zadatke.");
    }

    /** Linija za upis u datoteku, isti format kao ulaz. */
    public String toFileLine() {
        return "EMPLOYEE," + id + "," + name + "," + salary;
    }

    @Override
    public String toString() {
        return String.format("[Employee] ID: %d | Ime: %s | Plata: %.2f KM", id, name, salary);
    }
}
