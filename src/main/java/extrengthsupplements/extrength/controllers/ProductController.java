package extrengthsupplements.extrength.controllers;

import extrengthsupplements.extrength.DTO.ProductDTO;
import extrengthsupplements.extrength.Services.ClientServices;
import extrengthsupplements.extrength.Services.ImgFileServices;
import extrengthsupplements.extrength.Services.ProductServices;
import extrengthsupplements.extrength.models.Client;
import extrengthsupplements.extrength.models.Product;
import extrengthsupplements.extrength.models.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ImgFileServices imgFIleServices;
    @Autowired
    private ClientServices clientServices;

    @GetMapping("/products")
    public Set<ProductDTO> getProducts(){
        return productServices.findAllProducts().stream().filter(product -> product.isProductActive() && product.getStock()>0).map(ProductDTO::new).collect(Collectors.toSet());
    }
    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestParam String name,@RequestParam String description, @RequestParam ProductCategory category, @RequestParam double price,
                                                @RequestParam int stock, @RequestParam("files") MultipartFile file,  Authentication authentication){
        Client newCurrentClient = clientServices.findByEmail(authentication.getName());
        if (name.isEmpty() || category == null || price == 0.0 || stock == 0 ){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }if(newCurrentClient == null){
            return new ResponseEntity<>("Client not found", HttpStatus.FORBIDDEN);
        }


//        List<String> fileNames = new ArrayList<>();
//
//        try {
//
//            Arrays.asList(files).stream().forEach(file -> {
//
//                imgFIleServices.save(file);
//
//                fileNames.add(file.getOriginalFilename());
//
//
//            });
//
//        } catch (Exception e){
//
//            return new ResponseEntity<>("Not upload", HttpStatus.EXPECTATION_FAILED);
//
//        }


        if (file == null){

            Product product = new Product(name, description, category, price, stock);

            productServices.saveProduct(product);

            return  new ResponseEntity<>("Product Added", HttpStatus.CREATED);

        }
        try {


            imgFIleServices.save(file);

            Product product = new Product(name, description, "/web/assets/images/products/" + file.getOriginalFilename(), category, price, stock,true);


            productServices.saveProduct(product);


            return new ResponseEntity<>("Uploaded", HttpStatus.CREATED);

        } catch (Exception e){

            return new ResponseEntity<>("Not upload", HttpStatus.EXPECTATION_FAILED);


        }
    }

    @PatchMapping("/products")
    public ResponseEntity<Object> disableProduct(@RequestParam long id, Authentication authentication){
        Client client = clientServices.findByEmail(authentication.getName());
        Product product = productServices.findProductById(id);
        if (product == null){
            return new ResponseEntity<>("Product not found", HttpStatus.FORBIDDEN);
        }

        product.setProductActive(false);
        productServices.saveProduct(product);
        return new ResponseEntity<>("Product disabled",HttpStatus.ACCEPTED);

    }
}