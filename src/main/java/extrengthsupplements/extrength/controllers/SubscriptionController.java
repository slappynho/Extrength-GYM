package extrengthsupplements.extrength.controllers;

import extrengthsupplements.extrength.DTO.SubscriptionDTO;
import extrengthsupplements.extrength.Services.BillSubscriptionServices;
import extrengthsupplements.extrength.Services.ClientServices;
import extrengthsupplements.extrength.Services.SubscriptionServices;
import extrengthsupplements.extrength.Utils.EmailSenderService;
import extrengthsupplements.extrength.Utils.PdfSubGenerator;
import extrengthsupplements.extrength.models.BillSubscription;
import extrengthsupplements.extrength.models.Client;
import extrengthsupplements.extrength.models.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SubscriptionController {
    @Autowired
    private SubscriptionServices subscriptionServices;
    @Autowired
    private ClientServices clientServices;

    @Autowired
    private BillSubscriptionServices billSubscriptionServices;

    //@Autowired
    //EmailSenderService senderEmail;

    @GetMapping("/subscriptions")
    public List<SubscriptionDTO> getSubscriptions(){
        return subscriptionServices.findAllSubscriptions().stream().map(SubscriptionDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/subscription/{id}")
    public SubscriptionDTO getSubscription(@PathVariable Long id){
        return new SubscriptionDTO(subscriptionServices.findSubscriptionById(id));
    }
    @PostMapping("/subscriptions")
    public ResponseEntity<Object> purchaseSubscription(Authentication authentication, @RequestParam Long id, @RequestParam boolean paymentAuthorization) throws MessagingException {
        if (paymentAuthorization == false){
            return new ResponseEntity<>("v", HttpStatus.FORBIDDEN);
        }
        Client client = clientServices.findByEmail(authentication.getName());
        if (client == null){
            return new ResponseEntity<>("a", HttpStatus.FORBIDDEN);
        }
        Subscription subscription = subscriptionServices.findSubscriptionById(id);
        if (subscription == null){
            return new ResponseEntity<>("sdasdas", HttpStatus.FORBIDDEN);
        }
        if (client.getBillSubscription() != null){
            return new ResponseEntity<>("You already have a subscription", HttpStatus.FORBIDDEN);
        }
        BillSubscription billSubscription = new BillSubscription(client, subscription);
        client.setClientSubscription(true);
        client.setBillSubscription(billSubscription);
        billSubscriptionServices.saveBillSubscription(billSubscription);
        clientServices.saveClient(client);
        System.out.println("done!");

        PdfSubGenerator.createBillSusbcription(billSubscription);
        //senderEmail.sendMailWithAttachment(client.getEmail(),"Bill Subscription PDF","Susbcription type::"+billSubscription.getSubscription().getSubscriptionTypes(),"/tmp/billSusbcription.pdf");
        return new ResponseEntity<>("Subscription added", HttpStatus.CREATED);
    }
}
