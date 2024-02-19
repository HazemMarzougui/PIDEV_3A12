package entities;

public class commande {
    int idcommande, telephone;
    String nom, adresse, email, prenom;
public  commande(){

}
    public commande(int idcommande, String prenom, String nom, String adresse, int telephone, String email) {
        this.idcommande = idcommande;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;


    }
    public commande( String prenom, String nom,String adresse, int telephone,  String email ) {

        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }

    public int getIdcommande() {
        return idcommande;
    }

    public int getTelephone() {
        return telephone;
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

    public String getPrenom() {
        return prenom;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
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

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "commande{" +
                "idcommande=" + idcommande +
                ", telephone=" + telephone +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", email='" + email + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}