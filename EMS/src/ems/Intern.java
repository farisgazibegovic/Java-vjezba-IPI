package ems;

/**
 * Zadatak 1 - Pripravnik (Intern) sa jedinstvenim atributom school.
 */
public class Intern extends Employee {
    private String school;

    public Intern(int id, String name, double salary, String school) {
        super(id, name, salary);
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public void attendTraining() {
        System.out.println(name + " prisustvuje internoj obuci.");
    }

    @Override
    public void work() {
        System.out.println(name + " uci od mentora i pomaze na projektima (skola: " + school + ").");
    }

    @Override
    public String toFileLine() {
        return "INTERN," + id + "," + name + "," + salary + "," + school;
    }

    @Override
    public String toString() {
        return String.format("[Intern]   ID: %d | Ime: %s | Plata: %.2f KM | Skola: %s",
                id, name, salary, school);
    }
}
