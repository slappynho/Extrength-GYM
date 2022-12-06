package extrengthsupplements.extrength.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private List<Client> clients;

    private String name;

    private String coachName;

    @Column(name="description",length=1000)
    private String description;

    private String fromDate;

    private String fromTime;


    public Workout(){}

    public Workout(String name,String coachName, String description, String fromDate, String fromTime ) {
        this.name = name;
        this.coachName = coachName;
        this.description = description;
        this.fromDate = fromDate;
        this.fromTime = fromTime;

    }


    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getCoachName() {
        return coachName;
    }
    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromDate() {return fromDate;}

    public void setFromDate(String fromDate) {this.fromDate = fromDate;}

    public String getFromTime() {return fromTime;}

    public void setFromTime(String fromTime) {this.fromTime = fromTime;}

    public List<Client> getClients() {
        return clients;
    }

    public void addClients(Client client) {
        this.clients.add(client);
    }
}