package entities;

public class commande {

    int id_commande,telephone,id_user;
    String prenom,nom,adresse,email;
    float prix_totale;
public commande(){

}

    public commande(int id_commande, int telephone, String prenom, String nom, String adresse, String email,float prix_totale,int id_user) {
        this.id_commande = id_commande;
        this.telephone = telephone;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.prix_totale=prix_totale;
        this.id_user=id_user;
    }

    public commande( int id_commande, int telephone, String prenom, String nom, String adresse, String email,int id_user) {
        this.id_commande = id_commande;
        this.telephone = telephone;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.id_user=id_user;
    }
    public commande( int id_commande, int telephone, String prenom, String nom, String adresse, String email) {
        this.id_commande = id_commande;
        this.telephone = telephone;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;

    }

    public commande(int id_commande, int telephone, String adresse) {
    this.id_commande=id_commande;
    this.telephone=telephone;
    this.adresse=adresse;

    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_commande() {
        return id_commande;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public float getPrix_totale() {
        return prix_totale;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrix_totale(float prix_totale) {
        this.prix_totale = prix_totale;
    }

}
