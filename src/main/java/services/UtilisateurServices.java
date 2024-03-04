
package services;

import entities.ICRUD;
import entities.Utilisateur;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurServices implements ICRUD<Utilisateur>{
    public Connection conx;
    public Statement stm;

    public UtilisateurServices() {
        conx = MyDB.getInstance().getConx();

    }
    @Override
    public Utilisateur login(String email,String pass)throws SQLException {
        Utilisateur user = getUserByEmail(email);
        if ((user != null) && PasswordHasher.checkPassword(pass, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public Utilisateur getUserByEmail(String email) throws SQLException {

        String req = "SELECT * FROM `utilisateur` WHERE `email` = ?";
        PreparedStatement pstmt = conx.prepareStatement(req);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            Utilisateur p = new Utilisateur(rs.getInt("id"),rs.getInt("tel"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),
                    rs.getString("password"),rs.getString("token"),rs.getString("photo"),rs.getString("adresse"),rs.getString("description"),rs.getString("role"),rs.getDate("date_naiss"),rs.getBoolean("is_actif"));
            return p;
        } else {
            return null; // no person found with this id
        }
    }

    @Override
    public boolean isEmailUnique(String email) {
        String req = "SELECT COUNT(*) FROM `utilisateur` WHERE `email` = ?";
        try {
            PreparedStatement pstmt = conx.prepareStatement(req);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void signUp(Utilisateur u) throws SQLException {
        String hashPassword = PasswordHasher.hashPassword(u.getPassword());
        String insertSql = "INSERT INTO `utilisateur` (`email`, `password`,`nom`,`prenom`,`role`,`is_actif`,`roles`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conx.prepareStatement(insertSql);
        pstmt.setString(1, u.getEmail());
        pstmt.setString(2, hashPassword);
        pstmt.setString(3, u.getNom());
        pstmt.setString(4, u.getPrenom());
        pstmt.setString(5, u.getRole());
        pstmt.setBoolean(6, u.isIs_Actif());
        pstmt.setString(7, "  ");
        pstmt.executeUpdate();
    }

    @Override
    public boolean updateUser(Utilisateur u,int id) {

        PreparedStatement pstmt = null;
        int rowsUpdated = 0;
        try {
            String sql = "UPDATE `utilisateur` SET `nom`=?, `prenom`=?, `role`=?, `tel`=?, `adresse`=?, `date_naiss`=?, `description`=?, `is_actif`=?, `photo`=?  WHERE `id`=?";
            pstmt = conx.prepareStatement(sql);
            pstmt.setString(1, u.getNom());
            pstmt.setString(2, u.getPrenom());
            pstmt.setString(3, u.getRole());
            pstmt.setInt(4, u.getTel());
            pstmt.setString(5, u.getAddresse());
            pstmt.setDate(6, u.getDate_naiss());
            pstmt.setString(7, u.getDescription());
            pstmt.setBoolean(8, u.isIs_Actif());
            pstmt.setString(9, u.getPhoto());
            pstmt.setInt(10, id);
            rowsUpdated = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated > 0;


    }
    @Override
    public boolean updatePwd(String pwd,int id) {
        String hashPassword = PasswordHasher.hashPassword(pwd);
        PreparedStatement pstmt = null;
        int rowsUpdated = 0;
        try {
            String sql = "UPDATE `utilisateur` SET `password`=? WHERE `id`=?";
            pstmt = conx.prepareStatement(sql);
            pstmt.setString(1, hashPassword);
            pstmt.setInt(2, id);
            rowsUpdated = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated > 0;


    }
    @Override
    public List<Utilisateur> afficherListe() throws SQLException {
        String req = "SELECT * FROM `utilisateur`";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Utilisateur> users = new ArrayList<Utilisateur>();
        while (rs.next()) {
            Utilisateur p = new Utilisateur(rs.getInt("id"),
                    rs.getInt("tel"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("token"),
                    rs.getString("photo"),
                    rs.getString("adresse"),
                    rs.getString("description"),
                    rs.getString("role"),
                    rs.getDate("date_naiss"),
                    rs.getBoolean("is_Actif")
            );
            users.add(p);
        }

        return users;
    }
    @Override
    public List<Utilisateur> afficherListeS(String search) throws SQLException {
        String req = "SELECT * FROM `utilisateur` WHERE `nom` LIKE ? OR `prenom` LIKE ? OR `email` LIKE ? OR `role` LIKE ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, "%" + search + "%");
        ps.setString(2, "%" + search + "%");
        ps.setString(3, "%" + search + "%");
        ps.setString(4, "%" + search + "%");
        ResultSet rs = ps.executeQuery();
        List<Utilisateur> users = new ArrayList<Utilisateur>();
        while (rs.next()) {
            Utilisateur p = new Utilisateur(rs.getInt("id"),
                    rs.getInt("tel"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("token"),
                    rs.getString("photo"),
                    rs.getString("adresse"),
                    rs.getString("description"),
                    rs.getString("role"),
                    rs.getDate("date_naiss"),
                    rs.getBoolean("is_Actif")
            );
            users.add(p);
        }
        return users;
    }

    @Override
    public int getUserCountAc() {
        String query = "SELECT COUNT(*) as user_count FROM utilisateur WHERE is_actif = 1;";
        try (PreparedStatement pstmt = conx.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("user_count");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getUserCountB() {
        String query = "SELECT COUNT(*) as user_count FROM utilisateur WHERE is_actif = 0;";
        try (PreparedStatement pstmt = conx.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("user_count");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getUserCountA() {
        String query = "SELECT COUNT(*) as user_count FROM utilisateur WHERE role = 'Admin';";
        try (PreparedStatement pstmt = conx.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("user_count");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getUserCountC() {
        String query = "SELECT COUNT(*) as user_count FROM utilisateur WHERE role = 'Client';";
        try (PreparedStatement pstmt = conx.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("user_count");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public List<Utilisateur> triPrenom() throws SQLException {
        String req = "SELECT * FROM `utilisateur` order by prenom";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Utilisateur> users = new ArrayList<Utilisateur>();
        while (rs.next()) {
            Utilisateur p = new Utilisateur(rs.getInt("id"),
                    rs.getInt("tel"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("token"),
                    rs.getString("photo"),
                    rs.getString("adresse"),
                    rs.getString("description"),
                    rs.getString("role"),
                    rs.getDate("date_naiss"),
                    rs.getBoolean("is_Actif")
            );
            users.add(p);
        }

        return users;
    }
    @Override
    public List<Utilisateur> triNom() throws SQLException {
        String req = "SELECT * FROM `utilisateur` order by nom";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Utilisateur> users = new ArrayList<Utilisateur>();
        while (rs.next()) {
            Utilisateur p = new Utilisateur(rs.getInt("id"),
                    rs.getInt("tel"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("token"),
                    rs.getString("photo"),
                    rs.getString("adresse"),
                    rs.getString("description"),
                    rs.getString("role"),
                    rs.getDate("date_naiss"),
                    rs.getBoolean("is_Actif")
            );
            users.add(p);
        }

        return users;
    }
    public static boolean isValidPassword(String password) {
        // Enforce minimum length of 8 characters and check if not empty
        return password.length() >= 8 && !password.isEmpty();
    }
    @Override
    public boolean updateToken(String Email, String token) {
        PreparedStatement pstmt = null;
        int rowsUpdated = 0;
        try {
            String sql = "UPDATE `utilisateur` SET `token`=? WHERE `email`=?";
            pstmt = conx.prepareStatement(sql);
            pstmt.setString(1, token);
            pstmt.setString(2, Email);
            rowsUpdated = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated > 0;
    }
    @Override
    public int isTokenExist(String token) {
        String req = "SELECT id FROM `utilisateur` WHERE `token` = ?";
        try {
            PreparedStatement pstmt = conx.prepareStatement(req);
            pstmt.setString(1, token);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }    }
    public void deleteUser(int id) {
        PreparedStatement pstmt = null;
        int rowsUpdated = 0;
        try {
            String sql = "delete FROM `utilisateur` WHERE `id`=?";
            pstmt = conx.prepareStatement(sql);
            pstmt.setInt(1, id);
            rowsUpdated = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
