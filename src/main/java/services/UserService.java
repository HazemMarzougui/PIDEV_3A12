package services;

import entities.user;
import utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private Connection con;

    public UserService() {
        try {
            con = MyDB.getInstance().getConnection();
            if (con == null) {
                throw new SQLException("Failed to obtain database connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., log the error and exit gracefully
        }
    }

    public user getUserById(int userId) throws SQLException {
        user foundUser = null;
        String query = "SELECT * FROM user WHERE id_user = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve user data from the result set and create a user object
                int id = resultSet.getInt("id_user");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                double number = resultSet.getDouble("number");
                String mdp = resultSet.getString("mdp");
                String type = resultSet.getString("type");

                foundUser = new user(id, nom, prenom, email, number, mdp, type);
            }
        }

        return foundUser;
    }
}
