package Services;

import java.sql.SQLException;
import java.util.List;

public interface IServiceCategorie <T>{

    public void ajouterCategorie(T t) throws SQLException ;
    public void modifierCategorie(T t, int id_conseil ) throws SQLException ;
    public void deleteCategorie(int id_conseil) throws SQLException;
    public List<T> displayCategorie() throws SQLException ;


}