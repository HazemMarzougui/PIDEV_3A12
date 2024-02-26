package Services;
import Entities.Conseil;
import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println("Conseil Deleted.");
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

    public List<Conseil> sortProductsUser(String sortBy, String comboBoxData) {
        List<Conseil> conseilList = new ArrayList<>();
        try {
            String query = "";
            PreparedStatement preparedStatement;

            if (sortBy.equals("Nom_Conseil")) {
                if (comboBoxData.equals("Nom Conseil - ASC To DESC")) {
                    query = "SELECT * FROM conseil ORDER BY nom_conseil asc";
                } else if (comboBoxData.equals("Price - High To Low")) {
                    query = "SELECT * FROM conseil ORDER BY nom_conseil desc";
                }
            } else if (sortBy.equals("id_typeC")) {
                if (comboBoxData.equals("Type Conseil - ASC To DESC")) {
                    query = "SELECT * FROM conseil ORDER BY id_typeC asc";
                } else if (comboBoxData.equals("Points - High To Low")) {
                    query = "SELECT * FROM conseil ORDER BY id_typeC desc";
                }
            }
            preparedStatement = connection.prepareStatement(query);
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
    public List<Conseil> getonseilByID(int idConseil) throws SQLException {
        List<Conseil> conseilList = new ArrayList<>();
        String req = "SELECT * FROM conseil WHERE id_conseil = ?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, idConseil);
        ResultSet res = pre.executeQuery();
        while (res.next()) {
            Conseil conseil = new Conseil();
            conseil.setId_conseil(res.getInt(1));
            conseil.setNom_conseil(res.getString(2));
            conseil.setVideo(res.getString(3));
            conseil.setDescription(res.getString(4));
            conseil.setId_produit(res.getInt(5));
            conseil.setId_typeC(res.getInt(6));
            conseilList.add(conseil);
        }
        return conseilList;
    }


}