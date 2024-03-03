package entities;

public class Produits {
    private int id_produit;
    private String nom_produit;
    private int prix;
    private int quantite;
    private String description;
    private String image;
    private int id_categorie;
    private int id_offre;

    // Constructors
    public Produits() {
    }

    public Produits(int id_produit, String nom_produit, int prix, int quantite, String description, String image, int id_categorie, int id_offre) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
        this.image = image;
        this.id_categorie = id_categorie;
        this.id_offre = id_offre;
    }

    // Getters and setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }
}
