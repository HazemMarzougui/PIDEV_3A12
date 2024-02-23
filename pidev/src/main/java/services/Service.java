package services;

import java.sql.SQLException;
import entities.commande;

public interface Service<commande> {

    public void ajoutercommande(commande commande)throws SQLException;
}
