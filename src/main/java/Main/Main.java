/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.Main to edit this template
 */
package Main;

import connectivity.Connexion;
import java.sql.Connection;
import java.util.List;
import materiels.Assurance;
import materiels.Avion;

/**
 *
 * @author Mendrika
 */
public class Main {
    public static void main(String[] args) {
        try{
            Assurance v=new Assurance();
            List<Assurance> p=v.findAll(1);        
//            Connection con=new Connexion().getConnect();
        }
        catch( Exception ee){
            ee.printStackTrace();
        }
    }
}
