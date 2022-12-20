/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.restservice;



import java.util.HashMap;
import materiels.Kilometrage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KilometrageController  {

	@GetMapping("/kilometrages/{id}")
	public HashMap getKilometrageVehicule(@PathVariable int id) throws Exception{
            Kilometrage kilometrage = new Kilometrage();
            HashMap<String,Object> map = new HashMap();
            map.put("data", kilometrage.findById(id));
            return map;
	}
       
       @PostMapping("/kilometrages") 
       public Kilometrage saveKilometrageVehicule(@RequestBody Kilometrage kilometrage) throws Exception{
           
            return kilometrage.save();
	}
       
       
}
