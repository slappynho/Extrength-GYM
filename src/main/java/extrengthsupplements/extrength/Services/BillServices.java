package extrengthsupplements.extrength.Services;

import extrengthsupplements.extrength.models.Bill;

public interface BillServices {

    public void saveBill(Bill bill);
    public Bill findBillById(long id);
}
