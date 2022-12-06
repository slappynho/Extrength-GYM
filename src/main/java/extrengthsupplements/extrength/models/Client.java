package extrengthsupplements.extrength.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy = "client")
    private Set<Bill> bills = new HashSet<>();

    @ManyToMany(mappedBy = "clients")
    private Set<Workout> workouts = new HashSet<>();


    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToOne
    private BillSubscription billSubscription;

    private boolean clientSubscription ;

    private boolean verification = false;

    public Client(){}

    public Client(String firstName, String lastName, String email, String password, boolean clientSubscription) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.clientSubscription= clientSubscription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isClientSubscription() {
        return clientSubscription;
    }

    public void setClientSubscription(boolean clientSubscription) {
        this.clientSubscription = clientSubscription;
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public Set<Workout> getWorkouts() {
        return workouts;
    }

    public void addWorkouts(Workout workout) {
        this.workouts.add(workout);
    }

    public BillSubscription getBillSubscription() {
        return billSubscription;
    }

    public void setBillSubscription(BillSubscription billSubscription) {
        this.billSubscription = billSubscription;
    }

    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }
}
