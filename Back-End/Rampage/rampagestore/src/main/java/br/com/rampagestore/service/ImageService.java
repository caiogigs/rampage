package br.com.rampagestore.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    public String saveImage(MultipartFile image) {
        if(!image.isEmpty()){
            String imageName = image.getOriginalFilename();
            String fileExtension = "";
            if (imageName != null && imageName.contains(".")) {
                fileExtension = imageName.substring(imageName.lastIndexOf("."));    
            }
            String uniqueImageName = UUID.randomUUID().toString() + fileExtension;
            try{
                //Criando pasta de uplogad
                String imagesFolder = "C:/Users/Secretaria/Desktop/Back-end/Rampage/rampagestore/images";
                File dir = new File(imagesFolder);
                if(!dir.exists()){
                    dir.mkdirs();
                }

            //Criar arquivo no diretorio 
            File serverFile = new  File(dir.getAbsolutePath() + File.separator + uniqueImageName);
            BufferedOutputStream stream  = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(image.getBytes());
            stream.close();
            return serverFile.getAbsolutePath();
            }catch(Exception e){
                System.out.println("Erro ao carregar arquivo: " +imageName+" =>"+e.getMessage());
            }
        }else{
            System.out.println("Erro ao carregar arquivo. Arquivo enviado Vazaio");
        }
        return null;
    }
    
}
