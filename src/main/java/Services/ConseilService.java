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
        String req = "INSERT INTO conseil ( nom_conseil , video , description , id_user, id_produit , id_typeC) VALUES ( ?, ?, ?, ?, ?, ?)";

        // Using try-with-resources to ensure proper resource management
        try (PreparedStatement prepareStatement = connection.prepareStatement(req)) {
            // Setting the parameters for the prepared statement
            prepareStatement.setString(1, conseil.getNom_conseil());
            prepareStatement.setString(2, conseil.getVideo());
            prepareStatement.setString(3, conseil.getDescription()); // Corrected index from 4 to 3
            prepareStatement.setInt(4, conseil.getId_user());
            prepareStatement.setInt(5, conseil.getId_produit());
            prepareStatement.setInt(6, conseil.getId_typeC());

            // Executing the update operation
            prepareStatement.executeUpdate();
            displayConseil();
    }
    }

    @Override
    public void modifierConseil(Conseil conseil, int idConseil) throws SQLException
    {
        String req = "UPDATE conseil SET nom_conseil=?, video=?, description=?, id_user=?, id_produit=?, id_typeC=? WHERE id_conseil=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, conseil.getNom_conseil());
        pre.setString(2, conseil.getVideo());
        pre.setString(3, conseil.getDescription());
        pre.setInt(4, conseil.getId_user());
        pre.setInt(5, conseil.getId_produit());
        pre.setInt(6, conseil.getId_typeC());
        pre.setInt(7,idConseil);

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
            System.out.println("art deleted with success.");
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
            conseil1.setId_user(res.getInt(5));
            conseil1.setId_produit(res.getInt(6));
            conseil1.setId_typeC(res.getInt(7));
            conseils.add(conseil1);
        }
        return conseils;
    }

}
