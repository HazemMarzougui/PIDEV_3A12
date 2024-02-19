package services;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    public  void ajouter(T t ) throws SQLException;
    public  void modifier(T t,int idpanier ) throws SQLException;
    public  void supprimer(int idpanier) throws SQLException;
    public List<T> afficher() throws SQLException;



}
