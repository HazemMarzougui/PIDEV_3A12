package Services;

import Entities.Conseil;
import Entities.Review;
import Utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewService implements IServiceReview<Review> {

    private Connection connection ;

    public ReviewService()
    {
        connection = MyDB.getInstance().getConnection();
    }


    @Override
    public void ajouterReview(Review review) throws SQLException {
        String req = "INSERT INTO review (comments , id_conseil) VALUES (?,?)";
        try (PreparedStatement prepareStatement = connection.prepareStatement(req)) {
            // Setting the parameters for the prepared statement
            prepareStatement.setInt(2, review.getId_conseil());
            // Executing the update operation
            prepareStatement.executeUpdate();
        }
    }

    @Override
    public List<Review> displayReview() throws SQLException {
        List<Review> reviews = new ArrayList<>();

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM review");

        while (res.next()){
            Review review = new Review();
            review.setId_review(res.getInt(1));
            review.setId_conseil(res.getInt(2));

            reviews.add(review);
        }
        return reviews;
    }

    @Override
    public int ReviewCount() throws SQLException {
        int count = 0;
        String req = "SELECT COUNT(*) AS total_reviews FROM review";

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(req);

            if (resultSet.next()) {
                count = resultSet.getInt("total_reviews");
            }
        }
        return count;
    }
}
