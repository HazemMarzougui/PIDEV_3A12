package entities;

public class panier  {

    int id_panier,id_produit,quantite,id_commande;
    float prix_u;

    public panier(){
    }

    public panier(int id_panier, int id_produit, int quantite, float prix_u,int id_commande) {
        this.id_panier = id_panier;
        this.id_produit = id_produit;
        this.quantite = quantite;
        this.prix_u = prix_u;
        this.id_commande=id_commande;
    }
    public panier( int id_produit,int id_commande, int quantite, float prix_u) {

        this.id_produit = id_produit;
        this.quantite = quantite;
        this.prix_u = prix_u;
        this.id_commande=id_commande;
    }

    public int getId_panier() {
        return id_panier;
    }

    public int getId_produit() {
        return id_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public float getPrix_u() {
        return prix_u;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setPrix_u(float prix_u) {
        this.prix_u = prix_u;
    }

    @Override
    public String toString() {
        return "panier{" +
                "id_panier=" + id_panier +
                ", id_produit=" + id_produit +
                ", quantite=" + quantite +
                ", id_commande=" + id_commande +
                ", prix_u=" + prix_u +
                '}';
    }
}
