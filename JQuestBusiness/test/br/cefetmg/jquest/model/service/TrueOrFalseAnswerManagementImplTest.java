/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.TrueOrFalseAnswerDAOImpl;
import br.cefetmg.jquest.model.domain.TrueOrFalseAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Joao Pedro Renan
 */
public class TrueOrFalseAnswerManagementImplTest {
    private static TrueOrFalseAnswerManagementImpl tofaManagement;
    private static TrueOrFalseAnswerDAOImpl tofaDAO;
    private TrueOrFalseAnswer tofa;
    
    public TrueOrFalseAnswerManagementImplTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        tofaDAO = TrueOrFalseAnswerDAOImpl.getInstance();
        tofaManagement = new TrueOrFalseAnswerManagementImpl(tofaDAO);
    }
    
    @Before
    public void setUp() {
        this.tofa = new TrueOrFalseAnswer(0L, 0L, 0L, 0L, 0L);
    }
    
    @Test
    public void testTOFANullInsertion() throws PersistenceException, BusinessException {
        try {
            this.tofa = null;
            tofaManagement.insert(tofa);
        } catch (BusinessException | PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No TrueOrFalseAnswer was informed";
            assertTrue(msgErr.contains(msgEsperada));
        } catch (NullPointerException ex) {
            fail("Insertion of null TrueOrFalseAnswer");
        }
    }
    
    @Test
    public void testTOFANullUpdate() throws PersistenceException, BusinessException {
        try {
            this.tofa = null;
            tofaManagement.update(tofa);
        } catch (BusinessException | PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No TrueOrFalseAnswer was informed";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Update of null TrueOrFalseAnswer didnt throw an exception");
    }
    
    @Test
    public void testTOFANullOptionSeqRemoval() throws PersistenceException, BusinessException {
        try {
            this.tofa.setOptionSeq(null);
            tofaManagement.remove(tofa.getOptionSeq());
        } catch (BusinessException | PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No Answer ID was informed";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Update of null TrueOrFalseAnswer ID");
    }
    
    @Test
    public void testToFAGetToFAnswerByNullId() {
        try {
            Long optionSeq = null;
            tofaManagement.getToFAnswerById(optionSeq);
        } catch (BusinessException | PersistenceException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "No Answer ID was informed";
            assertTrue("GetById of null TrueOrFalseAnswer ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("GetById of null TrueOrFalseAnswer ID didnt throw an exception");
    }
    
    @Test
    public void testToFAInsertNullUserAnswer() throws PersistenceException {
        try {
            this.tofa.setUserAnswer(null);
            tofaManagement.insert(tofa);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No answer to the question was informed";
            assertTrue("Insertion of TOFA with null User Answer", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of TOFA with null User Answer didnt throw an exception");
    }
    
    @Test
    public void testToFAInsertNullUserId() throws PersistenceException {
        try {
            this.tofa.setUserId(null);
            tofaManagement.insert(tofa);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No user id was informed";
            assertTrue("Insertion of TOFA with null User ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of TOFA with null User ID didnt throw an exception");
    }
    
    @Test
    public void testToFAInsertNullQuestionId() throws PersistenceException {
        try {
            this.tofa.setQuestionId(null);
            tofaManagement.insert(tofa);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No question ID was associated to the answer";
            assertTrue("Insertion of TOFA with null question ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of TOFA with null question ID didnt throw an exception");
    }
    
    @Test
    public void testToFAInsertNullOptionSeq() throws PersistenceException {
        try {
            this.tofa.setOptionSeq(null);
            tofaManagement.insert(tofa);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No option ID was informed";
            assertTrue("Insertion of TOFA with null option ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of TOFA with null option ID didnt throw an exception");
    }
    
    @Test
    public void testToFAInsertNullUseSeq() throws PersistenceException {
        try {
            this.tofa.setUseSeq(null);
            tofaManagement.insert(tofa);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No user Sequence was informed";
            assertTrue("Insertion of TOFA with userSeq ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of TOFA with userSeq ID didnt throw an exception");
    }
    
    @Test
    public void testCorrectToFAInsert() {
        try {
            tofaManagement.insert(tofa);
        } catch(Exception ex) {
            fail("Insertion of a correct ToFA threw an exception");
        }
    }
}
