package ems;

/**
 * Zadatak 1 - Zaposlenik na pola radnog vremena sa atributom workHours.
 */
public class PartTimeEmployee extends Employee {
    private int workHours; // sedmicno

    public PartTimeEmployee(int id, String name, double salary, int workHours) {
        super(id, name, salary);
        this.workHours = workHours;
    }

    public int getWorkHours() {
        return workHours;
    }

    public double getHourlyRate() {
        // pretpostavka: 4 sedmice u mjesecu
        return workHours == 0 ? 0 : salary / (workHours * 4.0);
    }

    @Override
    public void work() {
        System.out.println(name + " radi " + workHours + " sati sedmicno u fleksibilnim smjenama.");
    }

    @Override
    public String toFileLine() {
        return "PARTTIME," + id + "," + name + "," + salary + "," + workHours;
    }

    @Override
    public String toString() {
        return String.format("[PartTime] ID: %d | Ime: %s | Plata: %.2f KM | Sati/sedmica: %d",
                id, name, salary, workHours);
    }
}
