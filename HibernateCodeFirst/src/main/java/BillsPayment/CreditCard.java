package BillsPayment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
public class CreditCard extends BillingDetail {


    @Column(length = 30)
    private String type;

    @Column(name = "expiration_month")
    private LocalDate expirationMonth;

    @Column(name = "expiration_year")
    private LocalDate expirationYear;


    public CreditCard(int number, User owner, String type, LocalDate expirationMonth, LocalDate expirationYear) {
        super(number, owner);
        this.type = type;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public CreditCard() {
    }
}
