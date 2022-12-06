package extrengthsupplements.extrength.Services.Implements;

import extrengthsupplements.extrength.DTO.ClientDTO;
import extrengthsupplements.extrength.Services.ClientServices;
import extrengthsupplements.extrength.models.Client;
import extrengthsupplements.extrength.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServicesImplements implements ClientServices {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void saveClient(Client client) {

    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findClientById(long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
}
