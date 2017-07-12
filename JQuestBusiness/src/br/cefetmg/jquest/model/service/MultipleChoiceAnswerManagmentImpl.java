
package br.cefetmg.jquest.model.service;

/**
 *
 * @author Breno Mariz
 * 
 * 
 */

import br.cefetmg.jquest.model.dao.MultipleChoiceAnswerDAO;
import br.cefetmg.jquest.model.dao.QuestionDAOImpl;
import br.cefetmg.jquest.model.dao.UserDAOImpl;
import br.cefetmg.jquest.model.domain.MultipleChoiceAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

public class MultipleChoiceAnswerManagmentImpl implements MultipleChoiceAnswerManagement{
    
    private MultipleChoiceAnswerDAO DAO;
    private final QuestionManagement questionManagement;
    private final UserManagement userManagement;
    
    public MultipleChoiceAnswerManagmentImpl(MultipleChoiceAnswerDAO DAO) {
        this.DAO = DAO;
        
        questionManagement = new QuestionManagementImpl(QuestionDAOImpl.getInstance());
        userManagement = new UserManagementImpl(UserDAOImpl.getInstance());
    }

    public void setDAO(MultipleChoiceAnswerDAO DAO) {
        this.DAO = DAO;
    }
    
    

    @Override
    public Long insert(MultipleChoiceAnswer multipleChoiceAnswer) throws BusinessException, PersistenceException {
        if (multipleChoiceAnswer == null) {
            throw new BusinessException("The object multipleChoiceAnswer cannot be null.");
        }

        if(multipleChoiceAnswer.getUserId() == null || userManagement.getUserById(multipleChoiceAnswer.getUserId()) == null)
            throw new BusinessException(" userID doesn't exist.");
        
        if(multipleChoiceAnswer.getQuestionId() == null || questionManagement.getQuestionById(multipleChoiceAnswer.getQuestionId()) == null){
            throw new BusinessException("questionId doesn't exist.");
        }
        DAO.insert(multipleChoiceAnswer);
        return multipleChoiceAnswer.getUserAnswerSeq();
    }

    @Override
    public boolean update(MultipleChoiceAnswer multipleChoiceAnswer) throws BusinessException, PersistenceException {
        if (multipleChoiceAnswer == null) {
            throw new BusinessException("The object multipleChoiceAnswer cannot be null.");
        }
        if (multipleChoiceAnswer.getQuestionId() == null || multipleChoiceAnswer.getUserId() == null || multipleChoiceAnswer.getUserAnswerSeq() == null) {
            throw new BusinessException("None of the questionID or userID or seqAnswer can be null.");
        }

        return DAO.update(multipleChoiceAnswer);
    }

    @Override
    public boolean remove(Long multipleChoiceAnswerId) throws PersistenceException {
        if (multipleChoiceAnswerId == null) {
            throw new PersistenceException("MultipleChoiceAnswerID canot be null.");
        }
        DAO.remove(multipleChoiceAnswerId);  
        return true;
    }

    @Override
    public MultipleChoiceAnswer getAnswerById(Long multipleChoiceAnswerId, Long questionId) throws PersistenceException {
        if(multipleChoiceAnswerId == null || questionId == null) {
            throw new PersistenceException("multipleChoiceAnswerId and questionId can't be null.");
        } 
        
        return DAO.getAnswerById(multipleChoiceAnswerId, questionId);
    }
    
    @Override
    public MultipleChoiceAnswer getAnswersByUserAndQuestionId(Long userId, Long questionId) throws PersistenceException {
        if (userId == null || questionId == null) {
            throw new PersistenceException("userId and questionId can't be null.");
        }

        return DAO.getAnswersByUserAndQuestionId(userId, questionId);
    }
    
    @Override
    public MultipleChoiceAnswer getAllByUserId(Long userId) throws PersistenceException {
        if (userId == null) {
            throw new PersistenceException("userId can't be null.");
        }

        return DAO.getAllByUserId(userId);
    }

    @Override
    public List<MultipleChoiceAnswer> getAll() throws PersistenceException {
        List<MultipleChoiceAnswer> aux = DAO.listAll();
        return aux;
    }
 
}
