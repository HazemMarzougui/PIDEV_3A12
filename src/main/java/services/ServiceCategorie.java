package services;

import entities.Categorie;
import entities.CrudCategorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Produit;
import utils.MyDB;

public class ServiceCategorie implements CrudCategorie<Categorie> {

    public Connection conx;
    public Statement stm;


    public ServiceCategorie() {
        conx = MyDB.getInstance().getConx();

    }

    @Override
    public void ajouter(Categorie c) {
        String req =
                "INSERT INTO categorie"
                        + "(nom_categorie)"
                        + "VALUES(?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, c.getNom_categorie());
            ps.executeUpdate();
            System.out.println("Categorie Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Categorie c) {
        try {
            String req = "UPDATE categorie SET nom_categorie=? WHERE id_categorie=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(2, c.getId_categorie());
            pst.setString(1, c.getNom_categorie());
            pst.executeUpdate();
            System.out.println("Categorie Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }



    @Override
    public List<Categorie> Show() {
        List<Categorie> list = new ArrayList<>();

        try {
            String req = "SELECT * from categorie";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Categorie(rs.getInt("id_categorie"), rs.getString("nom_categorie")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Categorie getById(int id) {
        Categorie categ = new Categorie();

        try {
            String sql = "SELECT * FROM categorie WHERE id_categorie = ?";
            PreparedStatement pst = conx.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                categ=new Categorie(rs.getInt("id_categorie"), rs.getString("nom_categorie"));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return categ;

    }


    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM categorie WHERE id_categorie=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Categorie suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }
}
