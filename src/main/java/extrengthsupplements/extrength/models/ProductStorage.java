package extrengthsupplements.extrength.models;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
public class ProductStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    private Bill bill;


    public ProductStorage(){

    }


    public ProductStorage(Product product, Bill bill){
        this.product = product;
        this.bill = bill;
    }


    public long getId() {
        return id;
    }

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;

    }
}
