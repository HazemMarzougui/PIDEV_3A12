package services;

import entities.Produit;
import utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service_produit implements IService_offre<Produit> {
    private Connection con;
    public Service_produit(){
        try {
            con = MyDB.getInstance().getConnection();
            if (con == null) {
                throw new SQLException("Failed to obtain database connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., log the error and exit gracefully
        }
    }

    @Override
    public void ajouter(Produit produit) throws SQLException {

    }

    @Override
    public void modifier(Produit produit) throws SQLException {

    }

    @Override
    public void supprimer(Produit produit) throws SQLException {

    }

    @Override
    public List<Produit> afficher() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String req = "SELECT id_produit, nom_produit, prix, quantite, description FROM produit";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res = pre.executeQuery();
        while (res.next()) {
            Produit produit = new Produit();
            produit.setId_produit(res.getInt("id_produit"));
            produit.setNom_produit(res.getString("nom_produit"));
            produit.setPrix(res.getInt("prix"));
            produit.setQuantite(res.getInt("quantite"));
            produit.setDescription(res.getString("description"));
            produits.add(produit);
        }
        return produits;
    }
}
