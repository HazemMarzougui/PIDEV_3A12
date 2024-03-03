package services;
import entities.commande;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class commandeService implements Service<commande> {

    private Connection con;

    public commandeService() {
        con = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajoutercommande(commande commande) throws SQLException {

        String req = "insert into commande (idcommande,prenom,nom,adresse,telephone,email,prix_totale,id_user)" +
                "values ('" + commande.getId_commande() + "','" + commande.getPrenom() + "','" + commande.getNom() + "','" + commande.getAdresse() + "','" + commande.getTelephone() + "','" + commande.getEmail() + "','" + commande.getPrix_totale() + "','" + commande.getId_user() + "')";
        Statement ste = con.createStatement();


        ste.executeUpdate(req);
    }


    @Override
    public List<commande> getAllCommand() throws SQLException {
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

    public List<commande> getCommandUser(int id_user) throws SQLException {
        List<commande> pers = new ArrayList<>();

        String req = "SELECT * FROM commande WHERE id_user=? ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id_user);
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

    public void SupprimerProduitCommande(int idcommande) {
        try {
            String req = "DELETE FROM `commande` WHERE idcommande=?  ";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, idcommande);

            ps.executeUpdate();
            System.out.println("produit deleted successfully from cart");
            ps.close();
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la supression de produit de la cart : " + e.getMessage());
        }


    }




    public commande getOneCommmande(int idcommande) throws SQLException {

        String req = "SELECT * FROM `commande` where idcommande= ?";
        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, idcommande);

        ResultSet rs = ps.executeQuery();
        commande commande = new commande();

        while (rs.next()) {
            commande.setId_commande(rs.getInt("idcommande"));
            commande.setPrenom(rs.getString("prenom"));
            commande.setNom(rs.getString("nom"));
            commande.setAdresse(rs.getString("adresse"));
            commande.setTelephone(rs.getInt("telephone"));
            commande.setEmail(rs.getString("email"));

        }
        ps.close();
        return commande;
    }


    public void modifierCommande(commande commande ,int idcommande) throws SQLException {
        String req = "UPDATE commande SET  adresse=?, telephone=?  WHERE idcommande=?";
        PreparedStatement pre = con.prepareStatement(req);


        pre.setString(1,commande.getAdresse());
        pre.setInt(2,commande.getTelephone());
        pre.setInt(3,commande.getId_commande());

        pre.executeUpdate();


    }





}

