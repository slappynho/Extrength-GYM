package extrengthsupplements.extrength.Services.Implements;

import extrengthsupplements.extrength.Services.ProductStorageServices;
import extrengthsupplements.extrength.models.ProductStorage;
import extrengthsupplements.extrength.repositories.ProductStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductStorageServicesImplements implements ProductStorageServices {

    @Autowired
    private ProductStorageRepository productStorageRepository;

    @Override
    public void saveProductStorage(ProductStorage productStorage) {
        productStorageRepository.save(productStorage);
    }

    @Override
    public ProductStorage findProductStorageById(long id) {
        return productStorageRepository.findProductStorageById(id);
    }
}
