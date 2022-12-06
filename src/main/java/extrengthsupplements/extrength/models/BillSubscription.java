package extrengthsupplements.extrength.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
public class BillSubscription {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;




    public BillSubscription(){}

    public BillSubscription(Client client, Subscription subscription) {
        this.client = client;
        this.subscription = subscription;
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }


}
