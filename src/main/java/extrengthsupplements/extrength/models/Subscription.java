package extrengthsupplements.extrength.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
public class Subscription {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;


    private SubscriptionTypes subscriptionTypes;

    private double amount;

    private LocalDate fromDate, toDate;

    public Subscription(){}

    public Subscription(SubscriptionTypes subscriptionTypes, double amount, LocalDate fromDate, LocalDate toDate) {
        this.subscriptionTypes = subscriptionTypes;
        this.amount = amount;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SubscriptionTypes getSubscriptionTypes() {
        return subscriptionTypes;
    }

    public void setSubscriptionTypes(SubscriptionTypes subscriptionTypes) {
        this.subscriptionTypes = subscriptionTypes;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

}
