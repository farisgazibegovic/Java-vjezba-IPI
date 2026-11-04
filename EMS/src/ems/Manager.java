package ems;

/**
 * Menadzer - zaposlenik koji uz platu dobija i bonus.
 */
public class Manager extends Employee {
    private double bonus;

    public Manager(int id, String name, double salary, double bonus) {
        super(id, name, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    @Override
    public double getTotalSalary() {
        return salary + bonus;
    }

    @Override
    public void work() {
        System.out.println(name + " vodi tim, organizuje sastanke i donosi odluke.");
    }

    @Override
    public String toFileLine() {
        return "MANAGER," + id + "," + name + "," + salary + "," + bonus;
    }

    @Override
    public String toString() {
        return String.format("[Manager]  ID: %d | Ime: %s | Plata: %.2f KM | Bonus: %.2f KM",
                id, name, salary, bonus);
    }
}
