package Services;
import Entities.Categorie;
import Entities.Conseil;
import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieService  implements IServiceCategorie<Categorie>{

    private Connection connection ;

    public CategorieService()
    {
        connection = MyDB.getInstance().getConnection();
    }
    @Override
    public void ajouterCategorie(Categorie categorie) throws SQLException
    {
        // SQL query to insert art data into the database
        String req = "INSERT INTO categorie ( nom_categorie ) VALUES (?)";

        // Using try-with-resources to ensure proper resource management
        try (PreparedStatement prepareStatement = connection.prepareStatement(req)) {
            // Setting the parameters for the prepared statement
            prepareStatement.setString(1, categorie.getNom_categorie());

            // Executing the update operation
            prepareStatement.executeUpdate();
            displayCategorie();
        }
    }

    @Override
    public void modifierCategorie(Categorie categorie, int id_Categorie) throws SQLException
    {
        String req = "UPDATE categorie SET nom_categorie=? WHERE id_categorie=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, categorie.getNom_categorie());
        pre.setInt(2,id_Categorie);

        pre.executeUpdate();
    }

    @Override
    public void deleteCategorie(int id_Categorie)throws SQLException
    {
        String req = "delete  from categorie where id_categorie=?";
        PreparedStatement pre = connection.prepareStatement(req);

        pre.setInt(1,id_Categorie);
        int rowsAffected = pre.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("art deleted with success.");
        } else {
            System.out.println("Attention !! no one have this  id .");
        }
    }

    @Override
    public List<Categorie> displayCategorie() throws SQLException
    {
        List<Categorie> categories = new ArrayList<>();

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM categorie");

        while (res.next()){
            Categorie categorie = new Categorie();
            categorie.setId_categorie(res.getInt(1));
            categorie.setNom_categorie(res.getString(2));
            categories.add(categorie);
        }
        return categories;
    }
    @Override
    public String getCategoriesName(int idCategory) throws SQLException {
        String categoryName = ""; // Default value
        String req = "SELECT nom_categorie FROM categorie WHERE id_categorie = ?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, idCategory);
            try (ResultSet res = pre.executeQuery()) {
                if (res.next()) {
                    categoryName = res.getString(1);
                }
            }
        }
        return categoryName;
    }


}
