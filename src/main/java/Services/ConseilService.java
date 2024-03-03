package Services;
import Entities.Categorie;
import Entities.Conseil;
import Entities.Review;
import Utils.MyDB;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConseilService  implements IService<Conseil>{

    private Connection connection ;

    public ConseilService()
    {
        connection = MyDB.getInstance().getConnection();
    }
    @Override
    public void ajouterConseil(Conseil conseil) throws SQLException
    {
        // SQL query to insert art data into the database
        String req = "INSERT INTO conseil ( nom_conseil , video , description , id_produit , id_typeC) VALUES ( ?, ?, ?, ?, ?)";

        // Using try-with-resources to ensure proper resource management
        try (PreparedStatement prepareStatement = connection.prepareStatement(req)) {
            // Setting the parameters for the prepared statement
            prepareStatement.setString(1, conseil.getNom_conseil());
            prepareStatement.setString(2, conseil.getVideo());
            prepareStatement.setString(3, conseil.getDescription()); // Corrected index from 4 to 3
            prepareStatement.setInt(4, conseil.getId_produit());
            prepareStatement.setInt(5, conseil.getId_typeC());

            // Executing the update operation
            prepareStatement.executeUpdate();
            displayConseil();
        }
    }

    @Override
    public void modifierConseil(Conseil conseil, int idConseil) throws SQLException
    {
        String req = "UPDATE conseil SET nom_conseil=?, video=?, description=?, id_produit=?, id_typeC=? WHERE id_conseil=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, conseil.getNom_conseil());
        pre.setString(2, conseil.getVideo());
        pre.setString(3, conseil.getDescription());
        pre.setInt(4, conseil.getId_produit());
        pre.setInt(5, conseil.getId_typeC());
        pre.setInt(6,idConseil);

        pre.executeUpdate();
    }

    @Override
    public void deleteConseil(int id_conseil)throws SQLException
    {
        String req = "delete  from conseil where id_conseil=?";
        PreparedStatement pre = connection.prepareStatement(req);

        pre.setInt(1,id_conseil);
        int rowsAffected = pre.executeUpdate();
        if (rowsAffected > 0) {

        } else {
            System.out.println("Attention !! no one have this  id .");
        }
    }

    @Override
    public List<Conseil> displayConseil() throws SQLException
    {
        List<Conseil> conseils = new ArrayList<>();

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM conseil");

        while (res.next()){
            Conseil conseil1 = new Conseil();
            conseil1.setId_conseil(res.getInt(1));
            conseil1.setNom_conseil(res.getString(2));
            conseil1.setVideo(res.getString(3));
            conseil1.setDescription(res.getString(4));
            conseil1.setId_produit(res.getInt(5));
            conseil1.setId_typeC(res.getInt(6));
            conseils.add(conseil1);
        }
        return conseils;
    }

    public List<Conseil> searchProducts(String search) {
        List<Conseil> conseilList = new ArrayList<>();
        try {
            String query = "SELECT * FROM conseil WHERE nom_conseil LIKE ? OR id_typeC LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parcours du résultat de la requête
            while (resultSet.next()) {
                Conseil conseil = new Conseil();
                conseil.setId_conseil(resultSet.getInt("id_conseil"));
                conseil.setNom_conseil(resultSet.getString("nom_conseil"));
                conseil.setVideo(resultSet.getString("video"));
                conseil.setDescription(resultSet.getString("description"));
                conseil.setId_produit(resultSet.getInt("id_produit"));
                conseil.setId_typeC(resultSet.getInt("id_typeC"));

                conseilList.add(conseil);
            }
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conseilList;
    }

    @Override
    public List<Conseil> sortConseilByNom() throws SQLException {
        List<Conseil> conseilList = new ArrayList<>();
        String req = "SELECT * FROM conseil ORDER BY nom_conseil ASC";
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(req);
        while (res.next())
        {
            Conseil conseil = new Conseil();
            conseil.setId_conseil(res.getInt(1));
            conseil.setNom_conseil(res.getString(2));
            conseil.setVideo(res.getString(3));
            conseil.setDescription(res.getString(4));
            conseil.setId_produit(res.getInt(5));
            conseil.setId_typeC(res.getInt(6));
        }
        return conseilList;
    }

    @Override
    public Conseil getconseilByID(int idConseil) throws SQLException {
        Conseil conseil = new Conseil();
        String req = "SELECT * FROM conseil WHERE id_conseil = ?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, idConseil);
        ResultSet res = pre.executeQuery();
        while (res.next()) {
            conseil.setId_conseil(res.getInt(1));
            conseil.setNom_conseil(res.getString(2));
            conseil.setVideo(res.getString(3));
            conseil.setDescription(res.getString(4));
            conseil.setId_produit(res.getInt(5));
            conseil.setId_typeC(res.getInt(6));
        }
        return conseil;
    }

    @Override
    public int ConseilNumbers() throws SQLException {
        int count = 0;
        String req = "SELECT COUNT(*) AS total_conseils FROM conseil";

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(req);

            if (resultSet.next()) {
                count = resultSet.getInt("total_conseils");
            }
        }
        return count;
    }

    public Categorie getCategoriesName(int idCategory) throws SQLException {
        Categorie categorie = new Categorie();
        String req = "SELECT nom_categorie FROM categorie WHERE id_categorie = ?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, idCategory);
            try (ResultSet res = pre.executeQuery()) {
                if (res.next()) {
                    categorie.setNom_categorie(res.getString("nom_categorie"));
                }
            }
        }
        return categorie;
    }

    @Override
    public Conseil getLastAddedConseil() throws SQLException {
        Conseil conseil = new Conseil();
        String req = "SELECT * FROM conseil ORDER BY dateCreation DESC LIMIT 1";

        try (Statement stmt = connection.createStatement(); ResultSet res = stmt.executeQuery(req)) {
            while (res.next()) {
                conseil.setDateCreation(res.getTimestamp("dateCreation"));
            }
        }

        return conseil;
    }

    public Map<Integer, Long> getConseilCountByCategory() throws SQLException {
        Map<Integer, Long> conseilCounts = new HashMap<>();
        String req = "SELECT id_typeC, COUNT(id_conseil) as conseil_count FROM conseil GROUP BY id_typeC";

        try (Statement stmt = connection.createStatement(); ResultSet res = stmt.executeQuery(req)) {
            while (res.next()) {
                int idCategorie = res.getInt("id_typeC");
                long conseilCount = res.getLong("conseil_count");
                conseilCounts.put(idCategorie, conseilCount);
            }
        }

        return conseilCounts;
    }

    public int getTotalConseilsReviews(int id_conseil) {
        int total = 0;
        try {
            // Ensure you have a valid and open database connection (the 'connection' variable)
            if (connection != null && !connection.isClosed()) {
                String query = "SELECT count(*) FROM review WHERE id_conseil = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, id_conseil);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            total = resultSet.getInt(1);
                        }
                    }
                }
            } else {
                // Handle the case when the database connection is not valid or closed
                System.err.println("Database connection is not valid or closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    public int getTotalConseilReviewsByStar(int id_conseil, int value) {
        int total = 0;
        try {
            String query = "SELECT count(*) FROM review WHERE id_conseil = ? and value = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_conseil);
            preparedStatement.setInt(2, value);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                total = resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public List<Review> getAllComments(int id_conseil) {
        List<Review> reviewList = new ArrayList<>();
        try {
            String query = "SELECT * FROM review WHERE id_conseil = ? ORDER BY id_review DESC ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_conseil);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parcours du résultat de la requête
            while (resultSet.next()) {
                Review review = new Review();
                review.setId_review(resultSet.getInt("id_review"));
                review.setTitle(resultSet.getString("titile"));
                review.setComments(resultSet.getString("comments"));
                review.setValue(resultSet.getInt("value"));
                review.setId_conseil(resultSet.getInt("id_conseil"));
                review.setDateCreation(resultSet.getTimestamp("dateCreation"));

                reviewList.add(review);
            }
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewList;

    }

    public void addReview(Review review) {
        try {
            String req = "INSERT INTO `review`(`titile`, `comments`,`value`, `id_conseil` ) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, review.getTitle());
            ps.setString(2, review.getComments());
            ps.setInt(3, review.getValue());
            ps.setInt(4, review.getId_conseil());
            ps.executeUpdate();
            System.out.println("reviews added successfully");
            ps.close();
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la récupération du review : " + e.getMessage());
        }
    }

    public double getAverageRatingForConseil(int conseilId) {
        double averageRating = 0.0;
        try {
            // Ensure you have a valid and open database connection (the 'connection' variable)
            if (connection != null && !connection.isClosed()) {
                String query = "SELECT AVG(value) FROM review WHERE id_conseil = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, conseilId);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            averageRating = resultSet.getDouble(1);
                        }
                    }
                }
            } else {
                // Handle the case when the database connection is not valid or closed
                System.err.println("Database connection is not valid or closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageRating;
    }



}