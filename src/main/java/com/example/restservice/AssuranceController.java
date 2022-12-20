/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.restservice;



import java.util.HashMap;
import materiels.Assurance;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AssuranceController  {

	@GetMapping("/assurances/{remaining}")
	public HashMap getAssurances(@PathVariable int remaining) throws Exception{
            Assurance assurance = new Assurance();
            HashMap<String,Object> map = new HashMap();
            
            int nb_jour = remaining*30;
            map.put("data", assurance.findAll(nb_jour));
            return map;
	}
       
       
       
}