/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.UseLogDAO;
import br.cefetmg.jquest.model.dao.UseLogDAOImpl;
import br.cefetmg.jquest.model.domain.UseLog;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GABRIEL HADDAD
 */
public class UseLogManagementImplTest {
    private static UseLogManagementImpl useLogManager;
    private static UseLogDAO useLogDAO;
    private static List<UseLog> useLogList;
    private UseLog useLog;
    private Date date;

    
    
    public UseLogManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       useLogDAO = UseLogDAOImpl.getInstance();
       useLogManager = new UseLogManagementImpl(useLogDAO);
       useLogList = new ArrayList<>();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       this.useLog = new UseLog();
       date = new Date(1900, 01, 20);
       useLog.setUseDate(date);
       useLog.setIdUser(1L);
    }

    @After
    public void tearDown() {
         for (UseLog useLog: useLogList)
            try {
                useLogDAO.remove(useLog.getUseSeq(), useLog.getIdUser());
            } catch (PersistenceException ex) {
                Logger.getLogger(UseLogManagementImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
  
    @Test
    public void testUseLogInsertNullSeq() throws Exception {
        Long seq = null;
        try {
            useLog.setUseSeq(null);
            seq = useLogManager.useLogInsert(useLog);
        }
        catch (PersistenceException | BusinessException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "UseLog's sequence cannot be null";
            assertTrue("Was able to insert useLog with no Seq", msgErr.contains(msgEsperada));
            return;
        }
        useLog.setUseSeq(seq);
        useLogList.add(useLog);
    }
    
    @Test
    public void testUseLogInsertNullIdUser() throws Exception {
        Long idUser = useLog.getIdUser();
        try {
            useLog.setIdUser(null);
            useLogManager.useLogInsert(useLog);
        }
        catch (PersistenceException | BusinessException ex) {
            return;
        }
        useLog.setIdUser(idUser);
        useLogList.add(useLog);
    }
    
   @Test
    public void testUseLogInsertNullUseDate() throws Exception {
        Date date = useLog.getUseDate();
        try {
            useLog.setUseDate(null);
            useLogManager.useLogInsert(useLog);
        }
        catch (PersistenceException | BusinessException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "UseLog's use date cannot be null";
            assertTrue("Was able to insert useLog with no UseDate", msgErr.contains(msgEsperada));
            return;
        }
        useLog.setUseDate(date);
        useLogList.add(useLog);
    }
  
      @Test
    public void testUseLogInsertCorrect() {
        Long id = -1L;
        try {
            id = useLogManager.useLogInsert(useLog);
        } catch (BusinessException | PersistenceException ex) {
            fail("UseLog failed to be registered");
            return;
        }
        if (id == -1L) {
            fail("UseLog failed to be registered");
            return;
        }
        useLogList.add(useLog);
    }
 
      @Test
    public void testUpdateNullUseLog() throws Exception {
        try {
            this.useLog = null;
            this.useLogManager.useLogUpdate(useLog);
        }
        catch (PersistenceException | BusinessException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "UseLog cannot be null";
            // Exibe msg caso 2o param. seja falso
            assertTrue("Updating null useLog didn't throw an exception", msgErr.contains(msgEsperada));
            return;
        }
        fail("Updating null useLog didnt throw exception");
    }
    
    @Test
    public void testUseLogUpdateNullSeq() {
        try {
            useLogManager.useLogInsert(useLog);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(UseLogManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Long seq = useLog.getUseSeq();
        useLog.setUseSeq(null);
        try {
            useLogManager.useLogUpdate(useLog);
        } catch (BusinessException | PersistenceException ex) {
            useLog.setUseSeq(seq);
            useLogList.add(useLog);
            return;
        }
        fail("Tried to update a useLog with a null sequence");
    }
    
    @Test
    public void testUseLogUpdateNullUserId() {
        try {
            useLogManager.useLogInsert(useLog);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(UseLogManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Long idUser = useLog.getIdUser();
        useLog.setIdUser(null);
        try {
            useLogManager.useLogUpdate(useLog);
        } catch (BusinessException | PersistenceException ex) {
            useLog.setIdUser(idUser);
            useLogList.add(useLog);
            return;
        }
        fail("Tried to update a useLog with a null userId");
    }
    
     @Test
    public void testUseLogUpdateNullUseDate() {
        try {
            useLogManager.useLogInsert(useLog);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(UseLogManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        useLog.setUseDate(null);
        try {
            useLogManager.useLogUpdate(useLog);
        } catch (BusinessException | PersistenceException ex) {
            useLogList.add(useLog);
            return;
        }
        fail("Tried to update a useLog with a null use date");
    }
    
    @Test
    public void testUseLogRemoveCorrect() throws Exception {
        try {
            Long useLogSeq = useLogManager.useLogInsert(useLog);
            useLogManager.useLogRemove(useLogSeq, useLog.getIdUser());
        } catch (PersistenceException ex) {
            fail("Removal of valid useLog");
        }
    }
    
    @Test
    public void testUseLogRemoveNullSeq() throws Exception {
        try {
            this.useLog.setUseSeq(null);
            this.useLogManager.useLogRemove(useLog.getUseSeq(), useLog.getIdUser());
        } catch (PersistenceException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "UseLog's sequence cannot be null";
            assertTrue("Removal of null useLog", msgErr.contains(msgEsperada));
            return;
        }
    }
    
    @Test
    public void testRemoveUnexistentUseLog() throws Exception {
        try {
            useLogManager.useLogRemove(useLog.getUseSeq(), useLog.getIdUser());
        } catch (PersistenceException ex) {
            return;
        }
        fail("Removal of unexistent useLog");
    }
    
    @Test
    public void testUseLogRemove() {
        try {
            useLogManager.useLogInsert(useLog);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(UseLogManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            useLogManager.useLogRemove(useLog.getUseSeq(), useLog.getIdUser());
        } catch (PersistenceException ex) {
            fail("Failed to remove UseLog");
        }
    }
    
      @Test
    public void testGetUseLogByNullSeq() throws Exception {
        try {
            this.useLog.setUseSeq(null);
            this.useLogManager.getUseLogBySeq(useLog.getUseSeq(), useLog.getIdUser());
        } catch (PersistenceException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "UseLogs's sequence cannot be null";
            // Exibe msg caso 2o param. seja falso
            assertTrue("Getting useLog by null Seq", msgErr.contains(msgEsperada));
            return;
        }
    }
    
    @Test
    public void testGetUseLogBySeq() {
        try {
            useLogManager.useLogInsert(useLog);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(UseLogManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        UseLog useLogTest;
        try {
            useLogTest = useLogManager.getUseLogBySeq(useLog.getUseSeq(), useLog.getIdUser());
        } catch (PersistenceException ex) {
            fail("Failed to get useLog by sequence");
        }
        finally {
            useLogList.add(useLog);
        }
    }
    
    @Test
    public void testGetAll() {
        UseLog useLog2 = new UseLog();
        useLog2.setIdUser(1L);
        useLog2.setUseDate(new Date(1900, 01, 20));
        List<UseLog> list;
        try {
            useLogManager.useLogInsert(useLog);
            useLogManager.useLogInsert(useLog2);
            useLogList.add(useLog);
            useLogList.add(useLog2);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(UseLogManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list = useLogManager.getAllLogsByUserId(useLog.getIdUser());
        } catch (PersistenceException ex) {
            fail("Failed to get all useLogs");
            return;
        }
        if(list.isEmpty()) {
            fail("Failed to get all useLogs correctly");
        }
    }
    
}
