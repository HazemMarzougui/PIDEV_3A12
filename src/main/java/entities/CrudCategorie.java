package entities;

import java.sql.SQLException;
import java.util.List;

public interface CrudCategorie<Categ> {
    public void ajouter(Categ c);
    public void modifier(Categ c);
    public void supprimer(int id) throws SQLException;
    public List<Categorie> Show();

    public Categorie getById(int id);
}
