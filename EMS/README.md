# EMS (Employee-Manager System) – Challenge, Zadatak 1

Programiranje u Javi – IPI Akademija Tuzla
Autor: Faris Gazibegović

## Šta je urađeno
1. **Novi zaposlenici** – `Intern` (atribut `school`) i `PartTimeEmployee` (atribut `workHours`), oba nadjačavaju `work()`.
2. **Ukupni troškovi plata** – `calculateTotalSalaryCost()` u `Main`, koristi `instanceof` i casting za bonus menadžera.
3. **Sortiranje po plati (silazno)** – `Collections.sort()` s prilagođenim komparatorom (+ ručni bubble sort kao alternativa).
4. **Spremanje u datoteku** – `saveEmployees()` piše isti format kao ulaz (prefiksi `EMPLOYEE`, `MANAGER`, `INTERN`, `PARTTIME`), datoteka se ponovo učitava bez problema.
5. **Zaposlenik mjeseca** – `findEmployeeOfTheMonth()` traži najveću ukupnu platu (s bonusom).

## Format datoteke
```
EMPLOYEE,id,ime,plata
MANAGER,id,ime,plata,bonus
INTERN,id,ime,plata,skola
PARTTIME,id,ime,plata,satiSedmicno
```

## Pokretanje
Pokrenuti `ems.Main` (JDK 11+). Ako `employees.txt` ne postoji, kreira se primjer.
