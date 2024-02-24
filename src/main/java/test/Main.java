package test;

import entities.Evenement;
import services.Service_evenement;
import utils.MyDB;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MyDB conn1 = MyDB.getInstance();

        Evenement evenement1 = new Evenement("BLACK2",new Date(), new Date(), "Description 1");
        Evenement evenement2 = new Evenement("",new Date(), new Date(), "Description 2");
        Evenement evenement3 = new Evenement("",new Date(), new Date(), "Description 3");

        Service_evenement service = new Service_evenement();

        try {
            // Add Evenements
            service.ajouter(evenement1);
            service.ajouter(evenement2);
            service.ajouter(evenement3);
        } catch (SQLException e) {
            System.out.println("Error adding evenements: " + e.getMessage());
        }

        try {
            // Display Evenements
            List<Evenement> evenements = service.afficher();
            System.out.println("List of Evenements:");
            for (Evenement e : evenements) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving evenements: " + e.getMessage());
        }

        // Modify Evenement
        evenement3.setDescription("Updated description");

        try {
            service.modifier(evenement3);
            System.out.println("Evenement updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating evenement: " + e.getMessage());
        }

        try {
            // Delete Evenement
            service.supprimer(evenement2);
            System.out.println("Evenement deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting evenement: " + e.getMessage());
        }
    }
}
