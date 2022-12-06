package extrengthsupplements.extrength.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private Set<ProductStorage> productsStorage;

    private String name;

    @Column(name="description",length=1000)
    private String description;

    private String urlImg;

    private ProductCategory productCategory;

    private double price;

    private int stock;

    private boolean productActive = true;


    public Product (){}

    public Product(String name, String description, ProductCategory productCategory, double price, int stock) {
        this.name = name;
        this.description= description;
        this.productCategory = productCategory;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, String description, String urlImg, ProductCategory productCategory, double price, int stock, boolean productActive) {
        this.name = name;
        this.description = description;
        this.urlImg = urlImg;
        this.productCategory = productCategory;
        this.price = price;
        this.stock = stock;
        this.productActive = productActive;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImg(){return urlImg;}

    public void setUrlImg(String urlImg) {this.urlImg = urlImg;}

    public ProductCategory getProductCategory() {return productCategory;}

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Set<ProductStorage> getProductsStorage() {
        return productsStorage;
    }

    public void setProductsStorage(ProductStorage productStorage) {
        productStorage.setProduct(this);
        this.productsStorage.add(productStorage);
    }

    public boolean isProductActive() {
        return productActive;
    }
    public void setProductActive(boolean productActive) {
        this.productActive = productActive;
    }
}
