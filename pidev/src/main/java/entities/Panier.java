package entities;

public class Panier {
  int id_panier,id_user,id_produit;
  String etat;


    public Panier(int id_panier, int id_user, int id_produit, String etat) {
        this.id_panier = id_panier;
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.etat = etat;
    }
    public Panier(){

    }
    public Panier(int id_user, int id_produit, String etat) {

        this.id_user = id_user;
        this.id_produit = id_produit;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "panier{" +
                "id_panier=" + id_panier +
                ", id_user=" + id_user +
                ", id_produit=" + id_produit +
                ", etat=" + etat +
                '}';
    }

    public int getId_panier() {
        return id_panier;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_produit() {
        return id_produit;
    }

    public String getEtat() {
        return etat;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
