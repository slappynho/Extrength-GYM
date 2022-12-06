package extrengthsupplements.extrength.DTO;

import extrengthsupplements.extrength.models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<BillDTO> bills;

    private BillSubscriptionDTO billSubscription;

    private Set<WorkoutDTO> exersices;

    private boolean clientSubscription;

    public ClientDTO(Client client) {

        this.id = client.getId();

        this.firstName = client.getFirstName();

        this.lastName = client.getLastName();

        this.email = client.getEmail();

        this.bills = client.getBills().stream().map(BillDTO::new).collect(Collectors.toSet());

        this.exersices = client.getWorkouts().stream().map(WorkoutDTO::new).collect(Collectors.toSet());

        if (client.getBillSubscription() != null){
            System.out.println(client.getBillSubscription());
            this.billSubscription = new BillSubscriptionDTO(client.getBillSubscription());
        }

        this.clientSubscription = client.isClientSubscription();
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<BillDTO> getBills() {
        return bills;
    }

    public BillSubscriptionDTO getBillSubscription() {
        return billSubscription;
    }

    public Set<WorkoutDTO> getWorkouts() {
        return exersices;
    }

    public boolean isClientSubscription() {
        return clientSubscription;
    }
}

