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
import javax.servlet.MultipartConfigElement;
import model.Entite;
import model.FormWrapper;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.unit.DataSize;
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
    
    
    public static void resize(String inputImagePath,
            String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
 
    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public static void resize(String inputImagePath,
            String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }
    private void saveUploadedFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            File myObj = new File("serveur/"+file.getOriginalFilename());
            myObj.createNewFile();
            Path path = Paths.get(myObj.toURI());
            Files.write(path, bytes);
//            BufferedImage bimg = ImageIO.read(myObj);
//            int width          = bimg.getWidth();
//            int height         = bimg.getHeight(); 
//            int pp=(int) Files.size(path);
//            System.out.println(width+"      "+height+"      "+pp);
//            int ss=(int) (pp/1000000);
//            int scaledWidth = width/ss;
//            int scaledHeight = height/ss;
//            Login.resize("serveur/"+file.getOriginalFilename(), "serveur/"+file.getOriginalFilename(), scaledWidth, scaledHeight);
        }
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
        @Bean
        public MultipartConfigElement multipartConfigElement() {
            MultipartConfigFactory factory = new MultipartConfigFactory();
            DataSize rr=DataSize.ofMegabytes(10000000);
            factory.setMaxFileSize(rr);
            factory.setMaxRequestSize(rr);
            return factory.createMultipartConfig();
        }
}
