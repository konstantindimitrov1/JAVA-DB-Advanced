import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Engine implements Runnable {
    private final Scanner scanner;
    private final EntityManager entityManager;

    public Engine() {
        this.scanner = new Scanner(System.in);
        this.entityManager = Persistence.createEntityManagerFactory("codeFirst")
                .createEntityManager();
    }

    @Override
    public void run() {
        System.out.println("Select DB from 1 to 6:");

        try {
            int number = Integer.parseInt(scanner.nextLine());

            switch (number) {
                case 1 -> createDbGringotts();
                case 2 -> createDbSales();
                case 3 -> createDbUniversity();
                case 4 -> createDbHospital();
                case 5 -> createDbBillsPayment();
                case 6 -> createDbFootballBetting();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid number from 1 to 6 next time :)");
        }

        entityManager.close();
    }

    private void createDbFootballBetting() {
        // TODO: 8/5/2022
    }

    private void createDbBillsPayment() {
        // TODO: 8/5/2022
    }

    private void createDbHospital() {
        // TODO: 8/5/2022
    }

    private void createDbUniversity() {
        // TODO: 8/5/2022
    }

    private void createDbSales() {
        // TODO: 8/5/2022
    }

    private void createDbGringotts() {
        // TODO: 8/5/2022
    }
}
