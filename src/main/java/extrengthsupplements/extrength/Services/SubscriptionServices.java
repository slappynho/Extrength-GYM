package extrengthsupplements.extrength.Services;

import extrengthsupplements.extrength.models.Subscription;

import java.util.List;

public interface SubscriptionServices {

    public Subscription findSubscriptionById(long id);
    public List<Subscription> findAllSubscriptions();
    public void saveSubscription(Subscription subscription);

}
