package extrengthsupplements.extrength.Services;

import extrengthsupplements.extrength.models.Product;

import java.util.List;

public interface ProductServices {
    public void saveProduct(Product product);
    public Product findProductById(long id);

    public Product findByName(String name);

    public List<Product> findAllProductsById(List<Long> ids);

    public List<Product> findAllProducts();
}
