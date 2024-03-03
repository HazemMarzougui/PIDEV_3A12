package services;

import entities.CrudProduit;
import entities.Produit;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceProduit implements CrudProduit<Produit> {
    public Connection conx;
    public Statement stm;


    public ServiceProduit() {
        conx = MyDB.getInstance().getConnection();

    }

    @Override
    public void ajouter(Produit p) {
        String req =
                "INSERT INTO produit"
                        + "(nom_produit,prix,quantite,description,image,id_categorie,id_offre)"
                        + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, p.getNom_produit());
            ps.setInt(2, p.getPrix());
            ps.setInt(3, p.getQuantite());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getImage());
            ps.setInt(6, p.getId_categorie());
            ps.setInt(7, p.getId_offre());
            ps.executeUpdate();
            System.out.println("Produit Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Produit p) {
        try {
            String req = "UPDATE produit SET nom_produit=?, prix=?, quantite=?, description=?, image=?, id_categorie=?, id_offre=? WHERE id_produit=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(8, p.getId_produit());
            pst.setString(1, p.getNom_produit());
            pst.setInt(2, p.getPrix());
            pst.setInt(3, p.getQuantite());
            pst.setString(4, p.getDescription());
            pst.setString(5, p.getImage());
            pst.setInt(6, p.getId_categorie());
            pst.setInt(7, p.getId_offre());
            pst.executeUpdate();
            System.out.println("Produit Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }



    @Override
    public List<Produit> Show() {
        List<Produit> list = new ArrayList<>();

        try {
            String req = "SELECT * from produit";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Produit(rs.getInt("id_produit"), rs.getString("nom_produit"),
                        rs.getInt("prix"), rs.getInt("quantite"),
                        rs.getString("description"), rs.getString("image"),
                        rs.getInt("id_categorie"),rs.getInt("id_offre")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }


    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM produit WHERE id_produit=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Produit suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    public List<Produit> TriNom() {
        List<Produit> list1 = new ArrayList<>();
        List<Produit> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getNom_produit().compareTo(o2.getNom_produit())).collect(Collectors.toList());
        return list1;
    }

    public List<Produit> TriPrix() {
        List<Produit> list1 = new ArrayList<>();
        List<Produit> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getNom_produit().compareTo(o2.getNom_produit())).collect(Collectors.toList());
        return list1;
    }
}
