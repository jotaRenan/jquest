package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.QuestionDAO;
import br.cefetmg.jquest.model.dao.QuestionDAOImpl;
import br.cefetmg.jquest.model.domain.Question;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;
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
        question.setDificulty('F');
        question.setDomainId(1L);
        question.setModuleId(1L);
        question.setHeadline("Enunciado teste");
        question.setType('O'); // A = Aberta M = Multipla Escolha V = V ou F
        question.setUserId(1L);
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
            Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @Test
    public void testQuestionUpdateNull() {
        Long id = null;
        try {
            id = questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        question = null;
        try {
            questionManagement.questionUpdate(question);
        } catch (BusinessException | PersistenceException ex) {
            //removes registered question
            try {
                questionManagement.questionRemove(id);
            } catch (PersistenceException e) {
                Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, e);
            } 
            return;
        }
        fail("Question updated to null");
    }
    
    @Test
    public void testQuestionUpdateNullHeadline() {
        try {
            questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        question.setHeadline(null);
        try {
            questionManagement.questionUpdate(question);
        } catch (BusinessException | PersistenceException ex) {
            //removes registered question
            try {
                questionManagement.questionRemove(question.getId());
            } catch (PersistenceException e) {
                Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, e);
            } 
            return;
        }
        fail("Question updated with null name");
    }
    
    @Test
    public void testQuestionUpdateNullDificulty() {
        try {
            questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        question.setDificulty(' ');
        try {
            questionManagement.questionUpdate(question);
        } catch (BusinessException | PersistenceException ex) {
            //removes registered question
            try {
                questionManagement.questionRemove(question.getId());
            } catch (PersistenceException e) {
                Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, e);
            } 
            return;
        }
        fail("Question updated with null description");
    }
    
    @Test
    public void testQuestionUpdateNullDomainId() {
        question.setDomainId(null);
        try {
            questionManagement.questionUpdate(question);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Question with null domain registered");
    }
    
    @Test
    public void testQuestionUpdateNullModuleId() {
        question.setModuleId(null);
        try {
            questionManagement.questionUpdate(question);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Question with null module registered");
    }
    
    @Test
    public void testQuestionUpdateNullType() {
        question.setType(' ');
        try {
            questionManagement.questionUpdate(question);
        } catch (BusinessException | PersistenceException ex) {
            return;
        }
        fail("Question with null type registered");
    }
    
    @Test
    public void testQuestionUpdateNullId() {
        Long id = null;
        try {
            id = questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        question.setId(null);
        try {
            questionManagement.questionUpdate(question);
        } catch (BusinessException | PersistenceException ex) {
            //removes registered question
            try {
                questionManagement.questionRemove(id);
            } catch (PersistenceException e) {
                Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, e);
            } 
            return;
        }
        fail("Tried to update a question with a null id");
    }
    
    @Test
    public void testQuestionUpdateCorrect() {
        try {
            questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        question.setHeadline("Update test");
        try {
            questionManagement.questionUpdate(question);
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to update question");
            return;
        }
        try {
            if (!questionManagement.getQuestionById(question.getId()).getHeadline().equals("Update test")) {
                fail("Failed to update question");
            }
        } catch (PersistenceException ex) {
            Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            //removes registered question
            try {
                questionManagement.questionRemove(question.getId());
            } catch (PersistenceException e) {
                Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, e);
            } 
        }
    }
    
    @Test
    public void testQuestionRemove() {
        try {
            questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            questionManagement.questionRemove(question.getId());
        } catch (PersistenceException ex) {
            fail("Failed to remove question");
        }
    }
    
    @Test
    public void testGetQuestionById() {
        try {
            questionManagement.questionInsert(question);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Question questionTest;
        try {
            questionTest = questionManagement.getQuestionById(question.getId());
        } catch (PersistenceException ex) {
            fail("Failed to get question by id");
        }
        finally {
            //removes registered question
            try {
                questionManagement.questionRemove(question.getId());
            } catch (PersistenceException e) {
                Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, e);
            } 
        }
    }
    
    @Test
    public void testGetAll() {
        Question question2 = question;
        List<Question> list;
        Long id1 = null;
        Long id2 = null;
        try {
            id1 = questionManagement.questionInsert(question);
            id2 = questionManagement.questionInsert(question2);
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list = questionManagement.getAll();
        } catch (PersistenceException ex) {
            fail("Failed to get all questions");
            return;
        }
        if(list.isEmpty()) {
            fail("Failed to get all questions correctly");
        }
        //removes registered question
        try {
            questionManagement.questionRemove(id1);
            questionManagement.questionRemove(id2);
        } catch (PersistenceException e) {
            Logger.getLogger(QuestionManagementImplTest.class.getName()).log(Level.SEVERE, null, e);
        } 
    }
    
    
}
