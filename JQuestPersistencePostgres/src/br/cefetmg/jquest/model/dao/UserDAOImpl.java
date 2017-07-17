package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.User;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paula Ribeiro
 */
public class UserDAOImpl implements UserDAO {

    private static UserDAOImpl userDAO = null;
    
    private UserDAOImpl() {}

    public static UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }
    
    @Override
    public Long insert(User user) throws PersistenceException {
        if (user == null) {
            throw new PersistenceException("Domain cannot be null");
        }
        
        Long userId = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "INSERT INTO \"user\" (nom_username, nom_email, txt_password, idt_profile) " +
                         "    VALUES (?, ?, ?, ?) returning cod_userID;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, String.valueOf(user.getIdtProfile()));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userId = rs.getLong("cod_userID");
                user.setId(userId);
            }

            rs.close();
            pstmt.close();
            connection.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DomainDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userId;
    }

    @Override
    public boolean update(User user) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "UPDATE \"user\" " +
                           " SET nom_username = ?, " +
                           "     nom_email = ? " +
                           "     txt_password = ? " +
                           "     idt_profile = ? " +
                         " WHERE cod_userID = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, String.valueOf(user.getIdtProfile()));
            pstmt.setLong(5, user.getId());
            pstmt.executeUpdate();
            
            pstmt.close();
            connection.close();
            
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e); 
        }
    }

    @Override
    public boolean remove(Long userId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM \"user\" WHERE cod_userID = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            pstmt.executeUpdate();
            
            pstmt.close();
            connection.close();
            
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e); 
        }
    }

    @Override
    public User getUserById(Long userId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM \"user\" WHERE cod_userID = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(userId);
                user.setUserName(rs.getString("nom_username"));
                user.setEmail(rs.getString("nom_email"));
                user.setPassword(rs.getString("txt_password"));
                user.setIdtProfile(rs.getString("idt_profile").charAt(0));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<User> listAll() throws PersistenceException {
        try {    
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM \"user\" ORDER BY nom_username";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<User> listAll = null;
            User user = null;
            
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    user = new User();
                    user.setId(rs.getLong("cod_userID"));
                    user.setUserName(rs.getString("nom_username"));
                    user.setEmail(rs.getString("nom_email"));
                    user.setPassword(rs.getString("txt_password"));
                    user.setIdtProfile(rs.getString("idt_profile").charAt(0));
                    listAll.add(user);
                } while (rs.next());
            }
            
            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public User getUserByEmail(String email) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM \"user\" WHERE nom_email = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong("cod_userid"));
                user.setUserName(rs.getString("nom_username"));
                user.setEmail(rs.getString("nom_email"));
                user.setPassword(rs.getString("txt_password"));
                user.setIdtProfile(rs.getString("idt_profile").charAt(0));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return user;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }
    
}
