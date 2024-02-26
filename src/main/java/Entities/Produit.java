package Entities;

public class Produit {

    private int id_produit ;
    private String nom_produit ;

    public Produit(){}

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    @Override
    public String toString() {
        return  nom_produit ;
    }
}
