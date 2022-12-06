package extrengthsupplements.extrength.Services.Implements;


import extrengthsupplements.extrength.Services.SubscriptionServices;
import extrengthsupplements.extrength.models.Subscription;
import extrengthsupplements.extrength.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServicesImplements implements SubscriptionServices {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription findSubscriptionById(long id) {
        return subscriptionRepository.findSubscriptionById(id);
    }

    @Override
    public List<Subscription> findAllSubscriptions(){
        return subscriptionRepository.findAll();
    }

    @Override
    public void saveSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }
}

