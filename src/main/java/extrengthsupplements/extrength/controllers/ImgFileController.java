package extrengthsupplements.extrength.controllers;

import extrengthsupplements.extrength.Services.ImgFileServices;
import extrengthsupplements.extrength.models.ImgFile;
import extrengthsupplements.extrength.repositories.ImgFIleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;



@RestController
@RequestMapping("/api")
public class ImgFileController {
    @Autowired
    private ImgFileServices imgFileServices;
    @Autowired
    private ImgFIleRepository imgFIleRepository;

    @PostMapping("/upload")
    public ResponseEntity<Object> setImg(@RequestParam("file") MultipartFile multipartFile){
        try {
            imgFileServices.save(multipartFile);
            return new ResponseEntity<>("Uploaded", HttpStatus.CREATED);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Not upload", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/images")
    public List<ImgFile> getListFiles() {
        return imgFIleRepository.findAll();
    }
}
