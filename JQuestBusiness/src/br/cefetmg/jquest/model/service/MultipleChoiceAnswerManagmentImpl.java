
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
    public boolean remove(Long COD_userIDUseLog, Long SEQ_use, Long questionId, Long SEQ_useAnswer) throws PersistenceException {
        if (COD_userIDUseLog == null ) {
            throw new PersistenceException("UserID canot be null.");
        }
        if (SEQ_use == null ) {
            throw new PersistenceException("SeqUse canot be null.");
        }
        if (questionId == null ) {
            throw new PersistenceException("questionID canot be null.");
        }
        if (SEQ_useAnswer == null ) {
            throw new PersistenceException("SEQ_useAnswer canot be null.");
        }
        DAO.remove(COD_userIDUseLog, SEQ_use, questionId, SEQ_useAnswer);  
        return true;
    }

    @Override
    public MultipleChoiceAnswer getAnswerById(Long COD_userIDUseLog, Long SEQ_use, Long questionId, Long SEQ_useAnswer) throws PersistenceException {
        if (COD_userIDUseLog == null ) {
            throw new PersistenceException("UserID canot be null.");
        }
        if (SEQ_use == null ) {
            throw new PersistenceException("SeqUse canot be null.");
        }
        if (questionId == null ) {
            throw new PersistenceException("questionID canot be null.");
        }
        if (SEQ_useAnswer == null ) {
            throw new PersistenceException("SEQ_useAnswer canot be null.");
        }
        
        return DAO.getAnswerById(COD_userIDUseLog, SEQ_use, questionId, SEQ_useAnswer);
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
 
}
