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
    private static MultipleChoiceAnswerDAO multChAnswerChDAO;
    private static MultipleChoiceAnswerManagmentImpl multChAnswerChManag;
    private MultipleChoiceAnswer multChAnswerCh;
    
    public MultipleChoiceAnswerManagmentImplTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        multChAnswerChDAO = MultipleChoiceAnswerDAOImpl.getInstance();
        multChAnswerChManag = new MultipleChoiceAnswerManagmentImpl(multChAnswerChDAO);
    }
    
    @Before
    public void SetUp() {
        this.multChAnswerCh = new MultipleChoiceAnswer(0L, 0L, 0L, 0L,0L);      
    }
    
    @Test
    public void testMultChAnswerChNullInsertion() throws PersistenceException, BusinessException{
        try{
            this.multChAnswerCh = null;
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (BusinessException | PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No MultipleChoiceAnswer was informed";
            assertTrue(msgErr.contains(msgEsperada));
        } catch (NullPointerException ex) {
            fail("Insertion of null MultipleChoiceAnswer");
        }
    }
    
    @Test
    public void testMultChAnswerChNullUpdate() throws PersistenceException, BusinessException {
        try {
            this.multChAnswerCh = null;
            multChAnswerChManag.update(multChAnswerCh);
        } catch (BusinessException | PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No MultipleChoiceAnswer was informed";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Update of null MultipleChoiceAnswer didn't throw an exception");    
    }
    
    @Test
    public void testMultChAnswerChNullOptionSeqRemoval() throws PersistenceException, BusinessException {
        try {
            this.multChAnswerCh.setOptionSeq(null);
            multChAnswerChManag.remove(multChAnswerCh.getOptionSeq());
        } catch (PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No MultipleChoice ID was informed";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Update of null MultipleChoiceAnswer ID");
    }
     
    @Test
    public void testMultChAnswerChGetByNullId() {
        try {
            Long optionSeq = null;
            multChAnswerChManag.getToFAnswerById(optionSeq);
        } catch (PersistenceException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "No MultipleChoiceAnswer ID was informed";
            assertTrue("GetById of null MultipleChoiceAnswer ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("GetById of null MultipleChoiceAnswer ID didnt throw an exception");
    }
    
    @Test
    public void testMultChAnswerChInsertNullUserAnswer() throws PersistenceException {
        try {
            this.multChAnswerCh.setUserAnswerSeq(null);
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No answer to the question was informed";
            assertTrue("Insertion of answer with null User Answer", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswer with null User Answer didnt throw an exception");
    }

    @Test
    public void testMultChAnswerChInsertNullUserId() throws PersistenceException {
        try {
            this.multChAnswerCh.setUserId(null);
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No user id was informed";
            assertTrue("Insertion of MultipleChoiceAnswer with null User ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswer with null User ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerChInsertNullQuestionId() throws PersistenceException {
        try {
            this.multChAnswerCh.setQuestionId(null);
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No question ID was associated to the answer";
            assertTrue("Insertion of MultipleChoiceAnswer with null question ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswer with null question ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerChInsertNullOptionSeq() throws PersistenceException {
        try {
            this.multChAnswerCh.setOptionSeq(null);
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No option ID was informed";
            assertTrue("Insertion of MultipleChoiceAnswer with null option ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswer with null option ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerChInsertNullUseSeq() throws PersistenceException {
        try {
            this.multChAnswerCh.setUseSeq(null);
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No user Sequence was informed";
            assertTrue("Insertion of MultipleChoiceAnswer with userSeq ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswer with userSeq ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerChInsert() {
        try {
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (Exception ex) {
            fail("Insertion of a correct MultipleChoiceAnswer threw an exception");
        }
    }

}
