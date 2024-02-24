package Entities;


public class Conseil {
    public int id_conseil ;
    public String nom_conseil;
    public String video;
    public String description;
    public int id_produit ;
    public int id_typeC ;


    public Conseil(){}
    public Conseil(int id_conseil , String nom_conseil , String video, String description , int id_produit, int id_typeC)
    {
        this.id_conseil = id_conseil ;
        this.nom_conseil = nom_conseil ;
        this.video = video ;
        this.description = description ;
        this.id_produit = id_produit ;
        this.id_typeC = id_typeC ;
    }
    public Conseil( String nom_conseil , String video, String description , int id_produit, int id_typeC)
    {
        this.nom_conseil = nom_conseil ;
        this.video = video ;
        this.description = description ;
        this.id_produit = id_produit ;
        this.id_typeC = id_typeC ;
    }

    public Integer getId_conseil() {
        return id_conseil;
    }

    public void setId_conseil(int id_conseil) {
        this.id_conseil = id_conseil;
    }

    public String getNom_conseil() {
        return nom_conseil;
    }

    public void setNom_conseil(String nom_conseil) {
        this.nom_conseil = nom_conseil;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public Integer getId_typeC() {
        return id_typeC;
    }

    public void setId_typeC(int id_typeC) {
        this.id_typeC = id_typeC;
    }

    @Override
    public String toString() {
        return "Conseil{" +
                "id_conseil=" + id_conseil +
                ", nom_conseil='" + nom_conseil + '\'' +
                ", video='" + video + '\'' +
                ", description='" + description + '\'' +
                ", id_produit=" + id_produit +
                ", id_typeC=" + id_typeC +
                '}'+ "\n";
    }
}
