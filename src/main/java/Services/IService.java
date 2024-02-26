package Services;

import Entities.Conseil;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{

    public void ajouterConseil(T t) throws SQLException ;
    public void modifierConseil(T t, int id_conseil ) throws SQLException ;
    public void deleteConseil(int id_conseil) throws SQLException;
    public List<T> displayConseil() throws SQLException ;

    public List<Conseil> searchProducts(String search);
    public List<Conseil> sortProductsUser(String sortBy, String comboBoxData);

    public List<Conseil> getonseilByID(int idConseil) throws SQLException;


}
