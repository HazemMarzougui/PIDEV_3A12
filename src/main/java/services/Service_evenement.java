package services;

import entities.Evenement;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service_evenement implements IService_evenement<Evenement> {

    private Connection con;

    public Service_evenement(){
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
    public void ajouter(Evenement evenement) throws SQLException {
        String req = "INSERT INTO evenement (nom_event, date_debut, date_fin, description) VALUES (?, ?, ?, ?)";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setString(1,evenement.getNom_event());
        pre.setDate(2, new Date(evenement.getDate_debut().getTime()));
        pre.setDate(3, new Date(evenement.getDate_fin().getTime()));
        pre.setString(4, evenement.getDescription());
        pre.executeUpdate();
    }

    @Override
    public void modifier(Evenement evenement) throws SQLException {
        String req = "UPDATE evenement SET nom_event=?, date_debut=?, date_fin=?, description=? WHERE id_evenement=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, evenement.getNom_event()); // Set nom_event
        pre.setDate(2, new Date(evenement.getDate_debut().getTime()));
        pre.setDate(3, new Date(evenement.getDate_fin().getTime()));
        pre.setString(4, evenement.getDescription());
        pre.setInt(5, evenement.getId_evenement());
        pre.executeUpdate();
    }

    @Override
    public void supprimer(Evenement evenement) throws SQLException {
        String req = "DELETE FROM evenement WHERE id_evenement=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, evenement.getId_evenement());
        pre.executeUpdate();
    }

    @Override
    public List<Evenement> afficher() throws SQLException {
        List<Evenement> evenements = new ArrayList<>();
        String req = "SELECT id_evenement, nom_event, date_debut, date_fin, description FROM evenement";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res = pre.executeQuery();
        while (res.next()) {
            Evenement e = new Evenement();
            e.setId_evenement(res.getInt("id_evenement"));
            e.setNom_event(res.getString("nom_event"));
            e.setDate_debut(res.getDate("date_debut"));
            e.setDate_fin(res.getDate("date_fin"));
            e.setDescription(res.getString("description"));
            evenements.add(e);
        }
        return evenements;
    }

}
