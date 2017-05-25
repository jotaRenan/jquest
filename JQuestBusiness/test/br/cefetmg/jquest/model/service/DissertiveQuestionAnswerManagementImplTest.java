/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.OpenEndedAnswerDAOImpl;
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
        dissertiveQuestionAnswerDAO = OpenEndedAnswerDAOImpl.getInstance();
        dissertiveQuestionAnswerManagement = new DissertiveQuestionAnswerManagementImpl(dissertiveQuestionAnswerDAO);        
    }
    
    @Before
    public void setUp() {
        this.dissertiveQuestionAnswer = new DissertiveQuestionAnswer(1L, 2L, 3L, "Test", 0);
    }
    
    @Test
    public void testOpenEndedAnswerInsertNull() {
        dissertiveQuestionAnswer = null;
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
            fail("Null openEndedAnswer registered");
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
    }
    
    @Test
    public void testOpenEndedAnswerInsertNullTxtAnswer() {
        dissertiveQuestionAnswer.setTxtAnswer(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
            fail("OpenEndedAnswer with null name registered");
        } catch (BusinessException | PersistenceException ex) {
            dissertiveQuestionAnswer.setTxtAnswer("Test");
            return;
        }
    }
    
    @Test
    public void testOpenEndedAnswerInsertNullUserID() {
        dissertiveQuestionAnswer.setUserID(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
            fail("Tried to insert a openEndedAnswer with a null UserID.");
        } catch (BusinessException | PersistenceException ex) {}
            dissertiveQuestionAnswer.setUserID(0L);
    }
    @Test
    public void testOpenEndedAnswerInsertNullQuestionID() {
        dissertiveQuestionAnswer.setQuestionID(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
            fail("Tried to insert a openEndedAnswer with a null QuestionID.");
        } catch (BusinessException | PersistenceException ex) {}
            dissertiveQuestionAnswer.setQuestionID(0L);
    }
    
    @Test
    public void testOpenEndedAnswerInsertNullSeqAnswerUser() {

        dissertiveQuestionAnswer.setSeqAnswerUser(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
            fail("Tried to insert a openEndedAnswer with a null QuestionID.");
        } catch (BusinessException | PersistenceException ex) {}
            dissertiveQuestionAnswer.setSeqAnswerUser(0L);
    }
    
    @Test
    public void testOpenEndedAnswerInsertCorrect() {
        Long userAnsserSeq = -3L;
        
        try {
            userAnsserSeq = dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
            userAnsserSeq = dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
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
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerRemove(1L, 2L, 3L);
        } catch (PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @Test
    public void testOpenEndedAnswerUpdateNull() {
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dissertiveQuestionAnswer = null;
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
         fail("OpenEndedAnswer updated to null");
        } catch (BusinessException | PersistenceException ex) { }
    }
    
    @Test
    public void testOpenEndedAnswerUpdateNullTxtAwnser() {
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dissertiveQuestionAnswer.setTxtAnswer(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
            fail("OpenEndedAnswer updated with null name");
        } catch (BusinessException | PersistenceException ex) { }
    }
    
    @Test
    public void testOpenEndedAnswerUpdateNullUserID() {
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dissertiveQuestionAnswer.setUserID(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
            fail("Tried to update a openEndedAnswer with a null UserID.");
        } catch (BusinessException | PersistenceException ex) {
            dissertiveQuestionAnswer.setUserID(0L);
        }
    }
    
    @Test
    public void testOpenEndedAnswerUpdateNullQuestionID() {
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dissertiveQuestionAnswer.setQuestionID(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
            fail("Tried to update a openEndedAnswer with a null QuestionID.");
        } catch (BusinessException | PersistenceException ex) {
            dissertiveQuestionAnswer.setQuestionID(0L);
        }
    }
    
    @Test
    public void testOpenEndedAnswerUpdateNullSeqAnswerUser() {
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dissertiveQuestionAnswer.setSeqAnswerUser(null);
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerUpdate(dissertiveQuestionAnswer);
            fail("Tried to update a openEndedAnswer with a null SeqAnswerUser.");
        } catch (BusinessException | PersistenceException ex) {
            dissertiveQuestionAnswer.setSeqAnswerUser(0L);
        }
    }
    
    @Test
    public void testOpenEndedAnswerUpdateCorrect() {
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
            //fail("Failed to update openEndedAnswer");
            return;
        }
        try {
            if (!dissertiveQuestionAnswerManagement.getDissertiveQuestionAnswerById(dissertiveQuestionAnswer.getUserID(), dissertiveQuestionAnswer.getQuestionID(), dissertiveQuestionAnswer.getSeqAnswerUser()).getTxtAnswer().equals("Update test")) {
                fail("Failed to update openEndedAnswer");
            }
        } catch (PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testOpenEndedAnswerRemove() {
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerRemove(dissertiveQuestionAnswer.getUserID(), dissertiveQuestionAnswer.getQuestionID(), dissertiveQuestionAnswer.getSeqAnswerUser());
        } catch (PersistenceException ex) {
            
        fail(ex.getMessage());
        //fail("Failed to remove openEndedAnswer");
        }
    }
    
    @Test
    public void testGetModuleById() {
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        DissertiveQuestionAnswer openEndedAnswerTest;
        try {
            openEndedAnswerTest = dissertiveQuestionAnswerManagement.getDissertiveQuestionAnswerById(dissertiveQuestionAnswer.getUserID(), dissertiveQuestionAnswer.getQuestionID(), dissertiveQuestionAnswer.getSeqAnswerUser());
        } catch (PersistenceException ex) {
            fail(ex.getMessage());
            //fail("Failed to get openEndedAnswer by id");
            return;
        }
        if (!openEndedAnswerTest.equals(dissertiveQuestionAnswer)) {
            fail("Failed to get openEndedAnswer by id");
        }
    }
    
    @Test
    public void testGetAll() {
        DissertiveQuestionAnswer openEndedAnswer2 = new DissertiveQuestionAnswer(3L, 2L, 1L, "Test Again", 0.2);
        List<DissertiveQuestionAnswer> list;
        try {
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(dissertiveQuestionAnswer);
            dissertiveQuestionAnswerManagement.DissertiveQuestionAnswerInsert(openEndedAnswer2);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(DissertiveQuestionAnswerManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list = dissertiveQuestionAnswerManagement.getAll();
        } catch (PersistenceException ex) {
            fail("Failed to get all openEndedAnswers");
            return;
        }
        if(list.isEmpty()) {
            fail("Failed to get all openEndedAnswers correctly");
        }
    }
}
