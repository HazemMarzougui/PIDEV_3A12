package Test;

import Entities.Conseil;
import Services.ConseilService;
import Utils.MyDB;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException
    {
        /*
        MyDB conn1 = MyDB.getInstance();
        ConseilService conseilService = new ConseilService();


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Ajouter Conseil");
            System.out.println("2. Afficher Conseil");
            System.out.println("3. Modifier Conseil");
            System.out.println("4. Supprimer Conseil");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add Conseil
                    Conseil Newconseil = getCONSEILInput();
                    try {
                        conseilService.ajouterConseil(Newconseil);
                        System.out.println("Conseil added successfully!");
                    } catch (SQLException e) {
                        System.out.println("Error adding Conseil: " + e.getMessage());
                    }
                    break;
                case 2:
                    // Display Conseil
                    try {
                        System.out.println(conseilService.displayConseil());
                    } catch (SQLException e) {
                        System.out.println("Error displaying Conseils: " + e.getMessage());
                    }
                    break;
                case 3:
                    // Update Conseil
                    System.out.print("Enter Conseil ID to update: ");
                    int ConseilIdToUpdate = scanner.nextInt();
                    Conseil updatedConseil = getCONSEILInput();
                    try {
                        conseilService.modifierConseil(updatedConseil, ConseilIdToUpdate);
                        System.out.println("Conseil updated successfully!");
                    } catch (SQLException e) {
                        System.out.println("Error updating Conseil: " + e.getMessage());
                    }

                    break;
                case 4:
                    // Delete ART
                    System.out.print("Enter Conseil ID to delete: ");
                    int ConseilIdToDelete = scanner.nextInt();
                    try {
                        conseilService.deleteConseil(ConseilIdToDelete);
                        System.out.println("Conseil deleted successfully!");
                    } catch (SQLException e) {
                        System.out.println("Error deleting Conseil: " + e.getMessage());
                    }
                    break;
                case 5:
                    // Exit
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }


    private static Conseil getCONSEILInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Donner votre Nom Conseil: ");
        String nomConseil = scanner.next();
        System.out.print("Donner votre video: ");
        String Video = scanner.next();
        System.out.print("Donner votre Description: ");
        String Description = scanner.next();
        System.out.print("Donner votre idUser: ");
        int idUser = scanner.nextInt();
        System.out.print("Donner votre idProduit: ");
        int idProduit = scanner.nextInt();
        System.out.print("Donner votre idTypeC: ");
        int idTypeC = scanner.nextInt();

        return new Conseil( nomConseil, Video, Description, idUser, idProduit,idTypeC);
    }*/



}}
