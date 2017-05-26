
package br.cefetmg.jquest.model.service;

/**
 *
 * @author Breno Mariz
 * 
 * 
 */

import br.cefetmg.jquest.model.dao.MultipleChoiceAnswerDAO;
import br.cefetmg.jquest.model.domain.MultipleChoiceAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

public class MultipleChoiceAnswerManagmentImpl implements MultipleChoiceAnswerManagement{
    
    private MultipleChoiceAnswerDAO DAO;
    
    public MultipleChoiceAnswerManagmentImpl() {};
    
        public MultipleChoiceAnswerManagmentImpl(MultipleChoiceAnswerDAO DAO) {
        this.DAO = DAO;
    }

    public void setDAO(MultipleChoiceAnswerDAO DAO) {
        this.DAO = DAO;
    }
    
    

    @Override
    public Long insert(MultipleChoiceAnswer multipleChoiceAnswer) throws BusinessException, PersistenceException {
        if (multipleChoiceAnswer == null) {
            throw new BusinessException("The object multipleChoiceAnswer cannot be null.");
        }
        if (multipleChoiceAnswer.getQuestionId() == null || multipleChoiceAnswer.getUserId() == null || multipleChoiceAnswer.getUserAnswerSeq() == null || multipleChoiceAnswer.getUseSeq() == null) {
            throw new BusinessException("None of the questionID or userID or seqAnswer or useSeq can be null.");
        }

        DAO.insert(multipleChoiceAnswer);
        return multipleChoiceAnswer.getUserAnswerSeq();
    }

    @Override
    public void update(MultipleChoiceAnswer multipleChoiceAnswer) throws BusinessException, PersistenceException {
        if (multipleChoiceAnswer == null) {
            throw new BusinessException("The object multipleChoiceAnswer cannot be null.");
        }
        if (multipleChoiceAnswer.getQuestionId() == null || multipleChoiceAnswer.getUserId() == null || multipleChoiceAnswer.getUserAnswerSeq() == null) {
            throw new BusinessException("None of the questionID or userID or seqAnswer can be null.");
        }

        DAO.update(multipleChoiceAnswer);
    }

    @Override
    public MultipleChoiceAnswer remove(Long multipleChoiceAnswerId) throws PersistenceException {
        if (multipleChoiceAnswerId == null) {
            throw new PersistenceException("None of the parameters can be null.");
        }
        return DAO.remove(multipleChoiceAnswerId);    
    }

    @Override
    public MultipleChoiceAnswer getToFAnswerById(Long multipleChoiceAnswerId) throws PersistenceException {
        if(multipleChoiceAnswerId == null) {
            throw new PersistenceException("multipleChoiceAnswerId can't be null.");
        } 
        
        return DAO.getToFAnswerById(multipleChoiceAnswerId);
    }

    @Override
    public List<MultipleChoiceAnswer> getAll() throws PersistenceException {
        List<MultipleChoiceAnswer> aux = DAO.listAll();
        return aux;
    }
    
}
