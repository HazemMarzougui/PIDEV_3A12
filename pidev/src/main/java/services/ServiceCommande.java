package services;
import utils.MyDB;
import entities.commande;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceCommande implements IServiceC <commande> {
    private Connection con;

    public ServiceCommande() {
        con = MyDB.getInstance().getConnection();

    }

    @Override
    public void ajouter(commande commande) throws SQLException {

        String req = "insert into commande (prenom,nom,adresse,telephone,email)"+
                "values ('"+commande.getPrenom()+"','"+commande.getNom()+"','"+commande.getAdresse()+ "','"+commande.getTelephone()+"','"+commande.getEmail()+"')";
        Statement ste = con.createStatement();


        ste.executeUpdate(req);


    }

    @Override
    public void modifier(commande commande, int idcommande) throws SQLException {


        String req = "UPDATE commande SET prenom=? , nom=? , adresse=?,telephone=?,email=? WHERE idcommande=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, commande.getPrenom());
        pre.setString(2, commande.getNom());
        pre.setString(3, commande.getAdresse());
        pre.setInt(4, commande.getTelephone());
        pre.setString(5, commande.getEmail());
        pre.setInt(6,commande.getIdcommande());

        pre.executeUpdate();

    }

    @Override
    public void supprimer(int idcommande) throws SQLException {
        String req = "DELETE FROM commande WHERE idcommande=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, idcommande);

        int rowsAffected = pre.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("commande supprimé avec succès.");
        } else {
            System.out.println("Aucun commande trouvé avec l'ID spécifié.");
        }

    }


    @Override
    public List<commande> afficher() throws SQLException {
        List<commande> pers = new ArrayList<>();

        String req = "select * from commande";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res= pre.executeQuery();
        while (res.next()){
            commande c = new commande();
            c.setIdcommande(res.getInt(1));
            c.setPrenom(res.getString(2));
            c.setNom(res.getString(3));
            c.setAdresse(res.getString(4));
            c.setTelephone(res.getInt(5));
            c.setEmail(res.getString(6));
            pers.add(c);
        }


        return pers;
    }
}
