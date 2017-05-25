
package br.cefetmg.jquest.model.service;

/**
 *
 * @author Breno Mariz
 * 
 * 
 */

import br.cefetmg.jquest.model.dao.MultipleChoiceAnswerChoiceDAO;
import br.cefetmg.jquest.model.domain.MultipleChoiceAnswerChoice;
import br.cefetmg.jquest.model.domain.OpenEndedAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;
import br.cefetmg.jquest.model.dao.DissertiveQuestionAnswerDAO;

public class MultipleChoiceAnswerChoiceManagmentImpl implements MultipleChoiceAnswerChoiceManagement{
    
    private MultipleChoiceAnswerChoiceDAO DAO;
    
    public MultipleChoiceAnswerChoiceManagmentImpl() {};
    
        public MultipleChoiceAnswerChoiceManagmentImpl(MultipleChoiceAnswerChoiceDAO DAO) {
        this.DAO = DAO;
    }

    public void setDAO(MultipleChoiceAnswerChoiceDAO DAO) {
        this.DAO = DAO;
    }
    
    

    @Override
    public Long insert(MultipleChoiceAnswerChoice multChoiceAnswerChoice) throws BusinessException, PersistenceException {
        if (multChoiceAnswerChoice == null) {
            throw new BusinessException("The object multChoiceAnswerChoice cannot be null.");
        }
        if (multChoiceAnswerChoice.getQuestionId() == null || multChoiceAnswerChoice.getUserId() == null || multChoiceAnswerChoice.getUserAnswerSeq() == null) {
            throw new BusinessException("None of the questionID or userID or seqAnswer can be null.");
        }
        if (multChoiceAnswerChoice.equals(new MultipleChoiceAnswerChoice())) {
            throw new BusinessException("The object multChoiceAnswerChoice cannot be empty.");
        }

        DAO.insert(multChoiceAnswerChoice);
        return multChoiceAnswerChoice.getUserAnswerSeq();
    }

    @Override
    public void update(MultipleChoiceAnswerChoice multChoiceAnswerChoice) throws BusinessException, PersistenceException {
        if (multChoiceAnswerChoice == null) {
            throw new BusinessException("The object multChoiceAnswerChoice cannot be null.");
        }
        if (multChoiceAnswerChoice.getQuestionId() == null || multChoiceAnswerChoice.getUserId() == null || multChoiceAnswerChoice.getUserAnswerSeq() == null) {
            throw new BusinessException("None of the questionID or userID or seqAnswer can be null.");
        }
        if (multChoiceAnswerChoice.equals(new MultipleChoiceAnswerChoice())) {
            throw new BusinessException("The object multChoiceAnswerChoice cannot be empty.");
        }
        DAO.update(multChoiceAnswerChoice);
    }

    @Override
    public MultipleChoiceAnswerChoice remove(Long multChoiceAnswerChoiceId) throws PersistenceException {
        if (multChoiceAnswerChoiceId == null) {
            throw new PersistenceException("None of the parameters can be null.");
        }
        return DAO.remove(multChoiceAnswerChoiceId);    
    }

    @Override
    public MultipleChoiceAnswerChoice getToFAnswerById(Long multChoiceAnswerChoiceId) throws PersistenceException {
        if(multChoiceAnswerChoiceId == null) {
            throw new PersistenceException("multChoiceAnswerChoiceId can't be null.");
        } 
        
        return DAO.getToFAnswerById(multChoiceAnswerChoiceId);
    }

    @Override
    public List<MultipleChoiceAnswerChoice> listAll() throws PersistenceException {
        List<MultipleChoiceAnswerChoice> aux = DAO.listAll();
        if(aux.isEmpty()) {
            throw new PersistenceException("There isn't elements in the List.");
        }
        return aux;
    }
    
}
