package services;

import entities.Offre;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service_offre implements IService_offre<Offre> {

    private Connection con;

    public Service_offre(){
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
    public void ajouter(Offre offre) throws SQLException {
        String req = "INSERT INTO offre (montant_remise, date_debut, date_fin, description, id_evenement_offre, id_produit_offre) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setInt(1, offre.getMontant_remise());
        pre.setDate(2, new Date(offre.getDate_debut().getTime()));
        pre.setDate(3, new Date(offre.getDate_fin().getTime()));
        pre.setString(4, offre.getDescription());
        pre.setInt(5, offre.getId_evenement_offre());
        pre.setInt(6, offre.getId_produit_offre());
        pre.executeUpdate();
    }

    @Override
    public void modifier(Offre offre) throws SQLException {
        String req = "UPDATE offre SET montant_remise=?, date_debut=?, date_fin=?, description=?, id_evenement_offre=?, id_produit_offre=? WHERE id_offre=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, offre.getMontant_remise());
        pre.setDate(2, new Date(offre.getDate_debut().getTime()));
        pre.setDate(3, new Date(offre.getDate_fin().getTime()));
        pre.setString(4, offre.getDescription());
        pre.setInt(5, offre.getId_evenement_offre());
        pre.setInt(6, offre.getId_produit_offre());
        pre.setInt(7, offre.getId_offre());
        pre.executeUpdate();
    }

    @Override
    public void supprimer(Offre offre) throws SQLException {
        String req = "DELETE FROM offre WHERE id_offre=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, offre.getId_offre());
        pre.executeUpdate();
    }

    @Override
    public List<Offre> afficher() throws SQLException {
        List<Offre> offres = new ArrayList<>();
        String req = "SELECT id_offre, montant_remise, date_debut, date_fin, description, id_evenement_offre, id_produit_offre FROM offre";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet res = pre.executeQuery();
        while (res.next()) {
            Offre o = new Offre();
            o.setId_offre(res.getInt("id_offre"));
            o.setMontant_remise(res.getInt("montant_remise"));
            o.setDate_debut(res.getDate("date_debut"));
            o.setDate_fin(res.getDate("date_fin"));
            o.setDescription(res.getString("description"));
            o.setId_evenement_offre(res.getInt("id_evenement_offre"));
            o.setId_produit_offre(res.getInt("id_produit_offre"));
            offres.add(o);
        }
        return offres;
    }
}
