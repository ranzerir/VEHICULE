/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import connectivity.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import model.Entite;

/**
 *
 * @author Mendrika
 */
public class LoginDao {
    
    public static String get_rand_token(){
        String salta="abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder lop=new StringBuilder();
        Random rnd=new Random();
        while(lop.length()<45){
            int index=(int) (rnd.nextInt(34));
            lop.append(salta.charAt(index));
        }
        String saltStr=lop.toString();
        return saltStr;
    }
    public static Entite findById(String token)throws Exception{
        Statement stmt=null;
        Timestamp time=null;
        String ssql="select * from Entite where token like '"+token+"'";
        Connection con=new Connexion().getConnect();
        stmt=con.createStatement();
        System.out.println(ssql);
        Entite n=null;
        ResultSet fin=stmt.executeQuery(ssql);
        while(fin.next()){
            n=new Entite(fin.getString("token"),fin.getTimestamp("date_expiration"));
        }
        return n;
    }
    public static void update(String token,String random,LocalDateTime local)throws Exception{
        Statement stmt=null;
        Connection con=new Connexion().getConnect();
        try{
            String sql="update Entite set token ="+random+", date_expiration="+local+"  where token like '"+token+"'";
            stmt=con.createStatement();
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }
        finally{
            if(con!=null){
                con.close();
            }
            if(stmt!=null){
                stmt.close();
            }
        }
    }
    public static Entite update_first(String email,String pass)throws Exception{
        PreparedStatement stmt=null;
        Entite ve=null;
        Connection con=new Connexion().getConnect();
        try{
            String sql="update Entite set token ='"+LoginDao.get_rand_token()+"', date_expiration='"+LocalDateTime.now().plusDays(1)+"'  where passwords like '"+pass+"' and email like '"+email+"'";
            stmt=con.prepareStatement(sql);
            System.out.println(sql);
            stmt.execute();
            String sql1="select * from Entite";
            stmt=con.prepareStatement(sql1);
            ResultSet fin=stmt.executeQuery();
            
            while(fin.next()){
                System.out.println(fin.getString(2)+"    fzefezfzef   "+fin.getString(3));
                if(fin.getString(2).compareTo(email)==0 && fin.getString(3).compareTo(pass)==0){
                    ve=new Entite();
                    ve.setId(fin.getInt(1));
                    ve.setName(fin.getString(2));
                    ve.setPassword(fin.getString(3));
                    ve.setToken(fin.getString(4));
                }
            }

        }
        finally{
            if(con!=null){
                con.close();
            }
            if(stmt!=null){
                stmt.close();
            }
        }
        return ve;
    }
}
