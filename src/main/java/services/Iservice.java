package services;
import entities.produit;

import java.sql.SQLException;
import java.util.List;

public interface Iservice<T>  {

    public List<T> getAllProducts()throws SQLException;

}
