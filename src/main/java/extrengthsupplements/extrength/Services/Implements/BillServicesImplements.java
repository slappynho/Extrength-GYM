package extrengthsupplements.extrength.Services.Implements;

import extrengthsupplements.extrength.Services.BillServices;
import extrengthsupplements.extrength.models.Bill;
import extrengthsupplements.extrength.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServicesImplements implements BillServices {
    @Autowired
    private BillRepository billRepository;

    @Override
    public void saveBill(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public Bill findBillById(long id) {
        return billRepository.findBillById(id);
    }
}
