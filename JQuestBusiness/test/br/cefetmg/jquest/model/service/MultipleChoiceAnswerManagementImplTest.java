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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author Breno Mariz
 */
public class MultipleChoiceAnswerManagementImplTest {
    private static MultipleChoiceAnswerDAO multChAnswerDAO;
    private static MultipleChoiceAnswerManagmentImpl multChAnswerManag;
    private MultipleChoiceAnswer multChAnswer;
    
    public MultipleChoiceAnswerManagementImplTest() {}
    
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
    public void testMultChAnswerNullInsertion() {
        multChAnswer = null;
        try {
            multChAnswerManag.insert(multChAnswer);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Null answer registered");
    }
    
    @Test
    public void testMultChAnswerNullUpdate() throws PersistenceException, BusinessException {
        multChAnswerManag.insert(multChAnswer);
        multChAnswer = null;
        try {
            multChAnswerManag.update(multChAnswer);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Null answer registered"); 
    }
    
    @Test
    public void testMultChAnswerNullOptionSeqRemoval() {
        try {
            this.multChAnswer.setOptionSeq(null);
            multChAnswerManag.remove(multChAnswer.getOptionSeq());
        } catch (PersistenceException ex) {
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
            return;
        }
        fail("Insertion of MultipleChoiceAnswer with null question ID didnt throw an exception");
    }

    @Test
    public void testMultChAnswerInsertNullUseSeq(){
        try {
            this.multChAnswer.setUseSeq(null);
            multChAnswerManag.insert(multChAnswer);
        } catch (BusinessException | PersistenceException ex) {
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
