package entities;
import java.util.Date;
public class user {

    private int id_user;
    private String nom;
    private String prenom;
    private String email;
    private double number;
    private String mdp;
    private String type;


    public user() {
    }

    public user(int id_user, String nom, String prenom, String email, double number, String mdp, String type) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.number = number;
        this.mdp = mdp;
        this.type = type;
    }

    public user(String nom, String prenom, String email, double number, String mdp, String type) {

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.number = number;
        this.mdp = mdp;
        this.type = type;
    }

    public int getId_user() {
        return id_user;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }



    public void setType(String type) {
        this.type = type;
    }
}
