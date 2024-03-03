package services;

import entities.panier;

import java.sql.SQLException;
import java.util.List;

public interface IServiceP <T> {

    public void ajouterProduitPanier(T t)throws SQLException;
    public panier Afficheproduit(int produitId);
   // public List<produit> getAllProducts() ;
    public void SupprimerProduitCommande(int id_produit);
    public List<panier> getAllProductsForCommand(int commandeId) throws SQLException;
    public void AdminSupprimerProduitCommande(int id_commande);
}
