package entities;

public class produit {

  int id_produit,prix,quantite;
  String nom;

    public produit() {
        this.id_produit = id_produit;
        this.prix = prix;
        this.quantite = quantite;
        this.nom = nom;
    }
    public produit( int prix, int quantite, String nom) {

        this.prix = prix;
        this.quantite = quantite;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "produit{" +
                "id_produit=" + id_produit +
                ", prix=" + prix +
                ", quantite=" + quantite +
                ", nom='" + nom + '\'' +
                '}';
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
