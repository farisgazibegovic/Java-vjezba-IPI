public class Manager extends Employee {
    private double bonus;

    public Manager(String name, int id, double salary, double bonus) {
        super(name, id, salary);
        this.bonus = bonus;
    }

    @Override
    public void work() {
        System.out.println(getName() + " is managing the team.");
    }

    public double getTotalSalary() {
        return getSalary() + bonus;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Bonus: $" + bonus;
    }
}