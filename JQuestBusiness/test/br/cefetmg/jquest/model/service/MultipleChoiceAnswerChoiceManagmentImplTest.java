/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.MultipleChoiceAnswerChoiceDAO;
import br.cefetmg.jquest.model.dao.MultipleChoiceAnswerChoiceDAOImpl;
import br.cefetmg.jquest.model.domain.MultipleChoiceAnswerChoice;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Breno Mariz
 */
public class MultipleChoiceAnswerChoiceManagmentImplTest {
    private static MultipleChoiceAnswerChoiceDAO multChAnswerChDAO;
    private static MultipleChoiceAnswerChoiceManagmentImpl multChAnswerChManag;
    private MultipleChoiceAnswerChoice multChAnswerCh;
    
    public MultipleChoiceAnswerChoiceManagmentImplTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        multChAnswerChDAO = MultipleChoiceAnswerChoiceDAOImpl.getInstance();
        multChAnswerChManag = new MultipleChoiceAnswerChoiceManagmentImpl(multChAnswerChDAO);
    }
    
    @Before
    public void SetUp() {
        this.multChAnswerCh = new MultipleChoiceAnswerChoice(0L, 0L, 0L, 0L,0L);      
    }
    
    @Test
    public void testMultChAnswerChNullInsertion() throws PersistenceException, BusinessException{
        try{
            this.multChAnswerCh = null;
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (BusinessException | PersistenceException ex) {
            
            
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No MultipleChoiceAnswerChoice was informed";
            assertTrue(msgErr.contains(msgEsperada));
        } catch (NullPointerException ex) {
            fail("Insertion of null MultipleChoiceAnswerChoice");
        }
    }
    
    @Test
    public void testMultChAnswerChNullUpdate() throws PersistenceException, BusinessException {
        try {
            this.multChAnswerCh = null;
            multChAnswerChManag.update(multChAnswerCh);
        } catch (BusinessException | PersistenceException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No MultipleChoiceAnswerChoice was informed";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        fail("Update of null MultipleChoiceAnswerChoice didn't throw an exception");    
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
        fail("Update of null MultipleChoiceAnswerChoice ID");
    }
     
    @Test
    public void testMultChAnswerChGetByNullId() {
        try {
            Long optionSeq = null;
            multChAnswerChManag.getToFAnswerById(optionSeq);
        } catch (PersistenceException ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "No MultipleChoiceAnswerChoice ID was informed";
            assertTrue("GetById of null MultipleChoiceAnswerChoice ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("GetById of null MultipleChoiceAnswerChoice ID didnt throw an exception");
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
        fail("Insertion of MultipleChoiceAnswerChoice with null User Answer didnt throw an exception");
    }

    @Test
    public void testMultChAnswerChInsertNullUserId() throws PersistenceException {
        try {
            this.multChAnswerCh.setUserId(null);
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No user id was informed";
            assertTrue("Insertion of MultipleChoiceAnswerChoice with null User ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswerChoice with null User ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerChInsertNullQuestionId() throws PersistenceException {
        try {
            this.multChAnswerCh.setQuestionId(null);
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No question ID was associated to the answer";
            assertTrue("Insertion of MultipleChoiceAnswerChoice with null question ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswerChoice with null question ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerChInsertNullOptionSeq() throws PersistenceException {
        try {
            this.multChAnswerCh.setOptionSeq(null);
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No option ID was informed";
            assertTrue("Insertion of MultipleChoiceAnswerChoice with null option ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswerChoice with null option ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerChInsertNullUseSeq() throws PersistenceException {
        try {
            this.multChAnswerCh.setUseSeq(null);
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (BusinessException ex) {
            ArrayList<String> msgErr = new ArrayList<>(Arrays.asList(ex.getMessage().split("\n")));
            String msgEsperada = "No user Sequence was informed";
            assertTrue("Insertion of MultipleChoiceAnswerChoice with userSeq ID", msgErr.contains(msgEsperada));
            return;
        }
        fail("Insertion of MultipleChoiceAnswerChoice with userSeq ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerChInsert() {
        try {
            multChAnswerChManag.insert(multChAnswerCh);
        } catch (Exception ex) {
            fail("Insertion of a correct MultipleChoiceAnswerChoice threw an exception");
        }
    }

}
