/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Module;
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
 * @author Gabriel Haddad
 */
public class ModuleDAOImpl implements ModuleDAO {
    private static ModuleDAOImpl moduleDAO = null;
    
    private ModuleDAOImpl() {}
    
    public static ModuleDAOImpl getInstance(){
        if (moduleDAO == null) {
            moduleDAO = new ModuleDAOImpl();
        }
        return moduleDAO;
    }  
    
    @Override
    public Long insert(Module module) throws PersistenceException {
        if (module == null) {
            throw new PersistenceException("Module cannot be null");
        }

        Long moduleId = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO module (cod_domain, nom_module, desc_module) "
                    + "    VALUES (?, ?, ?) returning cod_module;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, module.getDomainId());
            pstmt.setString(2, module.getName());
            pstmt.setString(3, module.getDescription());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                moduleId = rs.getLong("cod_module");
                module.setId(moduleId);
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ModuleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return moduleId;
    }

    @Override
    public boolean update(Module module) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "UPDATE module "
                    + " SET nom_module = ?, "
                    + "     desc_module = ?, "
                    + "     cod_domain = ?"
                    + " WHERE cod_module = ? ";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, module.getName());
            pstmt.setString(2, module.getDescription());
            pstmt.setLong(3, module.getDomainId());
            pstmt.setLong(4, module.getId());
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
    public boolean remove(Long moduleId, Long domainId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM module WHERE cod_module = ? AND cod_domain = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, moduleId);
            pstmt.setLong(2, domainId);
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
    public Module getModuleById(Long moduleId, Long domainId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM module WHERE cod_module = ? AND cod_domain = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, moduleId);
            pstmt.setLong(2, domainId);
            ResultSet rs = pstmt.executeQuery();

            Module module = null;
            if (rs.next()) {
                module = new Module();
                module.setId(moduleId);
                module.setDomainId(domainId);
                module.setName(rs.getString("nom_module"));
                module.setDescription(rs.getString("desc_module"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return module;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Module> listAll() throws PersistenceException {
        try {    
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM module ORDER BY nom_module";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Module> listAll = null;
            Module module = null;
            
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    module = new Module();
                    module.setId(rs.getLong("cod_module"));
                    module.setDomainId(rs.getLong("cod_domain"));
                    module.setName(rs.getString("nom_module"));
                    module.setDescription(rs.getString("desc_module"));
                    listAll.add(module);
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
    public List<Module> listModulesByDomainId(Long domainId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM module WHERE cod_domain = ? ORDER BY nom_module";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, domainId);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Module> listAll = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    Module module = new Module();
                    module.setId(rs.getLong("cod_module"));
                    module.setDomainId(domainId);
                    module.setName(rs.getString("nom_module"));
                    module.setDescription(rs.getString("desc_module"));
                    listAll.add(module);
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
