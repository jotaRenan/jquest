
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
    public Long insert(MultipleChoiceAnswer multChoiceAnswerChoice) throws BusinessException, PersistenceException {
        if (multChoiceAnswerChoice == null) {
            throw new BusinessException("The object multChoiceAnswerChoice cannot be null.");
        }
        if (multChoiceAnswerChoice.getQuestionId() == null || multChoiceAnswerChoice.getUserId() == null || multChoiceAnswerChoice.getUserAnswerSeq() == null) {
            throw new BusinessException("None of the questionID or userID or seqAnswer can be null.");
        }
        if (multChoiceAnswerChoice.equals(new MultipleChoiceAnswer())) {
            throw new BusinessException("The object multChoiceAnswerChoice cannot be empty.");
        }

        DAO.insert(multChoiceAnswerChoice);
        return multChoiceAnswerChoice.getUserAnswerSeq();
    }

    @Override
    public void update(MultipleChoiceAnswer multChoiceAnswerChoice) throws BusinessException, PersistenceException {
        if (multChoiceAnswerChoice == null) {
            throw new BusinessException("The object multChoiceAnswerChoice cannot be null.");
        }
        if (multChoiceAnswerChoice.getQuestionId() == null || multChoiceAnswerChoice.getUserId() == null || multChoiceAnswerChoice.getUserAnswerSeq() == null) {
            throw new BusinessException("None of the questionID or userID or seqAnswer can be null.");
        }
        if (multChoiceAnswerChoice.equals(new MultipleChoiceAnswer())) {
            throw new BusinessException("The object multChoiceAnswerChoice cannot be empty.");
        }
        DAO.update(multChoiceAnswerChoice);
    }

    @Override
    public MultipleChoiceAnswer remove(Long multChoiceAnswerChoiceId) throws PersistenceException {
        if (multChoiceAnswerChoiceId == null) {
            throw new PersistenceException("None of the parameters can be null.");
        }
        return DAO.remove(multChoiceAnswerChoiceId);    
    }

    @Override
    public MultipleChoiceAnswer getToFAnswerById(Long multChoiceAnswerChoiceId) throws PersistenceException {
        if(multChoiceAnswerChoiceId == null) {
            throw new PersistenceException("multChoiceAnswerChoiceId can't be null.");
        } 
        
        return DAO.getToFAnswerById(multChoiceAnswerChoiceId);
    }

    @Override
    public List<MultipleChoiceAnswer> listAll() throws PersistenceException {
        List<MultipleChoiceAnswer> aux = DAO.listAll();
        if(aux.isEmpty()) {
            throw new PersistenceException("There isn't elements in the List.");
        }
        return aux;
    }
    
}
