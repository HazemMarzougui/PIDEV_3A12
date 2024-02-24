package entities;

import java.sql.SQLException;
import java.util.List;


public interface ICRUD<T> {
    
    //public void ajouter(T p);
    //public void ajouterr(T p);
    //public List<T> afficherListe() throws SQLException ;
    public Utilisateur getUserByEmail(String email) throws SQLException;

    public Utilisateur login(String email,String pass) throws SQLException;
    public boolean isEmailUnique(String email);
        public int isTokenExist(String token);
        public boolean updatePwd(String pwd,int id);
    public void signUp(T u) throws SQLException ;
    public boolean updateUser(T u,int id);
    public boolean updateToken(String Email,String token);
    public List<T> afficherListe() throws SQLException;
    public List<Utilisateur> afficherListeS(String search) throws SQLException;
    public int getUserCountAc();
        public int getUserCountB();
            public int getUserCountI();
                public int getUserCountE();
                    public int getUserCountAd();
                    public List<Utilisateur> triNom() throws SQLException;
                    public List<Utilisateur> triPrenom() throws SQLException;

    
}
