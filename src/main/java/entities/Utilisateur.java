package entities;

import java.sql.Date;

public class Utilisateur {
    private int id,tel;
    private String nom,prenom,email,password,token,photo,addresse,description,role;
    private Date date_naiss;
    private boolean is_Actif;

    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

        public Utilisateur(int tel, String nom, String prenom, String email, String password, String token, String photo, String addresse, String description, String role, Date date_naiss, boolean is_Actif) {
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.token = token;
        this.photo = photo;
        this.addresse = addresse;
        this.description = description;
        this.role = role;
        this.date_naiss = date_naiss;
        this.is_Actif = is_Actif;
    }

    public Utilisateur(int id, int tel, String nom, String prenom, String email, String password, String token, String photo, String addresse, String description, String role, Date date_naiss, boolean is_Actif) {
        this.id = id;
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.token = token;
        this.photo = photo;
        this.addresse = addresse;
        this.description = description;
        this.role = role;
        this.date_naiss = date_naiss;
        this.is_Actif = is_Actif;
    }

    public Utilisateur(String nom, String prenom, String email, String password, String role, boolean is_Actif) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.is_Actif = is_Actif;
    }

    public Utilisateur(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }



    public Utilisateur(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Utilisateur(int tel, String nom, String prenom, String addresse, String description, String role, Date date_naiss) {
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.addresse = addresse;
        this.description = description;
        this.role = role;
        this.date_naiss = date_naiss;
    }



    public int getId() {
        return id;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoto() {
        return photo;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }

    public boolean isIs_Actif() {
        return is_Actif;
    }

    public void setIs_Actif(boolean is_Actif) {
        this.is_Actif = is_Actif;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", tel=" + tel + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", token=" + token + ", photo=" + photo + ", addresse=" + addresse + ", description=" + description + ", role=" + role + ", date_naiss=" + date_naiss + ", is_Actif=" + is_Actif + '}';
    }

    
    
}
