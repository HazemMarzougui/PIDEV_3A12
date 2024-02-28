package test;


import entities.produit;
import services.produitService;
import utils.MyDB;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args)  {
        MyDB conn1 = MyDB.getInstance();

        produitService ps = new produitService();

        produit p = new produit(54,5,"test");
        try {
            ps.getAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
