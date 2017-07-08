/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Domain;
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
public class DomainDAOImpl implements DomainDAO {

    private static DomainDAOImpl domainDAO = null;
    
    private DomainDAOImpl() {}

    public static DomainDAOImpl getInstance() {
        if (domainDAO == null) {
            domainDAO = new DomainDAOImpl();
        }
        return domainDAO;
    }

    @Override
    public Long insert(Domain domain) throws PersistenceException {
        if (domain == null) {
            throw new PersistenceException("Domain cannot be null");
        }
        
        Long domainId = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "INSERT INTO domain (nom_domain, desc_domain) " +
                         "    VALUES (?, ?) returning cod_domain;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, domain.getName());
            pstmt.setString(2, domain.getDescription());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                domainId = rs.getLong("cod_domain");
                domain.setId(domainId);
            }

            rs.close();
            pstmt.close();
            connection.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DomainDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return domainId;
    }

    @Override
    public boolean update(Domain domain) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "UPDATE domain " +
                           " SET nom_domain = ?, " +
                           "     desc_domain = ? " +
                         " WHERE cod_domain = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, domain.getName());
            pstmt.setString(2, domain.getDescription());
            pstmt.setLong(3, domain.getId());
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
    public boolean remove(Long domainId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM domain WHERE cod_domain = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, domainId);
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
    public Domain getDomainById(Long domainId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM domain WHERE cod_domain = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, domainId);
            ResultSet rs = pstmt.executeQuery();

            Domain domain = new Domain();
            if (rs.next()) {
                domain.setId(domainId);
                domain.setName(rs.getString("nom_domain"));
                domain.setDescription(rs.getString("desc_domain"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return domain;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Domain> listAll() throws PersistenceException {
        try {    
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM domain ORDER BY nom_domain";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Domain> listAll = null;
            Domain domain = null;
            
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    domain = new Domain();
                    domain.setId(rs.getLong("cod_domain"));
                    domain.setName(rs.getString("nom_domain"));
                    domain.setDescription(rs.getString("desc_domain"));
                    listAll.add(domain);
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
}
