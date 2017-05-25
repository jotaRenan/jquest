/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.MultipleChoiceAnswerDAO;
import br.cefetmg.jquest.model.dao.MultipleChoiceAnswerDAOImpl;
import br.cefetmg.jquest.model.domain.MultipleChoiceAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Breno Mariz
 */
public class MultipleChoiceAnswerManagmentImplTest {
    private static MultipleChoiceAnswerDAO multChAnswerDAO;
    private static MultipleChoiceAnswerManagmentImpl multChAnswerManag;
    private MultipleChoiceAnswer multChAnswer;
    
    public MultipleChoiceAnswerManagmentImplTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        multChAnswerDAO = MultipleChoiceAnswerDAOImpl.getInstance();
        multChAnswerManag = new MultipleChoiceAnswerManagmentImpl(multChAnswerDAO);
    }
    
    @Before
    public void SetUp() {
        this.multChAnswer = new MultipleChoiceAnswer(0L, 0L, 0L, 0L,0L);      
    }
    
    @Test
    public void testMultChAnswerNullInsertion() throws PersistenceException, BusinessException{
        try{
            this.multChAnswer = null;
            multChAnswerManag.insert(multChAnswer);
        } catch (BusinessException | PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No MultipleChoiceAnswer was informed";
            assertTrue(msgErr.contains(msgEsperada));
        } catch (NullPointerException ex) {
            fail("Insertion of null MultipleChoiceAnswer");
        }
    }
    
    @Test
    public void testMultChAnswerNullUpdate() throws PersistenceException, BusinessException {
        try {
            this.multChAnswer = null;
            multChAnswerManag.update(multChAnswer);
        } catch (BusinessException | PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No MultipleChoiceAnswer was informed";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Update of null MultipleChoiceAnswer didn't throw an exception");    
    }
    
    @Test
    public void testMultChAnswerNullOptionSeqRemoval() throws PersistenceException, BusinessException {
        try {
            this.multChAnswer.setOptionSeq(null);
            multChAnswerManag.remove(multChAnswer.getOptionSeq());
        } catch (PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No MultipleChoice ID was informed";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Update of null MultipleChoiceAnswer ID");
    }
     
    @Test
    public void testMultChAnswerGetByNullId() {
        try {
            Long optionSeq = null;
            multChAnswerManag.getToFAnswerById(optionSeq);
        } catch (PersistenceException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "No MultipleChoiceAnswer ID was informed";
            assertTrue("GetById of null MultipleChoiceAnswer ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("GetById of null MultipleChoiceAnswer ID didnt throw an exception");
    }
    
    @Test
    public void testMultChAnswerInsertNullUserAnswer() throws PersistenceException {
        try {
            this.multChAnswer.setUserAnswerSeq(null);
            multChAnswerManag.insert(multChAnswer);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No answer to the question was informed";
            assertTrue("Insertion of answer with null User Answer", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswer with null User Answer didnt throw an exception");
    }

    @Test
    public void testMultChAnswerInsertNullUserId() throws PersistenceException {
        try {
            this.multChAnswer.setUserId(null);
            multChAnswerManag.insert(multChAnswer);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No user id was informed";
            assertTrue("Insertion of MultipleChoiceAnswer with null User ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswer with null User ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerInsertNullQuestionId() throws PersistenceException {
        try {
            this.multChAnswer.setQuestionId(null);
            multChAnswerManag.insert(multChAnswer);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No question ID was associated to the answer";
            assertTrue("Insertion of MultipleChoiceAnswer with null question ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswer with null question ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerInsertNullOptionSeq() throws PersistenceException {
        try {
            this.multChAnswer.setOptionSeq(null);
            multChAnswerManag.insert(multChAnswer);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No option ID was informed";
            assertTrue("Insertion of MultipleChoiceAnswer with null option ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswer with null option ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerInsertNullUseSeq() throws PersistenceException {
        try {
            this.multChAnswer.setUseSeq(null);
            multChAnswerManag.insert(multChAnswer);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No user Sequence was informed";
            assertTrue("Insertion of MultipleChoiceAnswer with userSeq ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswer with userSeq ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerInsert() {
        try {
            multChAnswerManag.insert(multChAnswer);
        } catch (Exception ex) {
            fail("Insertion of a correct MultipleChoiceAnswer threw an exception");
        }
    }

}
