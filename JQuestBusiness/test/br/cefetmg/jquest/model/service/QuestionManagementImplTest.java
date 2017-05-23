package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.QuestionDAO;
import br.cefetmg.jquest.model.dao.QuestionDAOImpl;
import br.cefetmg.jquest.model.domain.Domain;
import br.cefetmg.jquest.model.domain.Module;
import br.cefetmg.jquest.model.domain.Question;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paula Ribeiro
 */
public class QuestionManagementImplTest {
    private static QuestionManagement questionManagement;
    private static QuestionDAO questionDAO;
    private Question question;

    public QuestionManagementImplTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        questionDAO = QuestionDAOImpl.getInstance();
        questionManagement = new QuestionManagementImpl(questionDAO);
    }
    
    @Before
    public void setUp() {
        this.question = new Question();
        question.setDificulty("easy");
        question.setDomainId(1L);
        question.setModuleId(1L);
        question.setHeadline("Enunciado teste");
        question.setType('o');
    }
    
    @Test
    public void testQuestionInsertNull() {
        question = null;
        try {
            questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Null question registered");
    }
    
    @Test
    public void testQuestionInsertNullHeadline() {
        question.setHeadline(null);
        try {
            questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Question with null headline registered");
    }
    
    @Test
    public void testQuestionInsertNullDomainId() {
        question.setDomainId(null);
        try {
            questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Question with null domain registered");
    }
    
    @Test
    public void testQuestionInsertNullModuleId() {
        question.setModuleId(null);
        try {
            questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Question with null module registered");
    }
    
    @Test
    public void testQuestionInsertNullType() {
        question.setType(' ');
        try {
            questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Question with null type registered");
    }
    
    @Test
    public void testQuestionInsertCorrect() {
        Long id = -1L;
        try {
            id = questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            fail("Question failed to be registered");
            return;
        }
        if (id == -1L) {
            fail("Question failed to be registered");
            return;
        }
        
        //removes registered question
        try {
            questionManagement.questionRemove(id);
        } catch (PersistenceException ex) {
            Logger.getLogger(DomainManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
