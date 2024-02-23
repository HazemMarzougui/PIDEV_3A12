package services;

import entities.panier;
import entities.produit;
import utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class produitService implements Iservice<produit>{

    private Connection con;

    public produitService()
    {
        con = MyDB.getInstance().getConnection();
    }


    @Override
    public  List<produit> getAllProducts() throws SQLException{
        List<produit> pers = new ArrayList<>();

        String req = "select * from produit";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res = pre.executeQuery();
        while (res.next()) {
            produit p = new produit();
            p.setId_produit(res.getInt(1));
            p.setNom(res.getString(2));
            p.setPrix(res.getInt(3));
            p.setQuantite(res.getInt(4));


            pers.add(p);
        }


        return pers;


    }



}