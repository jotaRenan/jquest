/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.OpenEndedAnswerDAO;
import br.cefetmg.jquest.model.dao.OpenEndedAnswerDAOImpl;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import br.cefetmg.jquest.model.domain.OpenEndedAnswer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Thalesgsn
 */
public class OpenEndedAnswerManagementImplTest {
    private static OpenEndedAnswerManagement openEndedAnswerManagement;
    private static OpenEndedAnswerDAO openEndedAnswerDAO;
    private OpenEndedAnswer openEndedAnswer;
    
    public OpenEndedAnswerManagementImplTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        openEndedAnswerDAO = OpenEndedAnswerDAOImpl.getInstance();
        openEndedAnswerManagement = new OpenEndedAnswerManagementImpl(openEndedAnswerDAO);        
    }
    
    @Before
    public void setUp() {
        this.openEndedAnswer = new OpenEndedAnswer(1L, 2L, 3L, "Test", 0);
    }
    
    @Test
    public void testOpenEndedAnswerInsertNull() {
        openEndedAnswer = null;
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
            fail("Null openEndedAnswer registered");
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
    }
    
    @Test
    public void testOpenEndedAnswerInsertNullTxtAnswer() {
        openEndedAnswer.setTxtAnswer(null);
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
            fail("OpenEndedAnswer with null name registered");
        } catch (BusinessException | PersistenceException ex) {
            openEndedAnswer.setTxtAnswer("Test");
            return;
        }
    }
    
    @Test
    public void testOpenEndedAnswerInsertNullUserID() {
        openEndedAnswer.setUserID(null);
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
            fail("Tried to insert a openEndedAnswer with a null UserID.");
        } catch (BusinessException | PersistenceException ex) {}
            openEndedAnswer.setUserID(0L);
    }
    @Test
    public void testOpenEndedAnswerInsertNullQuestionID() {
        openEndedAnswer.setQuestionID(null);
        try {
            openEndedAnswerManagement.openEndedAnswerUpdate(openEndedAnswer);
            fail("Tried to insert a openEndedAnswer with a null QuestionID.");
        } catch (BusinessException | PersistenceException ex) {}
            openEndedAnswer.setQuestionID(0L);
    }
    
    @Test
    public void testOpenEndedAnswerInsertNullSeqAnswerUser() {

        openEndedAnswer.setSeqAnswerUser(null);
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
            fail("Tried to insert a openEndedAnswer with a null QuestionID.");
        } catch (BusinessException | PersistenceException ex) {}
            openEndedAnswer.setSeqAnswerUser(0L);
    }
    
    @Test
    public void testOpenEndedAnswerInsertCorrect() {
        Long userAnsserSeq = -3L;
        
        try {
            userAnsserSeq = openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
            userAnsserSeq = openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
        } catch (BusinessException | PersistenceException ex) {
            
            fail(ex.getMessage());
            //fail("OpenEndedAnswer failed to be registered");
            return;
        }
        if (userAnsserSeq == -3L) {
            fail("OpenEndedAnswer failed to be registered");
            return;
        }
        
        //removes registered openEndedAnswer
        try {
            openEndedAnswerManagement.openEndedAnswerRemove(1L, 2L, 3L);
        } catch (PersistenceException ex) {
            Logger.getLogger(OpenEndedAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @Test
    public void testOpenEndedAnswerUpdateNull() {
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(OpenEndedAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        openEndedAnswer = null;
        try {
            openEndedAnswerManagement.openEndedAnswerUpdate(openEndedAnswer);
         fail("OpenEndedAnswer updated to null");
        } catch (BusinessException | PersistenceException ex) { }
    }
    
    @Test
    public void testOpenEndedAnswerUpdateNullTxtAwnser() {
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(OpenEndedAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        openEndedAnswer.setTxtAnswer(null);
        try {
            openEndedAnswerManagement.openEndedAnswerUpdate(openEndedAnswer);
            fail("OpenEndedAnswer updated with null name");
        } catch (BusinessException | PersistenceException ex) { }
    }
    
    @Test
    public void testOpenEndedAnswerUpdateNullUserID() {
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(OpenEndedAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        openEndedAnswer.setUserID(null);
        try {
            openEndedAnswerManagement.openEndedAnswerUpdate(openEndedAnswer);
            fail("Tried to update a openEndedAnswer with a null UserID.");
        } catch (BusinessException | PersistenceException ex) {
            openEndedAnswer.setUserID(0L);
        }
    }
    
    @Test
    public void testOpenEndedAnswerUpdateNullQuestionID() {
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(OpenEndedAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        openEndedAnswer.setQuestionID(null);
        try {
            openEndedAnswerManagement.openEndedAnswerUpdate(openEndedAnswer);
            fail("Tried to update a openEndedAnswer with a null QuestionID.");
        } catch (BusinessException | PersistenceException ex) {
            openEndedAnswer.setQuestionID(0L);
        }
    }
    
    @Test
    public void testOpenEndedAnswerUpdateNullSeqAnswerUser() {
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(OpenEndedAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        openEndedAnswer.setSeqAnswerUser(null);
        try {
            openEndedAnswerManagement.openEndedAnswerUpdate(openEndedAnswer);
            fail("Tried to update a openEndedAnswer with a null SeqAnswerUser.");
        } catch (BusinessException | PersistenceException ex) {
            openEndedAnswer.setSeqAnswerUser(0L);
        }
    }
    
    @Test
    public void testOpenEndedAnswerUpdateCorrect() {
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(OpenEndedAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        openEndedAnswer.setTxtAnswer("Update test");
        try {
            openEndedAnswerManagement.openEndedAnswerUpdate(openEndedAnswer);
        } catch (BusinessException | PersistenceException ex) {
            fail(ex.getMessage());
            //fail("Failed to update openEndedAnswer");
            return;
        }
        try {
            if (!openEndedAnswerManagement.getOpenEndedAnswerById(openEndedAnswer.getUserID(), openEndedAnswer.getQuestionID(), openEndedAnswer.getSeqAnswerUser()).getTxtAnswer().equals("Update test")) {
                fail("Failed to update openEndedAnswer");
            }
        } catch (PersistenceException ex) {
            Logger.getLogger(OpenEndedAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testOpenEndedAnswerRemove() {
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(OpenEndedAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            openEndedAnswerManagement.openEndedAnswerRemove(openEndedAnswer.getUserID(), openEndedAnswer.getQuestionID(), openEndedAnswer.getSeqAnswerUser());
        } catch (PersistenceException ex) {
            
        fail(ex.getMessage());
        //fail("Failed to remove openEndedAnswer");
        }
    }
    
    @Test
    public void testGetModuleById() {
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(OpenEndedAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        OpenEndedAnswer openEndedAnswerTest;
        try {
            openEndedAnswerTest = openEndedAnswerManagement.getOpenEndedAnswerById(openEndedAnswer.getUserID(), openEndedAnswer.getQuestionID(), openEndedAnswer.getSeqAnswerUser());
        } catch (PersistenceException ex) {
            fail(ex.getMessage());
            //fail("Failed to get openEndedAnswer by id");
            return;
        }
        if (!openEndedAnswerTest.equals(openEndedAnswer)) {
            fail("Failed to get openEndedAnswer by id");
        }
    }
    
    @Test
    public void testGetAll() {
        OpenEndedAnswer openEndedAnswer2 = new OpenEndedAnswer(3L, 2L, 1L, "Test Again", 0.2);
        List<OpenEndedAnswer> list;
        try {
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer);
            openEndedAnswerManagement.openEndedAnswerInsert(openEndedAnswer2);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(OpenEndedAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list = openEndedAnswerManagement.getAll();
        } catch (PersistenceException ex) {
            fail("Failed to get all openEndedAnswers");
            return;
        }
        if(list.isEmpty()) {
            fail("Failed to get all openEndedAnswers correctly");
        }
    }
}
