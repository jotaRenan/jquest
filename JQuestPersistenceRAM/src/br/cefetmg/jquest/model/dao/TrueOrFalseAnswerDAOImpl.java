/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;
import br.cefetmg.jquest.model.domain.TrueOrFalseAnswer;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Paula Ribeiro
 */
public class TrueOrFalseAnswerDAOImpl implements TrueOrFalseAnswerDAO {

    private static TrueOrFalseAnswerDAOImpl trueOrFalseAnswerDAO = null;
    private static HashMap<Long, TrueOrFalseAnswer> trueOrFalseAnswerDB = new HashMap<Long, TrueOrFalseAnswer>();
    private static long trueOrFalseAnswerCount = 0;

    private TrueOrFalseAnswerDAOImpl() {}
    
    public static TrueOrFalseAnswerDAOImpl getInstance() {
        if (trueOrFalseAnswerDAO == null) {
            trueOrFalseAnswerDAO = new TrueOrFalseAnswerDAOImpl();
        }
        return trueOrFalseAnswerDAO;
    }
    
    @Override
    public Long insert(TrueOrFalseAnswer tofAnswer) throws PersistenceException {
        if (tofAnswer == null) {
            throw new PersistenceException("Answer cannot be null");
        }
        Long answerId = tofAnswer.getUseSeq();
        
        if (answerId != null && trueOrFalseAnswerDB.containsKey(answerId)) {
            throw new PersistenceException("Duplicate key");
        }
        answerId = ++trueOrFalseAnswerCount;
        tofAnswer.setUseSeq(answerId);
        trueOrFalseAnswerDB.put(answerId, tofAnswer);
        return answerId;
    }

    @Override
    public void update(TrueOrFalseAnswer tofAnswer) throws PersistenceException {
        if (tofAnswer == null) {
            throw new PersistenceException("Answer cannot be null");
        }
        Long answerId = tofAnswer.getUseSeq();
        if (answerId == null) {
            throw new PersistenceException("Entity Id cannot be null");
        }
        if (!trueOrFalseAnswerDB.containsKey(answerId)) {
            throw new PersistenceException("Answer with id " + tofAnswer.getUseSeq() + " is not persisted");
        }
        trueOrFalseAnswerDB.replace(answerId, tofAnswer);
    }

    @Override
    public TrueOrFalseAnswer remove(Long tofAnswerId) throws PersistenceException {
        if (tofAnswerId == null) {
            throw new PersistenceException("Answer ID cant be null");
        }
        if (!trueOrFalseAnswerDB.containsKey(tofAnswerId)){
            throw new PersistenceException("Answer with id " + tofAnswerId + " is not persisted");
        }
        return trueOrFalseAnswerDB.remove(tofAnswerId);

    }

    @Override
    public TrueOrFalseAnswer getToFAnswerById(Long tofAnswerId) throws PersistenceException {
        if (tofAnswerId == null) {
            throw new PersistenceException("Answer ID cant be null");
        }
        if (!trueOrFalseAnswerDB.containsKey(tofAnswerId)) {
            throw new PersistenceException("Answer with id " + tofAnswerId + " is not persisted");
        }
        return trueOrFalseAnswerDB.remove(tofAnswerId);
    }

    @Override
    public List<TrueOrFalseAnswer> listAll() throws PersistenceException {
        return new ArrayList<>(trueOrFalseAnswerDB.values());
    }
    
}
