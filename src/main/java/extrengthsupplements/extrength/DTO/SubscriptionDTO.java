package extrengthsupplements.extrength.DTO;

import extrengthsupplements.extrength.models.Subscription;
import extrengthsupplements.extrength.models.SubscriptionTypes;

import java.time.LocalDate;

public class SubscriptionDTO {


    private long id;
    private SubscriptionTypes subscriptionTypes;
    private double amount;

    private LocalDate fromDate, toDate;




    public SubscriptionDTO(Subscription subscription) {

        this.id = subscription.getId();

        this.subscriptionTypes = subscription.getSubscriptionTypes();

        this.amount = subscription.getAmount();

        this.fromDate = subscription.getFromDate();

        this.toDate = subscription.getToDate();

    }

    public long getId() {
        return id;
    }

    public SubscriptionTypes getSubscriptionTypes() {
        return subscriptionTypes;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }


}
