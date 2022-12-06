package extrengthsupplements.extrength.Services.Implements;

import extrengthsupplements.extrength.Services.BillSubscriptionServices;
import extrengthsupplements.extrength.models.BillSubscription;
import extrengthsupplements.extrength.repositories.BillSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillSubscriptionServicesImplements implements BillSubscriptionServices {
    @Autowired
    private BillSubscriptionRepository billSubscriptionRepository;

    @Override
    public void saveBillSubscription(BillSubscription billSubscription) {
        billSubscriptionRepository.save(billSubscription);
    }

    @Override
    public BillSubscription findById(long id) {
        return billSubscriptionRepository.findById(id);
    }
}
