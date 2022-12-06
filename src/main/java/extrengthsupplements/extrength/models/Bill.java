package extrengthsupplements.extrength.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "bill",fetch = FetchType.EAGER)
    private Set<ProductStorage> productStorages;


    private String number;

    private double amount;

    public Bill(){}

    public Bill(Client client, double amount, String number ) {
        this.client = client;
        this.amount = amount;
        this.number= number;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public Set<ProductStorage> getProductStorage() {
        return productStorages;
    }

    public void setProductStorage(Set<ProductStorage> productStorage) {

        this.productStorages = productStorage;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
