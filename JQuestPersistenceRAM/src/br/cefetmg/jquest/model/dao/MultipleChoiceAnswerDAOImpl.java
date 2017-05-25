/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.MultipleChoiceAnswer;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Paula Ribeiro
 */
public class MultipleChoiceAnswerDAOImpl implements MultipleChoiceAnswerDAO {
    
    private static MultipleChoiceAnswerDAOImpl MultipleChoiceAnswerDAO = null;
    private static HashMap<Long, MultipleChoiceAnswer> MultipleChoiceAnswerDB = new HashMap<Long, MultipleChoiceAnswer>();
    private static long trueOrFalseAnswerCount = 0;

    private MultipleChoiceAnswerDAOImpl() {}
    
    public static MultipleChoiceAnswerDAO getInstance() {
        if (MultipleChoiceAnswerDAO == null) {
            MultipleChoiceAnswerDAO = new MultipleChoiceAnswerDAOImpl();
        }
        return MultipleChoiceAnswerDAO;
    }
    
    @Override
    public Long insert(MultipleChoiceAnswer multChoiceAnswerChoice) throws PersistenceException {
        if (multChoiceAnswerChoice == null) {
            throw new PersistenceException("Answer cannot be null");
        }
        Long answerId = multChoiceAnswerChoice.getUseSeq();
        
        if (answerId != null && MultipleChoiceAnswerDB.containsKey(answerId)) {
            throw new PersistenceException("Duplicate key");
        }
        answerId = ++trueOrFalseAnswerCount;
        multChoiceAnswerChoice.setUseSeq(answerId);
        MultipleChoiceAnswerDB.put(answerId, multChoiceAnswerChoice);
        return answerId;
    }

    @Override
    public void update(MultipleChoiceAnswer multChoiceAnswerChoice) throws PersistenceException {
        if (multChoiceAnswerChoice == null) {
            throw new PersistenceException("Answer cannot be null");
        }
        Long answerId = multChoiceAnswerChoice.getUseSeq();
        if (answerId == null) {
            throw new PersistenceException("Entity Id cannot be null");
        }
        if (!MultipleChoiceAnswerDB.containsKey(answerId)) {
            throw new PersistenceException("Answer with id " + multChoiceAnswerChoice.getUseSeq() + " is not persisted");
        }
        MultipleChoiceAnswerDB.replace(answerId, multChoiceAnswerChoice);
    }

    @Override
    public MultipleChoiceAnswer remove(Long multChoiceAnswerChoiceId) throws PersistenceException {
        if (multChoiceAnswerChoiceId == null) {
            throw new PersistenceException("Answer ID cant be null");
        }
        if (!MultipleChoiceAnswerDB.containsKey(multChoiceAnswerChoiceId)){
            throw new PersistenceException("Answer with id " + multChoiceAnswerChoiceId + " is not persisted");
        }
        return MultipleChoiceAnswerDB.remove(multChoiceAnswerChoiceId);
    }

    @Override
    public MultipleChoiceAnswer getToFAnswerById(Long multChoiceAnswerChoiceId) throws PersistenceException {
        if (multChoiceAnswerChoiceId == null) {
            throw new PersistenceException("Answer ID cant be null");
        }
        if (!MultipleChoiceAnswerDB.containsKey(multChoiceAnswerChoiceId)) {
            throw new PersistenceException("Answer with id " + multChoiceAnswerChoiceId + " is not persisted");
        }
        return MultipleChoiceAnswerDB.get(multChoiceAnswerChoiceId);
    }

    @Override
    public List<MultipleChoiceAnswer> listAll() throws PersistenceException {
        List<MultipleChoiceAnswer> MultipleChoiceAnswerList = new ArrayList<>();
        Iterator<MultipleChoiceAnswer> it = MultipleChoiceAnswerDB.values().iterator();
        while (it.hasNext()) {
            MultipleChoiceAnswerList.add(it.next());
        }
        return MultipleChoiceAnswerList;
    }
    
}
