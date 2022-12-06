package extrengthsupplements.extrength.Services;

import extrengthsupplements.extrength.models.Client;

import java.util.List;

public interface ClientServices {

    void saveClient(Client client);

    public List<Client> getAllClients();

    Client findClientById(long id);

    Client findByEmail(String email);


}
