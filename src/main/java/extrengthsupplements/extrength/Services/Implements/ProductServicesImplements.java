package extrengthsupplements.extrength.Services.Implements;

import extrengthsupplements.extrength.Services.ProductServices;
import extrengthsupplements.extrength.models.Product;
import extrengthsupplements.extrength.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServicesImplements implements ProductServices {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findProductById(long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product findByName(String name){return productRepository.findByName(name);}

    @Override
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsById(List<Long> ids){return productRepository.findAllById(ids);}
}
