package entities;

import java.util.Date;

public class Evenement {
    private int id_evenement;
    private String nom_event ;
    private Date date_debut;
    private Date date_fin;
    private String description;
    private int clientId;

    // Constructors
    public Evenement(int id_evenement,String nom_event , Date date_debut, Date date_fin, String description) {
        this.id_evenement = id_evenement;
        this.nom_event=nom_event;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }
    public Evenement() {
    }

    public Evenement(String nom_event, Date date_debut, Date date_fin, String description) {
        this.nom_event=nom_event;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }

    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }


    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    // Getters and Setters
    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method


    @Override
    public String toString() {
        return "Evenement{" +
                "id_evenement=" + id_evenement +
                ", nom_event='" + nom_event + '\'' +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", description='" + description + '\'' +
                '}';
    }
}
