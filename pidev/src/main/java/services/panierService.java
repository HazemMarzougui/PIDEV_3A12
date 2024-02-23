package services;

import test.MainFX;
import utils.MyDB;

import java.sql.*;

import entities.panier;

import java.util.ArrayList;
import java.util.List;
import entities.produit;
public class panierService implements IServiceP<panier> {

    private Connection con;

    public panierService()
    {
        con = MyDB.getInstance().getConnection();
    }



    @Override
    public void ajouterProduitPanier(panier p) throws SQLException {
        String req = "insert into panier (id_produit,id_commande,prix_u,quantite)"+
                "values ('"+p.getId_produit()+"','"+p.getId_commande()+"','"+p.getPrix_u()+"','"+p.getQuantite()+ "')";
        Statement ste = con.createStatement();


        ste.executeUpdate(req);
    }


    public  panier Afficheproduit(int produitId)  {
         panier panier=null;

        try {
            String req = "SELECT * FROM `panier` where id_produit = ?  ";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, produitId);


            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                 panier = new panier();
                panier.setId_panier(rs.getInt("id_panier"));
                panier.setId_produit(rs.getInt("id_produit"));


            }
            ps.close();

        }catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la récupération du commande : " + e.getMessage());
        }
        return panier;
    }




    public List<produit> getAllProducts() {
        List<produit> panierList = new ArrayList<>();
        try {
            // Create a comma-separated string of product IDs for the SQL query
            StringBuilder idBuilder = new StringBuilder();
            for (int i = 0; i < MainFX.GlobalData.produits.size(); i++) {
                idBuilder.append("?");
                if (i < MainFX.GlobalData.produits.size() - 1) {
                    idBuilder.append(",");
                }
            }
            String query = "SELECT * FROM produit WHERE id_produit IN (" + idBuilder.toString() + ")";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            // Set product IDs as parameters
            for (int i = 0; i < MainFX.GlobalData.produits.size(); i++) {
                preparedStatement.setInt(i + 1, MainFX.GlobalData.produits.get(i));
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                produit panier = new produit();
                panier.setId_produit(resultSet.getInt("id_produit"));
                panier.setNom(resultSet.getString("nom"));
                panier.setQuantite(resultSet.getInt("quantite"));
                panier.setPrix(resultSet.getInt("prix"));
                panierList.add(panier);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return panierList;
    }



    public void SupprimerProduitCommande(int id_produit){
        try {
            String req = "DELETE FROM `panier` WHERE id_produit=?  ";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id_produit);

            ps.executeUpdate();
            System.out.println("produit deleted successfully from cart");
            ps.close();
        }catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la supression de produit de la cart : " + e.getMessage());
        }
    }

}
