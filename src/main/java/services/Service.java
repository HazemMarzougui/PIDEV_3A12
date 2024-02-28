package services;

import java.sql.SQLException;
import java.util.List;

import entities.commande;

public interface Service<commande> {

    public void ajoutercommande(commande commande) throws SQLException;

    public List<commande> getAllCommand() throws SQLException;
    public void SupprimerProduitCommande(int id_commande);
    public commande getOneCommmande(int idcommande) throws SQLException;
    public void modifierCommande(entities.commande commande , int idcommande) throws SQLException;

}