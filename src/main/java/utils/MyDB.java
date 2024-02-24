package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {
    final String url="jdbc:mysql://localhost:3306/pidev";
    final String username="root";
    final String pwd="";
    private Connection conx;

    public static MyDB instance;


    public static MyDB getInstance(){
        if (instance == null)
            instance = new MyDB();
        return instance;

    }
    MyDB(){

        try {
            conx = DriverManager.getConnection(url, username, pwd);
            System.out.println("connected successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }



    }

    public Connection getConx() {
        return conx;
    }
}
