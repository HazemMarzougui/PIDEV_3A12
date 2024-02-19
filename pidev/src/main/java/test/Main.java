package test;

import entities.*;
import services.ServiceCommande;
import services.ServicePanier;
import utils.MyDB;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        MyDB conn1 = MyDB.getInstance();


         commande p1 = new commande("sdfgs","fsdf","fs",6531,"dgdf");
        Panier p3 = new Panier(123,55 , 0,"confirme");
        Panier p4 = new Panier(50900907,11111 , "confirme");

        ServicePanier s = new ServicePanier();
        ServiceCommande C =new ServiceCommande();
       try {
            C.ajouter(p1);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            System.out.println(s.afficher());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        /*try {
            s.modifier(p4);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        /*try {
            s.supprimer(p4);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/



    }
}
