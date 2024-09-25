package br.com.rampagestore.control;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.rampagestore.service.ImageService;

@RestController
@RequestMapping("/rampage/imagen")
public class ImageController {

    @Autowired
    private ImageService imageService;

    private String diretory = "F:/PIStrint2/Back-end/Rampage/rampagestore/images";
    
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file){
        try{
            String filePath = imageService.saveImage(file);

            return ResponseEntity.ok("Imagem enviada com sucesso " + filePath); 
        }catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Falha ao enviar a imagem: "+ e.getMessage());
        }
    }

}
