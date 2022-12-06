package extrengthsupplements.extrength.Services;

import extrengthsupplements.extrength.DTO.ItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public interface PaymentServiceMP {
    ResponseEntity<Object> getPaymentUrl(List<ItemDTO> itemDTOList);

}