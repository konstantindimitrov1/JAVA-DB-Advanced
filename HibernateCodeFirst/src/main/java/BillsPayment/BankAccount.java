package BillsPayment;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BankAccount extends BillingDetail {

    @Column(name = "bank_name", length = 50)
    private String bankName;

    @Column(name = "swift", length = 10)
    private String SWIFT;

    public BankAccount() {
    }

    public BankAccount(int number, User owner, String bankName, String SWIFT) {
        super(number, owner);
        this.bankName = bankName;
        this.SWIFT = SWIFT;


    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSWIFT() {
        return SWIFT;
    }

    public void setSWIFT(String SWIFT) {
        this.SWIFT = SWIFT;
    }
}
