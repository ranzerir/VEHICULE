/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.restservice;

import java.util.ArrayList;
import materiels.Avion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RestController
public class AvionController  {

    	@GetMapping("/avions")
	public ArrayList getListAvion() throws Exception{
            Avion v = new Avion();

            return v.findAll();
	}
        
	@GetMapping("/avions/{id}")
	public Avion getAvion(@PathVariable int id) throws Exception{
            Avion v = new Avion();
            return v.findById(id);
	}
       
       /*@PostMapping("/avions") 
       public Avion saveVehicule(@RequestBody Avion newVehicule) throws Exception{
           
            return newVehicule.save();
	}*/
       
       @PutMapping("/avions/{id}") 
       public Avion updateVehicule(@RequestBody Avion vehicule,@PathVariable int id) {

            return vehicule;
	}
       
}
