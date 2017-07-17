/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.DissertiveQuestionAnswerDAOImpl;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.domain.DissertiveQuestionAnswer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import br.cefetmg.jquest.model.dao.DissertiveQuestionAnswerDAO;

/**
 *
 * @author Thalesgsn
 */
public class DissertiveQuestionAnswerManagementImplTest {
    private static DissertiveQuestionAnswerManagement dissertiveQuestionAnswerManagement;
    private static DissertiveQuestionAnswerDAO dissertiveQuestionAnswerDAO;
    private DissertiveQuestionAnswer dissertiveQuestionAnswer;
    
    public DissertiveQuestionAnswerManagementImplTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        dissertiveQuestionAnswerDAO = DissertiveQuestionAnswerDAOImpl.getInstance();
        dissertiveQuestionAnswerManagement = new DissertiveQuestionAnswerManagementImpl(dissertiveQuestionAnswerDAO);        
    }
    
    @Before
    public void setUp() {
        this.dissertiveQuestionAnswer = new DissertiveQuestionAnswer(1L, 2L, 3L, "Test", 0);
    }
    
    @Test
    public void testDissertiveQuestionAnswerInsertNull() {
        dissertiveQuestionAnswer = null;
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
            fail("Null dissertiveQuestionAnswer registered");
        } catch (BusinessException | PersistenceException ex) {
        }
    }
    
    @Test
    public void testDissertiveQuestionAnswerInsertNullTxtAnswer() {
        dissertiveQuestionAnswer.setTxtAnswer(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
            fail("DissertiveQuestionAnswer with null name registered");
        } catch (BusinessException | PersistenceException ex) {
            dissertiveQuestionAnswer.setTxtAnswer("Test");
        }
    }
    
    @Test
    public void testDissertiveQuestionAnswerInsertNullUserID() {
        dissertiveQuestionAnswer.setUserID(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
            fail("Tried to insert a dissertiveQuestionAnswer with a null UserID.");
        } catch (BusinessException | PersistenceException ex) {}
            dissertiveQuestionAnswer.setUserID(0L);
    }
    @Test
    public void testDissertiveQuestionAnswerInsertNullQuestionID() {
        dissertiveQuestionAnswer.setQuestionID(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
            fail("Tried to insert a dissertiveQuestionAnswer with a null QuestionID.");
        } catch (BusinessException | PersistenceException ex) {}
            dissertiveQuestionAnswer.setQuestionID(0L);
    }
    
    @Test
    public void testDissertiveQuestionAnswerInsertNullSeqAnswerUser() {
        dissertiveQuestionAnswer.setSeqAnswerUser(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
            fail("Tried to insert a dissertiveQuestionAnswer with a null QuestionID.");
        } catch (BusinessException | PersistenceException ex) {}
            dissertiveQuestionAnswer.setSeqAnswerUser(0L);
    }
    
    @Test
    public void testDissertiveQuestionAnswerInsertCorrect() {
        Long userAnsserSeq = -3L;
        
        try {
            userAnsserSeq = dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            
            fail(ex.getMessage());
            //fail("DissertiveQuestionAnswer failed to be registered");
            return;
        }
        if (userAnsserSeq == -3L) {
            fail("DissertiveQuestionAnswer failed to be registered");
            return;
        }
        
        //removes registered dissertiveQuestionAnswer
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerRemove(3L);
        } catch (PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @Test
    public void testDissertiveQuestionAnswerUpdateNull() {
         try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dissertiveQuestionAnswer = null;
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
         fail("DissertiveQuestionAnswer updated to null");
        } catch (BusinessException | PersistenceException ex) { }
    }
    
    @Test
    public void testDissertiveQuestionAnswerUpdateNullTxtanswer() {
         try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dissertiveQuestionAnswer.setTxtAnswer(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
            fail("DissertiveQuestionAnswer updated with null name");
        } catch (BusinessException | PersistenceException ex) { }
    }
    
    @Test
    public void testDissertiveQuestionAnswerUpdateNullUserID() {
         try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dissertiveQuestionAnswer.setUserID(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
            fail("Tried to update a dissertiveQuestionAnswer with a null UserID.");
        } catch (BusinessException | PersistenceException ex) {
            dissertiveQuestionAnswer.setUserID(0L);
        }
    }
    
    @Test
    public void testDissertiveQuestionAnswerUpdateNullQuestionID() {
         try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dissertiveQuestionAnswer.setQuestionID(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
            fail("Tried to update a dissertiveQuestionAnswer with a null QuestionID.");
        } catch (BusinessException | PersistenceException ex) {
            dissertiveQuestionAnswer.setQuestionID(0L);
        }
    }
    
    @Test
    public void testDissertiveQuestionAnswerUpdateNullSeqAnswerUser() {
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dissertiveQuestionAnswer.setSeqAnswerUser(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
            fail("Tried to update a dissertiveQuestionAnswer with a null SeqAnswerUser.");
        } catch (BusinessException | PersistenceException ex) {
            dissertiveQuestionAnswer.setSeqAnswerUser(0L);
        }
    }
    
    @Test
    public void testDissertiveQuestionAnswerUpdateCorrect() {
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dissertiveQuestionAnswer.setTxtAnswer("Update test");
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            fail(ex.getMessage());
            //fail("Failed to update dissertiveQuestionAnswer");
            return;
        }
        try {
            if (!dissertiveQuestionAnswerManagement.getDissertiveQuestionAnswerById(dissertiveQuestionAnswer.getSeqAnswerUser()).getTxtAnswer().equals("Update test")) {
                fail("Failed to update dissertiveQuestionAnswer");
            }
        } catch (PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testDissertiveQuestionAnswerRemove() {
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerRemove(dissertiveQuestionAnswer.getSeqAnswerUser());
        } catch (PersistenceException ex) {
            
        fail(ex.getMessage());
        //fail("Failed to remove dissertiveQuestionAnswer");
        }
    }
    
    @Test
    public void testGetModuleById() {
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        DissertiveQuestionAnswer dissertiveQuestionAnswerTest;
        try {
            dissertiveQuestionAnswerTest = dissertiveQuestionAnswerManagement.getDissertiveQuestionAnswerById(dissertiveQuestionAnswer.getSeqAnswerUser());
        } catch (PersistenceException ex) {
            fail(ex.getMessage());
            //fail("Failed to get dissertiveQuestionAnswer by id");
            return;
        }
        if (!dissertiveQuestionAnswerTest.equals(dissertiveQuestionAnswer)) {
            fail("Failed to get dissertiveQuestionAnswer by id");
        }
    }
    
    @Test
    public void testGetAll() {
        DissertiveQuestionAnswer dissertiveQuestionAnswer2 = new DissertiveQuestionAnswer(3L, 2L, 1L, "Test Again", 0.2);
        List<DissertiveQuestionAnswer> list;
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer2);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list = dissertiveQuestionAnswerManagement.getAll();
        } catch (PersistenceException ex) {
            fail("Failed to get all dissertiveQuestionAnswers");
            return;
        }
        if(list.isEmpty()) {
            fail("Failed to get all dissertiveQuestionAnswers correctly");
        }
    }
}
