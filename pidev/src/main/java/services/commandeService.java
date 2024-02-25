package services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.commande;
import utils.MyDB;

public class commandeService implements Service<commande> {

    private Connection con;

    public commandeService()
    {
        con = MyDB.getInstance().getConnection();
    }
    @Override
    public void ajoutercommande(commande commande) throws SQLException {

        String req = "insert into commande (idcommande,prenom,nom,adresse,telephone,email,prix_totale)"+
                "values ('"+commande.getId_commande()+"','"+commande.getPrenom()+"','"+commande.getNom()+"','"+commande.getAdresse()+ "','"+commande.getTelephone()+"','"+commande.getEmail()+"','"+commande.getPrix_totale()+"')";
        Statement ste = con.createStatement();


        ste.executeUpdate(req);
    }


    @Override
    public List<commande> getAllCommand() throws SQLException{
        List<commande> pers = new ArrayList<>();

        String req = "select * from commande";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res = pre.executeQuery();
        while (res.next()) {
            commande c = new commande();
            c.setId_commande(res.getInt(1));
            c.setNom(res.getString(3));
            c.setAdresse(res.getString(4));
            c.setTelephone(res.getInt(5));
            c.setEmail(res.getString(6));
            c.setPrix_totale(res.getFloat(7));



              pers.add(c);
        }


        return pers;


    }



}

