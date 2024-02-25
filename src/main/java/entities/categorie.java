package entities;

public class categorie {
    private int id_categorie;
    private String nom_categorie;

    public int getid_categorie() {
        return id_categorie;
    }

    public void setid_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "categorie{" +
                "id_categorie=" + id_categorie +
                ", nom_categorie='" + nom_categorie + '\'' +
                '}';
    }

    public String getnom_categorie() {
        return nom_categorie;
    }

    public void setnom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public categorie(int id_categorie, String nomCategorie) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
    }

    public categorie() {
    }

    public categorie(String nomCategorie) {
        this.nom_categorie = nom_categorie;
    }
}
