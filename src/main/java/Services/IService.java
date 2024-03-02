package Services;

import Entities.Categorie;
import Entities.Conseil;
import Entities.Review;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IService <T>{

    public void ajouterConseil(T t) throws SQLException ;
    public void modifierConseil(T t, int id_conseil ) throws SQLException ;
    public void deleteConseil(int id_conseil) throws SQLException;
    public List<T> displayConseil() throws SQLException ;

    public List<Conseil> searchProducts(String search);

    public List<Conseil> sortConseilByNom()throws SQLException ;

    public Conseil getconseilByID(int idConseil) throws SQLException;

    public int ConseilNumbers() throws SQLException;

    public Conseil getLastAddedConseil() throws SQLException;

    public Categorie getCategoriesName(int idCategory) throws SQLException;

    public Map<Integer, Long> getConseilCountByCategory() throws SQLException;


    public List<Review> getAllComments(int id_conseil)throws SQLException;

    public void addReview(Review review)throws SQLException ;

    public double getAverageRatingForConseil(int conseilId);


}
