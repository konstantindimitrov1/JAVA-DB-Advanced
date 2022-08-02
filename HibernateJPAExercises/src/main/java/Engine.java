package src.main.java;

import src.main.java.entities.Address;
import src.main.java.entities.Employee;
import src.main.java.entities.Project;
import src.main.java.entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Comparator;

public class Engine implements Runnable {
    private final EntityManager entityManager;
    private final BufferedReader reader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        System.out.println("Please, enter exercise number from 1 to 13:");
        try {
            int exerciseNumber = Integer.parseInt(reader.readLine());
            switch (exerciseNumber) {
                case 1 -> System.out.println("This is just a setup.");
                case 2 -> changeCasing();
                case 3 -> containsEmployee();
                case 4 -> employeesWithSalaryOver50000();
                case 5 -> employeesFromDepartment();
                case 6 -> addingNewAddressAndUpdatingEmployee();
                case 7 -> addressesWithEmployeeCount();
                case 8 -> getEmployeeWithProject();
                case 9 -> findLatest10Projects();
                case 10 -> increaseSalaries();
                case 11 -> findEmployeesByFirstName();
                case 12 -> employeesMaximumSalaries();
                case 13 -> removeTowns();
                default -> throw new IllegalStateException("Unexpected value: " + exerciseNumber);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.entityManager.close();
        }
    }

    private void removeTowns() throws IOException {
        entityManager.getTransaction().begin();

        String townToRemove = reader.readLine();

        int ttr = entityManager.createQuery("DELETE FROM Town t " +
                        "WHERE t.name LIKE :ttr").setParameter("ttr", townToRemove)
                .executeUpdate();

//        int addressesDeleted = entityManager.createQuery("DELETE FROM Address a " +
//                        "WHERE a.town.name LIKE :tName")
//                .setParameter("tName", townToRemove)
//                .executeUpdate();
//
//        entityManager.createQuery("DELETE FROM Town t " +
//                        "WHERE t.name LIKE :tName")
//                .setParameter("tName", townToRemove)
//                .executeUpdate();

//        String addressAsPluralOrNot = addressesDeleted == 1 ? "address" :
//                "addresses";
//
//        System.out.printf("%d %s in %s deleted", addressesDeleted, addressAsPluralOrNot, townToRemove);

        System.out.println(ttr);

        entityManager.getTransaction().commit();
    }


    private void employeesMaximumSalaries() {
        entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.salary NOT BETWEEN 30000 AND 70000 " +
                        "GROUP BY e.department.name ", Employee.class)
                .getResultStream().sorted(Comparator.comparing(e -> e.getDepartment().getId()))
                .forEach(e -> System.out.printf("%s %.2f\n", e.getDepartment().getName(), e.getSalary()));

    }

    private void findEmployeesByFirstName() throws IOException {
        String pattern = reader.readLine();

        entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.firstName LIKE concat(:pattern, '%')", Employee.class)
                .setParameter("pattern", pattern)
                .getResultStream().forEach(e -> System.out.printf("%s %s - %s - ($%.2f)\n",
                        e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));
    }

    private void increaseSalaries() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.department.name IN ('Engineering', " +
                        "'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultStream().forEach(e -> {
                    BigDecimal newSalary = e.getSalary().multiply(new BigDecimal("1.12"));
                    e.setSalary(newSalary);
                    entityManager.persist(e);
                    System.out.printf("%s %s ($%.2f)\n", e.getFirstName(),
                            e.getLastName(), e.getSalary());
                });
        entityManager.getTransaction().commit();

    }

    private void findLatest10Projects() {
        entityManager.createQuery("SELECT p FROM Project p " +
                        "ORDER BY p.startDate desc", Project.class)
                .getResultStream().limit(10)
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("""
                                Project name: %s
                                \tProject Description: %s
                                \tProject Start Date:%s
                                \tProject End Date:%s
                                """,
                        p.getName(), p.getDescription(), p.getStartDate(), p.getEndDate()));


    }

    private void getEmployeeWithProject() throws IOException {
        int employeeId = Integer.parseInt(reader.readLine());

        entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.id = :empId", Employee.class)
                .setParameter("empId", employeeId)
                .getResultStream().limit(1)
                .forEach(e -> {
                    System.out.printf("%s %s - %s\n",
                            e.getFirstName(), e.getLastName(), e.getJobTitle());

                    e.getProjects().stream().map(Project::getName)
                            .sorted().forEach(System.out::println);
                });
    }

    private void addressesWithEmployeeCount() {
        entityManager.createQuery("SELECT a FROM Address a " +
                        "ORDER BY a.employees.size desc", Address.class).getResultStream().limit(10)
                .forEach(a -> System.out.printf("%s, %s - %d employees\n", a.getText(), a.getTown().getName(), a.getEmployees().size()));
    }

    private void addingNewAddressAndUpdatingEmployee() throws IOException {
        entityManager.getTransaction().begin();

        Address address = new Address();

        address.setText("Vitoshka 15");

        Address isAddressPresent = entityManager.createQuery("SELECT a FROM Address a " +
                "WHERE a.text LIKE 'Vitoshka 15'", Address.class).getResultList().get(0);

        if (isAddressPresent == null) {
            Town town = entityManager.createQuery("SELECT t FROM Town t WHERE t.name LIKE 'Sofia'", Town.class)
                    .getSingleResult();

            address.setTown(town);

            entityManager.persist(address);
        } else {
            address = isAddressPresent;
        }

        Employee e = entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.lastName LIKE :lastName", Employee.class)
                .setParameter("lastName", reader.readLine()).getResultList().get(0);

        e.setAddress(address);

        entityManager.persist(e);

        entityManager.getTransaction().commit();
    }

    private void employeesFromDepartment() {
        entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.department.name LIKE :deptName " +
                        "ORDER BY e.salary, e.id", Employee.class)
                .setParameter("deptName", "Research and Development")
                .getResultStream().forEach(e -> System.out.printf("%s %s from %s - $%.2f\n",
                        e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary()));

    }

    private void employeesWithSalaryOver50000() {
        entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.salary > 50000", Employee.class).getResultStream().map(Employee::getFirstName)
                .forEach(System.out::println);

    }

    private void containsEmployee() throws IOException {
        String employeeName = reader.readLine();

        boolean isPresent = entityManager.createQuery("SELECT firstName, lastName " +
                        "FROM Employee WHERE firstName LIKE ?1 AND lastName LIKE ?2")
                .setParameter(1, employeeName.split(" ")[0])
                .setParameter(2, employeeName.split(" ")[1]).getResultList().isEmpty();

        System.out.println(isPresent ? "No" : "Yes");

    }

    private void changeCasing() {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("UPDATE Town t SET t.name = upper(t.name) " +
                "WHERE length(t.name) <= 5");

        query.executeUpdate();

        entityManager.getTransaction().commit();
    }
}
