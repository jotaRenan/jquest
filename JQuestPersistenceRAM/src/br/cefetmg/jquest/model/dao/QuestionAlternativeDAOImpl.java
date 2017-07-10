/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.QuestionAlternative;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Paula Ribeiro
 */
public class QuestionAlternativeDAOImpl implements QuestionAlternativeDAO {
    private static QuestionAlternativeDAOImpl questionAlternativeDAO = null;
    private static HashMap<Long, QuestionAlternative> questionAlternativeDB = new HashMap<Long, QuestionAlternative>();
    private static long cEAlternativeCount = 0;

    private QuestionAlternativeDAOImpl() {}
    
    public static QuestionAlternativeDAOImpl getInstance() {
        if (questionAlternativeDAO == null) {
            questionAlternativeDAO = new QuestionAlternativeDAOImpl();
        }
        return questionAlternativeDAO;
    }
    
    @Override
    public Long insert(QuestionAlternative closedEndedAlt) throws PersistenceException {
        if (closedEndedAlt == null) {
            throw new PersistenceException("Domain cannot be null");
        }
        Long answerId = closedEndedAlt.getOptionSeq();
        
        if (answerId != null && questionAlternativeDB.containsKey(answerId)) {
            throw new PersistenceException("Duplicate key");
        }
        answerId = ++cEAlternativeCount;
        closedEndedAlt.setOptionSeq(answerId);
        questionAlternativeDB.put(answerId, closedEndedAlt);
        return answerId;
    }

    @Override
    public boolean update(QuestionAlternative closedEndedAlt) throws PersistenceException {
        if (closedEndedAlt == null) {
            throw new PersistenceException("Answer cannot be null");
        }
        Long answerId = closedEndedAlt.getOptionSeq();
        if (answerId == null) {
            throw new PersistenceException("Entity Id cannot be null");
        }
        if (!questionAlternativeDB.containsKey(answerId)) {
            throw new PersistenceException("Answer with id " + closedEndedAlt.getOptionSeq()+ " is not persisted");
        }
       questionAlternativeDB.replace(answerId, closedEndedAlt);
       return true;
    }

    @Override
    public boolean remove(Long closedEndedAltId) throws PersistenceException {
        if (closedEndedAltId == null) {
            throw new PersistenceException("Answer ID cant be null");
        }
        if (!questionAlternativeDB.containsKey(closedEndedAltId)){
            throw new PersistenceException("Answer with id " + closedEndedAltId + " is not persisted");
        }
        questionAlternativeDB.remove(closedEndedAltId);
        return true;
    }

    @Override
    public QuestionAlternative getQuestionAlternativeById(Long closedEndedAltId) throws PersistenceException {
        if (closedEndedAltId == null) {
            throw new PersistenceException("Answer ID cant be null");
        }
        if (!questionAlternativeDB.containsKey(closedEndedAltId)) {
            throw new PersistenceException("Answer with id " + closedEndedAltId + " is not persisted");
        }
        return questionAlternativeDB.get(closedEndedAltId);
    }

    @Override
    public List<QuestionAlternative> listAll() throws PersistenceException {
        List<QuestionAlternative> aux = new ArrayList(questionAlternativeDB.values());        
        return aux;
    }
}
