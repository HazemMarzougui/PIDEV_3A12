package Services;

import Entities.Categorie;
import Utils.MyDB;
import Entities.Produit;
import Utils.MyDB;
import com.sun.scenario.effect.impl.prism.ps.PPSRenderer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitService {

    private Connection connection ;

    public ProduitService()
    {
        connection = MyDB.getInstance().getConnection();
    }

    public List<Produit> displayProduit() throws SQLException
    {
        List<Produit> produits = new ArrayList<>();

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM produits");

        while (res.next()){
            Produit produit = new Produit();
            produit.setId_produit(res.getInt(1));
            produit.setNom_produit(res.getString(2));
            produits.add(produit);
        }
        return produits;
    }
    public Produit getProduitsName(int idProduit) throws SQLException {
        Produit produit =new Produit();
        String req = "SELECT nom_produit FROM produits WHERE id_produit = ?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, idProduit);
            try (ResultSet res = pre.executeQuery()) {
                if (res.next()) {
                   produit.setNom_produit(res.getString("nom_produit"));
                }
            }
        }
        return produit;
    }

}
