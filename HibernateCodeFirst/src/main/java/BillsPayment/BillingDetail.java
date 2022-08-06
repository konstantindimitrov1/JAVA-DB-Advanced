package BillsPayment;

import javax.persistence.*;

@Entity(name = "billing_detail")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BillingDetail {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private int number;

    @ManyToOne
    private User owner;

    public BillingDetail(int number, User owner) {
        this.number = number;
        this.owner = owner;
    }

    public BillingDetail() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
