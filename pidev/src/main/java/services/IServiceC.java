package services;

import java.sql.SQLException;
import java.util.List;

public interface IServiceC <T> {
    public  void ajouter(T t ) throws SQLException;
    public  void modifier(T t,int idcommande ) throws SQLException;
    public  void supprimer(int idcommande) throws SQLException;
    public List<T> afficher() throws SQLException;
}
