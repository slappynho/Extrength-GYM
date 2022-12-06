package extrengthsupplements.extrength.Services;

import extrengthsupplements.extrength.models.BillSubscription;

public interface BillSubscriptionServices {

    public void saveBillSubscription(BillSubscription billSubscription);

    public BillSubscription findById(long id);
}
