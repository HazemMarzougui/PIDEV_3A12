package entities;

import java.util.List;
import java.sql.SQLException;


public interface CrudProduit<Prod> {

    public void ajouter(Prod p);
    public void modifier(Prod p);
    public void supprimer(int id) throws SQLException;
    public List<Produit> Show();
}
