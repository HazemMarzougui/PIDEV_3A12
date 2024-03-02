package Entities;

public class Produit {

    public int id_produit ;
    public String nom_produit ;

    public Produit(){}

    public Produit(int id_produit , String nom_produit)
    {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
    }
    public Produit( String nom_produit)
    {
        this.nom_produit = nom_produit;
    }

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
