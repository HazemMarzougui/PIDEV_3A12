package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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


    }

