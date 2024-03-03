package services;

import java.sql.SQLException;
import java.util.List;
public interface Service<T> {

    public void ajoutercommande(T t) throws SQLException;

    public List<T> getAllCommand() throws SQLException;
    public void SupprimerProduitCommande(int id_commande);
    public T getOneCommmande(int idcommande) throws SQLException;
    public void modifierCommande(T t , int idcommande) throws SQLException;

}