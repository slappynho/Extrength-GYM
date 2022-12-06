package extrengthsupplements.extrength.Services.Implements;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import extrengthsupplements.extrength.DTO.ItemDTO;
import extrengthsupplements.extrength.Services.PaymentServiceMP;
import extrengthsupplements.extrength.Utils.PaymentUtils;
import extrengthsupplements.extrength.models.Preference;
import extrengthsupplements.extrength.models.Product;
import extrengthsupplements.extrength.repositories.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
@Service
public class PaymentServiceMPImplement implements PaymentServiceMP {
    @Autowired
    ProductRepository productRepository;



    @Override
    public ResponseEntity<Object> getPaymentUrl(@NotNull List<ItemDTO> itemDTOList) {
        itemDTOList.forEach(itemDTO -> {
            Product productInfo =  productRepository.findById(Long.parseLong(itemDTO.getId())).orElse(null);
            itemDTO.setDescription(productInfo.getDescription());
            itemDTO.setTitle(productInfo.getName());
            itemDTO.setCurrency_id("ARS");
            itemDTO.setPicture_url("");
            itemDTO.setCategory_id("art");
            itemDTO.setUnit_price( productInfo.getPrice() + 5 * 1.11);

        });
        Preference preference = new Preference(itemDTOList);
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = mapper.writeValueAsString(preference);
            System.out.println(requestBody);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        try {


            String linkResponse = PaymentUtils.getMPUrl(requestBody);
            return new ResponseEntity<Object>(linkResponse ,HttpStatus.CREATED);
        } catch (IOException e) {
            System.out.println(e);
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}