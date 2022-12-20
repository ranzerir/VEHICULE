/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.restservice;


import materiels.*;
import dao.LoginDao;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import model.Entite;
import model.FormWrapper;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Mendrika
 */
@RestController
@CrossOrigin("*")
public class Login {

    @RequestMapping(value = "/login",method=RequestMethod.GET)
    public ResponseEntity<Object> get_name(@RequestParam(name="email")String mail,@RequestParam(name="password")String password) {
        Entite ee=null;
        try{
            ee=LoginDao.update_first(mail, password);
//            ee=LoginDao.get_employer(mail, password);
            System.out.println("huzehfuizuehfiuez   "+ee);
            if(ee!=null){
                return new ResponseEntity<>(ee, HttpStatus.OK);
            }
            else{
                System.out.println("Vitaaa");
                return new ResponseEntity<>("{\"message\": \"utilisateur inexistant\" }", HttpStatus.OK);
            }
        }
        catch(Exception aaa){
            aaa.printStackTrace();
            return new ResponseEntity<>("{\"message\": \""+aaa.getMessage()+"\"}", HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/logout",method=RequestMethod.GET)
    public ResponseEntity<Object> logout(@RequestParam(name="token")String mail) {
        Entite ee=null;
        try{
             LoginDao.update(mail,null,null);
        }
        catch(Exception aaa){
            aaa.printStackTrace();
            return new ResponseEntity<>("{\"message\": \""+aaa.getMessage()+"\"}", HttpStatus.OK);
        }
        return new ResponseEntity<>("{\"message\": \"Logout succes\"}", HttpStatus.OK);
    }
    private void saveUploadedFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            File myObj = new File("serveur/"+file.getOriginalFilename());
            myObj.createNewFile();
            Path path = Paths.get(myObj.toURI());
            Files.write(path, bytes);
            InputStream input = new FileInputStream(myObj);
            BufferedImage originalImage = ImageIO.read(input);
            Image newResizedImage = originalImage.getScaledInstance(300, 100, Image.SCALE_SMOOTH);
            String s = myObj.getAbsolutePath();
            String fileExtension = s.substring(s.lastIndexOf(".") + 1);
            ImageIO.write(convertToBufferedImage(newResizedImage),
                    fileExtension, myObj);
        }
    }
    public static BufferedImage convertToBufferedImage(Image img) {;

        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bi = new BufferedImage(
                img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = bi.createGraphics();
        graphics2D.drawImage(img, 0, 0, null);
        graphics2D.dispose();

        return bi;
    }
        @RequestMapping(value = "/api/render/file",method=RequestMethod.GET)
        public ResponseEntity<Object> multiUploadFileModel() throws IOException{
                File b =new File("serveur/");
                File[] tab=b.listFiles();
                byte[][] pae=new byte[tab.length][];
                List<String> encodedString=new ArrayList<>();
                for(int i=0;i<tab.length;i++){
                    pae[i] = Files.readAllBytes(tab[i].toPath());   
                    encodedString.add(Base64.getEncoder().encodeToString(pae[i]));

                }
            return new ResponseEntity<>(encodedString, HttpStatus.OK);
        }        
    @RequestMapping(value = "/api/upload/file",method=RequestMethod.POST)
    public ResponseEntity<Object> multiUploadFileModel(@RequestParam("File") MultipartFile file) {
            // Save as you want as per requiremens0
            
                System.out.println(file.getOriginalFilename()+"    cccccc     ");
            try{
                if(file!=null){
                    saveUploadedFile(file);        
                }
            }
            catch(IOException ae){
                ae.printStackTrace();
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);                
            }
        return new ResponseEntity<>("Successfully uploaded!", HttpStatus.OK);
    }   
}
