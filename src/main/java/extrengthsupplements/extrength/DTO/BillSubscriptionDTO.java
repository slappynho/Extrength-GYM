package extrengthsupplements.extrength.DTO;

import extrengthsupplements.extrength.models.BillSubscription;

public class BillSubscriptionDTO {


    private long id;

    private SubscriptionDTO subscription;


    public BillSubscriptionDTO(BillSubscription billSubscription){


        this.id = billSubscription.getId();


        this.subscription = new SubscriptionDTO(billSubscription.getSubscription());



    }

    public long getId() {
        return id;
    }

    public SubscriptionDTO getSubscription() {
        return subscription;
    }

}
