/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.QuestionAlternativeDAOImpl;
import br.cefetmg.jquest.model.domain.QuestionAlternative;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Haddad
 */

public class QuestionAlternativeImplTest {
    private static QuestionAlternativeManagementImpl questionAltManagement;
    private static QuestionAlternativeDAOImpl questionAltDAO;
    private QuestionAlternative questionAlt;
    
    public QuestionAlternativeImplTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        questionAltDAO = QuestionAlternativeDAOImpl.getInstance();
        questionAltManagement = new QuestionAlternativeManagementImpl(questionAltDAO);
    }
    
    @Before
    public void setUp() {
        questionAlt = new QuestionAlternative(1L, 0L , "Teste", true);
    }
    
    @Test
    public void testQuestionAltNullInsertion() throws PersistenceException {
        try{
            this.questionAlt = null;
            questionAltManagement.insert(questionAlt);
        }catch (BusinessException | PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "Alternative cannot be null";
            assertTrue(msgErr.contains(msgEsperada));
        }catch (NullPointerException ex) {
            fail("Insertion of null QuestionAlternative");
        }
    }

    @Test
    public void testQuestionAltInsertNullQuestionId() throws PersistenceException {
        try {
            this.questionAlt.setQuestionId(null);
            questionAltManagement.insert(questionAlt);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "Question Id cannot be null";
            assertTrue("Insertion of QuestionAlternative with null question ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of QuestionAlternative with null Question ID didnt throw an exception");
    }
    
    @Test
    public void testQuestionAltInsertNullAssertion() throws PersistenceException {
        try {
            this.questionAlt.setAssertionText(null);
            questionAltManagement.insert(questionAlt);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "Assertion cannot be null";
            assertTrue("Insertion of QuestionAlternative with null assertion", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of QuestionAlternative with null assertion didnt throw an exception");
    }
    
    @Test
    public void testQuestionAltNullUpdate() throws PersistenceException, BusinessException {
        try {
            this.questionAlt = null;
            questionAltManagement.update(questionAlt);
        } catch (BusinessException | PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "Alternative cannot be null";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Update of null QuestionAlternative didnt throw an exception");
    }
    
    @Test
    public void testQuestionAltGetQuestionAltByNullId() {
        try {
            Long id = null;
            Long closedEndedAltId = null;
            questionAltManagement.getQuestionAlternativeById(id, closedEndedAltId);
        } catch (PersistenceException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "Answer's id cannot be null";
            assertTrue("GetById of null QuestionAlternative ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("GetById of null QuestionAlternative ID didnt throw an exception");
    }

    @Test
    public void testQuestionAltNullQuestionIdRemoval() throws PersistenceException, BusinessException {
        try {
            this.questionAlt.setQuestionId(null);
            this.questionAlt.setOptionSeq(null);
            questionAltManagement.remove(questionAlt.getQuestionId(), questionAlt.getOptionSeq());
        } catch (PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "Answer's id cannot be null";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Removal of null QuestionAlternative ID");
    }
    
    @Test
    public void testCorrectQuestionAltInsert() {
        try {
            questionAltManagement.insert(questionAlt);
        } catch(Exception ex) {
            fail("Insertion of a correct QuestionAlternative threw an exception");
        }
    }
}
