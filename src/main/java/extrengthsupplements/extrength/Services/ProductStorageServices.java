package extrengthsupplements.extrength.Services;

import extrengthsupplements.extrength.models.ProductStorage;

public interface ProductStorageServices {

    public void saveProductStorage(ProductStorage productStorage);

    public ProductStorage findProductStorageById(long id);
}
