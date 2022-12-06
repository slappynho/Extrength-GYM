package extrengthsupplements.extrength.controllers;

import extrengthsupplements.extrength.DTO.ClientDTO;
import extrengthsupplements.extrength.Services.ClientServices;
import extrengthsupplements.extrength.Utils.EmailSenderService;
import extrengthsupplements.extrength.models.Client;
import extrengthsupplements.extrength.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ClientController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    ClientServices clientServices;

    @Autowired
    EmailSenderService emailSenderService;

    @GetMapping("/clients")
    public List<ClientDTO> getClients(){
        return clientServices.getAllClients().stream().map(ClientDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){
        return new ClientDTO(clientServices.findClientById(id));
    }

    @GetMapping("/clients/current")
    public ClientDTO getClient(Authentication authentication){
        Client client = clientServices.findByEmail(authentication.getName());
        return new ClientDTO(client);
    }

    @GetMapping("/clients/list")
    public List<ClientDTO> getClientDTO() {
      return  clientServices.getAllClients().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/client/activation/{id}")
    public RedirectView verification(@PathVariable Long id){
        Client client = clientServices.findClientById(id);
        if (client == null){
            return new RedirectView("/web/login.html?confirm=false");
        }
        client.setVerification(true);
        clientServices.saveClient(client);
        return new RedirectView("/web/login.html?confirm=true");
    }

    @PostMapping("/clients")
    public ResponseEntity<Object> register(@RequestParam String firstName, @RequestParam String lastName,
                                           @RequestParam String email, @RequestParam String password)

    {
        if (firstName.isEmpty()){

            return new ResponseEntity<>("Missing Data: FirstName", HttpStatus.FORBIDDEN);


        }
        if (lastName.isEmpty()){

            return new ResponseEntity<>("Missing Data: lastName", HttpStatus.FORBIDDEN);


        }
        if (email.isEmpty() ){

            return new ResponseEntity<>("Missing Data: email", HttpStatus.FORBIDDEN);


        }
        if (password.isEmpty()){

            return new ResponseEntity<>("Missing Data: password", HttpStatus.FORBIDDEN);


        }

        if (clientRepository.findByEmail(email) != null){

            return new ResponseEntity<>("This email already use",HttpStatus.FORBIDDEN);

        }
        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password), false);

        clientRepository.save(client);

        //emailSenderService.sendEmail(client.getEmail(),"Activacion de cuenta","https://extrength.railway.com/api/client/activation/" + client.getId());

        return new ResponseEntity<>("Create", HttpStatus.CREATED);

    }

}
