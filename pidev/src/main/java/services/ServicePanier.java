package services;

import entities.Panier;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePanier implements IService<Panier>{

  private Connection con;
  public  ServicePanier(){ con = MyDB.getInstance().getConnection();
  }




    public  void ajouter(Panier panier) throws SQLException {
        String req = "insert into panier (etat,id_user,id_produit)"+
                "values ('"+panier.getEtat()+"','"+panier.getId_user()+"',"+panier.getId_produit()+")";
        Statement ste = con.createStatement();
        ste.executeUpdate(req);
    }


    public void modifier(Panier panier ,int idpanier) throws SQLException {
        String req = "UPDATE panier SET etat=? , id_user=? , id_produit=? WHERE id_panier=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,panier.getEtat());
        pre.setInt(2,panier.getId_user());
        pre.setInt(3,panier.getId_produit());
        pre.setInt(4,panier.getId_panier());

        pre.executeUpdate();


    }


    public void supprimer(int idpanier) throws SQLException {
        String req = "DELETE FROM panier WHERE id_panier=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, idpanier);

        int rowsAffected = pre.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Panier supprimé avec succès.");
        } else {
            System.out.println("Aucun panier trouvé avec l'ID spécifié.");
        }
    }


    public List<Panier> afficher() throws SQLException {
        List<Panier> pers = new ArrayList<>();

        String req = "select * from panier";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res= pre.executeQuery();
        while (res.next()){
            Panier p = new Panier();
            p.setId_panier(res.getInt(1));
            p.setEtat(res.getString("etat"));
            p.setId_user(res.getInt(3));
            p.setId_produit(res.getInt(4));
            pers.add(p);
        }


        return pers;
    }





}
