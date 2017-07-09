/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.UseLog;
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
public class UseLogDAOImpl implements UseLogDAO {
    
    private static UseLogDAOImpl useLogDAO = null;
    
    private UseLogDAOImpl() {}
    
    public static UseLogDAOImpl getInstance(){
        if (useLogDAO == null) {
            useLogDAO = new UseLogDAOImpl();
        }
        return useLogDAO;
    }  
    
    @Override
    public Long insert(UseLog useLog) throws PersistenceException {
        if (useLog == null) {
            throw new PersistenceException("Module cannot be null");
        }

        Long useLogSeq = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO uselog (cod_id, dat_use) "
                    + "    VALUES (?, ?) returning seq_use;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, useLog.getIdUser());
            pstmt.setDate(2, new java.sql.Date(useLog.getUseDate().getTime()));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                useLogSeq = rs.getLong("seq_use");
                useLog.setUseSeq(useLogSeq);
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ModuleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return useLogSeq;
    }

    @Override
    public boolean update(UseLog useLog) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE useLog "
                    + " SET dat_use = ? "
                    + " WHERE cod_id = ? "
                    + " AND seq_use = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setDate(1, new java.sql.Date(useLog.getUseDate().getTime()));
            pstmt.setLong(2, useLog.getIdUser());
            pstmt.setLong(3, useLog.getUseSeq());
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
    public boolean remove(Long useLogSeq, Long userId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM uselog WHERE cod_id = ? AND seq_use = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            pstmt.setLong(2, useLogSeq);
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
    public UseLog getUseLogBySeq(Long useLogSeq, Long userId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM uselog WHERE cod_id = ? AND seq_use = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            pstmt.setLong(2, useLogSeq);
            ResultSet rs = pstmt.executeQuery();

            UseLog useLog = null;
            if (rs.next()) {
                useLog = new UseLog();
                useLog.setIdUser(userId);
                useLog.setUseSeq(useLogSeq);
                useLog.setUseDate(rs.getDate("dat_use"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return useLog;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<UseLog> getAllLogsByUserId(Long userId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM uselog WHERE cod_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<UseLog> listAll = null;
            
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    UseLog useLog = new UseLog();
                    useLog.setIdUser(userId);
                    useLog.setUseSeq(rs.getLong("seq_use"));
                    useLog.setUseDate(rs.getDate("dat_use"));
                    listAll.add(useLog);
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
