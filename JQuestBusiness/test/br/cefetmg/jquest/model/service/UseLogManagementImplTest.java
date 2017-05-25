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
import java.text.SimpleDateFormat;
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
    private static List<Long> useLogList;
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
       useLog.setIdUser(0L);
       useLog.setUseSeq(0L);
    }
    
    @After
    public void tearDown() {
         for(Long id: useLogList)
            try {
                useLogDAO.remove(id);
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
        useLogList.add(seq);
    }
    
    @Test
    public void testInsertNullUseLog() throws Exception {
        try {
            useLog = null;
            useLogManager.useLogInsert(useLog);
        } catch (BusinessException | PersistenceException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "UseLog cannot be null";
            // Exibe msg caso 2o param. seja falso
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of null useLog");
    }
    
    @Test
    public void testUseLogInsertNullIdUser() throws Exception {
        Long idUser = null;
        try {
            useLog.setIdUser(null);
            idUser = useLogManager.useLogInsert(useLog);
        }
        catch (PersistenceException | BusinessException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "UseLog's userID cannot be null";
            assertTrue("Was able to insert useLog with no UserId", msgErr.contains(msgEsperada));
            return;
        }
        useLogList.add(idUser);
    }
    
   @Test
    public void testUseLogInsertNullUseDate() throws Exception {
        Long date = null;
        try {
            useLog.setUseDate(null);
            date = useLogManager.useLogInsert(useLog);
        }
        catch (PersistenceException | BusinessException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "UseLog's use date cannot be null";
            assertTrue("Was able to insert useLog with no UseDate", msgErr.contains(msgEsperada));
            return;
        }
        useLogList.add(date);
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
        
        //removes registered useLog
        try {
            useLogManager.useLogRemove(id);
        } catch (PersistenceException ex) {
            Logger.getLogger(UseLogManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
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
        useLog.setUseSeq(null);
        try {
            useLogManager.useLogUpdate(useLog);
        } catch (BusinessException | PersistenceException ex) {
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
        useLog.setIdUser(null);
        try {
            useLogManager.useLogUpdate(useLog);
        } catch (BusinessException | PersistenceException ex) {
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
            return;
        }
        fail("Tried to update a useLog with a null use date");
    }
    
    @Test
    public void testUseLogRemoveCorrect() throws Exception {
        try {
            Long useLogSeq = useLogManager.useLogInsert(useLog);
            useLogManager.useLogRemove(useLogSeq);
        } catch (PersistenceException ex) {
            fail("Removal of valid useLog");
        }
    }
    
    @Test
    public void testUseLogRemoveNullSeq() throws Exception {
        try {
            this.useLog.setUseSeq(null);
            this.useLogManager.useLogRemove(useLog.getUseSeq());
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
            useLogManager.useLogRemove(useLog.getUseSeq());
        } catch (PersistenceException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "UseLog with sequence " + useLog.getUseSeq()+ " is not persisted";
            assertTrue(msgErr.contains(msgEsperada));
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
            useLogManager.useLogRemove(useLog.getUseSeq());
        } catch (PersistenceException ex) {
            fail("Failed to remove UseLog");
        }
    }
    
      @Test
    public void testGetUseLogByNullSeq() throws Exception {
        try {
            this.useLog.setUseSeq(null);
            this.useLogManager.getUseLogBySeq(useLog.getUseSeq());
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
            useLogTest = useLogManager.getUseLogBySeq(useLog.getUseSeq());
        } catch (PersistenceException ex) {
            fail("Failed to get useLog by sequence");
            return;
        }
        if (!useLogTest.equals(useLog)) {
            fail("Failed to get useLog by sequence");
        }
    }
    
    @Test
    public void testGetAll() {
        UseLog useLog2 = useLog;
        List<UseLog> list;
        try {
            useLogManager.useLogInsert(useLog);
            useLogManager.useLogInsert(useLog2);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(UseLogManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list = useLogManager.getAll();
        } catch (PersistenceException ex) {
            fail("Failed to get all useLogs");
            return;
        }
        if(list.isEmpty()) {
            fail("Failed to get all useLogs correctly");
        }
    }
    
}
