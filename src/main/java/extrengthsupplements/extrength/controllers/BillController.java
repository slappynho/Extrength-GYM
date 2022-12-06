package extrengthsupplements.extrength.controllers;

import extrengthsupplements.extrength.DTO.BillDTO;
import extrengthsupplements.extrength.Services.BillServices;
import extrengthsupplements.extrength.Services.ClientServices;
import extrengthsupplements.extrength.Services.ProductServices;
import extrengthsupplements.extrength.Services.ProductStorageServices;
import extrengthsupplements.extrength.Utils.BillNumbers;
import extrengthsupplements.extrength.Utils.EmailSenderService;
import extrengthsupplements.extrength.Utils.PdfGenerator;
import extrengthsupplements.extrength.models.Bill;
import extrengthsupplements.extrength.models.Client;
import extrengthsupplements.extrength.models.Product;
import extrengthsupplements.extrength.models.ProductStorage;
import extrengthsupplements.extrength.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BillController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ClientServices clientServices;
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ProductStorageServices productStorageServices;
    @Autowired
    private BillServices billServices;

    @Autowired
    private EmailSenderService senderEmail;

    @GetMapping("/bills")
    public List<BillDTO> getBills(){
        return billRepository.findAll().stream().map(BillDTO::new).collect(Collectors.toList());
    }


    @PostMapping("/purchase")
    public ResponseEntity<Object> purchase (@RequestParam List<Long> ids, @RequestParam boolean paymentAuthorization, Authentication authentication) throws Exception {
        Client client = clientServices.findByEmail(authentication.getName());
        if (client == null){
            return new ResponseEntity<>("Client wasn't found", HttpStatus.FORBIDDEN);
        }
        if (!paymentAuthorization){
            return new ResponseEntity<>("Payment unauthorized", HttpStatus.FORBIDDEN);
        }
        if (ids.isEmpty()){
            return new ResponseEntity<>("missing product", HttpStatus.FORBIDDEN);
        }
        List<Product> products = ids.stream().map(id -> productServices.findProductById(id)).collect(Collectors.toList());
        double amount = products.stream().map(Product::getPrice).reduce(0.0, Double::sum);

        Bill bill  = new Bill(client, amount, BillNumbers.generateBillNumber());
        billServices.saveBill(bill);
        Set<ProductStorage> productStorages = new HashSet<>();

        for (Long id: ids){
            Product product1 = productServices.findProductById(id);
            if (product1.getStock() < 0) {
                return new ResponseEntity<>("Product " + product1.getName() + "doesn't have stock", HttpStatus.FORBIDDEN);
            }
            product1.setStock(product1.getStock()-1);

            ProductStorage productStorage = new ProductStorage(product1, bill);

            productServices.saveProduct(product1);
            productStorageServices.saveProductStorage(productStorage);
            productStorages.add(productStorage);

        }

        bill.setProductStorage(productStorages);
        billServices.saveBill(bill);

        BillDTO billDTO = new BillDTO(bill);
        PdfGenerator.createBill(ids, billDTO,client,productServices);
        //senderEmail.sendMailWithAttachment(client.getEmail(), "Purchase PDF","Invoce number:"+ billDTO.getNumber(),"c:/temp/bill.pdf");
        return new ResponseEntity<>("Purchase success", HttpStatus.CREATED);
    }
}
