
package entities;

import java.util.Date;

public class Offre {
    private int id_offre;
    private int montant_remise;
    private Date date_debut;
    private Date date_fin;
    private String description;
    private int id_evenement_offre;
    private int id_produit_offre;

    public Offre() {
        // Default constructor
    }

    public Offre(int id_offre, int montant_remise, Date date_debut, Date date_fin, String description, int id_evenement_offre, int id_produit_offre) {
        this.id_offre = id_offre;
        this.montant_remise = montant_remise;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.id_evenement_offre = id_evenement_offre;
        this.id_produit_offre = id_produit_offre;
    }






    public Offre(int montant_remise, Date date_debut, Date date_fin, String description, int id_evenement_offre, int id_produit_offre) {
        this.montant_remise = montant_remise;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.id_evenement_offre = id_evenement_offre;
        this.id_produit_offre = id_produit_offre;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public int getMontant_remise() {
        return montant_remise;
    }

    public void setMontant_remise(int montant_remise) {
        this.montant_remise = montant_remise;
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

    public int getId_evenement_offre() {
        return id_evenement_offre;
    }

    public void setId_evenement_offre(int id_evenement_offre) {
        this.id_evenement_offre = id_evenement_offre;
    }

    public int getId_produit_offre() {
        return id_produit_offre;
    }

    public void setId_produit_offre(int id_produit_offre) {
        this.id_produit_offre = id_produit_offre;
    }
}
