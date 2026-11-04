package ems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * EMS (Employee-Manager System) - Challenge, Zadatak 1 (dijelovi 1-5).
 *
 * Format datoteke (jedan zaposlenik po redu):
 *   EMPLOYEE,id,ime,plata
 *   MANAGER,id,ime,plata,bonus
 *   INTERN,id,ime,plata,skola
 *   PARTTIME,id,ime,plata,satiSedmicno
 */
public class Main {

    private static final String CLASS_FILE = "employeedata.txt";
    private static final String INPUT_FILE = "employees.txt";
    private static final String OUTPUT_FILE = "employees_saved.txt";

    public static void main(String[] args) {
        // Ako postoji datoteka s casa (employeedata.txt), ucitaj nju; inace employees.txt
        String inputFile = new File(CLASS_FILE).exists() ? CLASS_FILE : INPUT_FILE;
        if (inputFile.equals(INPUT_FILE)) {
            createSampleFileIfMissing(INPUT_FILE);
        }
        System.out.println("Ucitavam iz: " + inputFile + "\n");

        List<Employee> employees = loadEmployees(inputFile);

        // 1. Dodavanje novih tipova zaposlenika
        employees.add(new Intern(6, "Lejla Hodzic", 600, "IPI Akademija Tuzla"));
        employees.add(new PartTimeEmployee(7, "Amar Mujic", 900, 20));

        System.out.println("=== 1. Svi zaposlenici i njihov stil rada ===");
        for (Employee e : employees) {
            System.out.println(e);
            e.work(); // polimorfizam - poziva se nadjacana metoda
        }

        // 2. Ukupni troskovi plata
        System.out.println("\n=== 2. Ukupni troskovi plata ===");
        System.out.printf("Ukupno: %.2f KM%n", calculateTotalSalaryCost(employees));

        // 3. Sortiranje po plati silazno
        System.out.println("\n=== 3. Zaposlenici sortirani po plati (silazno) ===");
        sortBySalaryDesc(employees);
        for (Employee e : employees) {
            System.out.println(e);
        }

        // 4. Spremanje u datoteku
        System.out.println("\n=== 4. Spremanje u datoteku ===");
        saveEmployees(employees, OUTPUT_FILE);
        List<Employee> reloaded = loadEmployees(OUTPUT_FILE);
        System.out.println("Ponovo ucitano iz '" + OUTPUT_FILE + "': " + reloaded.size() + " zaposlenika.");

        // 5. Zaposlenik mjeseca
        System.out.println("\n=== 5. Zaposlenik mjeseca ===");
        printEmployeeOfTheMonth(employees);
    }

    // ---------------------------------------------------------------
    // 2. Ukupni troskovi plata (uvjeti + instanceof + casting)
    // ---------------------------------------------------------------
    public static double calculateTotalSalaryCost(List<Employee> employees) {
        double total = 0;
        for (Employee e : employees) {
            total += e.getSalary();
            if (e instanceof Manager) {
                Manager m = (Manager) e; // casting
                total += m.getBonus();
            }
        }
        return total;
    }

    // ---------------------------------------------------------------
    // 3. Sortiranje - Collections.sort() s prilagodjenim komparatorom
    // ---------------------------------------------------------------
    public static void sortBySalaryDesc(List<Employee> employees) {
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee a, Employee b) {
                return Double.compare(b.getSalary(), a.getSalary()); // b pa a = silazno
            }
        });
    }

    /** Alternativa: rucno sortiranje (bubble sort), silazno po plati. */
    public static void bubbleSortBySalaryDesc(List<Employee> employees) {
        int n = employees.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (employees.get(j).getSalary() < employees.get(j + 1).getSalary()) {
                    Employee tmp = employees.get(j);
                    employees.set(j, employees.get(j + 1));
                    employees.set(j + 1, tmp);
                }
            }
        }
    }

    // ---------------------------------------------------------------
    // 4. Spremanje u datoteku (isti format kao ulaz)
    // ---------------------------------------------------------------
    public static void saveEmployees(List<Employee> employees, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Employee e : employees) {
                writer.write(e.toFileLine());
                writer.newLine();
            }
            System.out.println("Spremljeno " + employees.size() + " zaposlenika u '" + fileName + "'.");
        } catch (IOException ex) {
            System.out.println("Greska pri spremanju: " + ex.getMessage());
        }
    }

    // ---------------------------------------------------------------
    // 5. Zaposlenik mjeseca - najveca ukupna plata (s bonusom)
    // ---------------------------------------------------------------
    public static Employee findEmployeeOfTheMonth(List<Employee> employees) {
        if (employees.isEmpty()) {
            return null;
        }
        Employee best = employees.get(0);
        for (Employee e : employees) {
            if (e.getTotalSalary() > best.getTotalSalary()) {
                best = e;
            }
        }
        return best;
    }

    public static void printEmployeeOfTheMonth(List<Employee> employees) {
        Employee best = findEmployeeOfTheMonth(employees);
        if (best == null) {
            System.out.println("Nema zaposlenika.");
            return;
        }
        System.out.println("Zaposlenik mjeseca je: " + best.getName());
        System.out.println(best);
        System.out.printf("Ukupna plata (s bonusom): %.2f KM%n", best.getTotalSalary());
    }

    // ---------------------------------------------------------------
    // Ucitavanje iz datoteke
    // ---------------------------------------------------------------
    public static List<Employee> loadEmployees(String fileName) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                // Format s casa: "Name: Alice, ID: 101, Salary: 50000.0[, Bonus: $15000.0]"
                if (line.startsWith("Name:")) {
                    Employee e = parseLabeledLine(line);
                    if (e != null) {
                        employees.add(e);
                    } else {
                        System.out.println("Neispravan red, preskacem: " + line);
                    }
                    continue;
                }
                String[] p = line.split(",");
                try {
                    String type = p[0].trim().toUpperCase();
                    int id = Integer.parseInt(p[1].trim());
                    String name = p[2].trim();
                    double salary = Double.parseDouble(p[3].trim());

                    switch (type) {
                        case "EMPLOYEE":
                            employees.add(new Employee(id, name, salary));
                            break;
                        case "MANAGER":
                            employees.add(new Manager(id, name, salary, Double.parseDouble(p[4].trim())));
                            break;
                        case "INTERN":
                            employees.add(new Intern(id, name, salary, p[4].trim()));
                            break;
                        case "PARTTIME":
                            employees.add(new PartTimeEmployee(id, name, salary, Integer.parseInt(p[4].trim())));
                            break;
                        default:
                            System.out.println("Nepoznat tip, preskacem: " + line);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    System.out.println("Neispravan red, preskacem: " + line);
                }
            }
        } catch (IOException ex) {
            System.out.println("Greska pri citanju: " + ex.getMessage());
        }
        return employees;
    }

    /**
     * Cita red u formatu "Name: Bob, ID: 102, Salary: 80000.0, Bonus: $15000.0".
     * Ako postoji Bonus, zaposlenik je Manager.
     */
    private static Employee parseLabeledLine(String line) {
        String name = null;
        Integer id = null;
        Double salary = null;
        Double bonus = null;
        try {
            for (String part : line.split(",")) {
                String[] kv = part.split(":", 2);
                if (kv.length < 2) {
                    continue;
                }
                String key = kv[0].trim().toLowerCase();
                String value = kv[1].trim().replace("$", "");
                switch (key) {
                    case "name":   name = value; break;
                    case "id":     id = Integer.parseInt(value); break;
                    case "salary": salary = Double.parseDouble(value); break;
                    case "bonus":  bonus = Double.parseDouble(value); break;
                    default: break;
                }
            }
        } catch (NumberFormatException ex) {
            return null;
        }
        if (name == null || id == null || salary == null) {
            return null;
        }
        return bonus != null ? new Manager(id, name, salary, bonus) : new Employee(id, name, salary);
    }

    private static void createSampleFileIfMissing(String fileName) {
        File f = new File(fileName);
        if (f.exists()) {
            return;
        }
        try (BufferedWriter w = new BufferedWriter(new FileWriter(f))) {
            w.write("EMPLOYEE,1,Adnan Hasic,1500\n");
            w.write("EMPLOYEE,2,Amra Selimovic,1800\n");
            w.write("MANAGER,3,Kenan Begic,2500,800\n");
            w.write("EMPLOYEE,4,Selma Kovacevic,2200\n");
            w.write("MANAGER,5,Mirza Omerovic,2400,500\n");
        } catch (IOException ex) {
            System.out.println("Ne mogu kreirati primjer datoteke: " + ex.getMessage());
        }
    }
}
