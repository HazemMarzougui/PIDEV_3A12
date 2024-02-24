package entities;
import java.util.Date;
public class offre {


    public class Offre {
        private int idOffre;
        private int montantRemise;
        private Date dateDebut;
        private Date dateFin;
        private String description;
        private int idEvenementOffre;
        private int idProduitOffre;

        // Constructors, getters, and setters
    }

    public class Evenement {
        private int idEvenement;

        public int getIdEvenement() {
            return idEvenement;
        }

        public void setIdEvenement(int idEvenement) {
            this.idEvenement = idEvenement;
        }
    }

    public class Produit {
        private int idProduit;

    }

}
