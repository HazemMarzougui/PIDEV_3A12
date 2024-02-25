package services;

import java.sql.SQLException;
import java.util.List;

import entities.commande;

public interface Service<commande> {

    public void ajoutercommande(commande commande) throws SQLException;

    public List<commande> getAllCommand() throws SQLException;

}