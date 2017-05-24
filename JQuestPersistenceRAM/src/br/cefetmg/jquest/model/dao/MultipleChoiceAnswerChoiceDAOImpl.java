/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.MultipleChoiceAnswerChoice;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Paula Ribeiro
 */
public class MultipleChoiceAnswerChoiceDAOImpl implements MultipleChoiceAnswerChoiceDAO {
    
    private static MultipleChoiceAnswerChoiceDAOImpl multipleChoiceAnswerChoiceDAO = null;
    private static HashMap<Long, MultipleChoiceAnswerChoice> multipleChoiceAnswerChoiceDB = new HashMap<Long, MultipleChoiceAnswerChoice>();
    private static long trueOrFalseAnswerCount = 0;

    private MultipleChoiceAnswerChoiceDAOImpl() {}
    
    public static MultipleChoiceAnswerChoiceDAO getInstance() {
        if (multipleChoiceAnswerChoiceDAO == null) {
            multipleChoiceAnswerChoiceDAO = new MultipleChoiceAnswerChoiceDAOImpl();
        }
        return multipleChoiceAnswerChoiceDAO;
    }
    
    @Override
    public Long insert(MultipleChoiceAnswerChoice multChoiceAnswerChoice) throws PersistenceException {
        if (multChoiceAnswerChoice == null) {
            throw new PersistenceException("Answer cannot be null");
        }
        Long answerId = multChoiceAnswerChoice.getUseSeq();
        
        if (answerId != null && multipleChoiceAnswerChoiceDB.containsKey(answerId)) {
            throw new PersistenceException("Duplicate key");
        }
        answerId = ++trueOrFalseAnswerCount;
        multChoiceAnswerChoice.setUseSeq(answerId);
        multipleChoiceAnswerChoiceDB.put(answerId, multChoiceAnswerChoice);
        return answerId;
    }

    @Override
    public void update(MultipleChoiceAnswerChoice multChoiceAnswerChoice) throws PersistenceException {
        if (multChoiceAnswerChoice == null) {
            throw new PersistenceException("Answer cannot be null");
        }
        Long answerId = multChoiceAnswerChoice.getUseSeq();
        if (answerId == null) {
            throw new PersistenceException("Entity Id cannot be null");
        }
        if (!multipleChoiceAnswerChoiceDB.containsKey(answerId)) {
            throw new PersistenceException("Answer with id " + multChoiceAnswerChoice.getUseSeq() + " is not persisted");
        }
        multipleChoiceAnswerChoiceDB.replace(answerId, multChoiceAnswerChoice);
    }

    @Override
    public MultipleChoiceAnswerChoice remove(Long multChoiceAnswerChoiceId) throws PersistenceException {
        if (multChoiceAnswerChoiceId == null) {
            throw new PersistenceException("Answer ID cant be null");
        }
        if (!multipleChoiceAnswerChoiceDB.containsKey(multChoiceAnswerChoiceId)){
            throw new PersistenceException("Answer with id " + multChoiceAnswerChoiceId + " is not persisted");
        }
        return multipleChoiceAnswerChoiceDB.remove(multChoiceAnswerChoiceId);
    }

    @Override
    public MultipleChoiceAnswerChoice getToFAnswerById(Long multChoiceAnswerChoiceId) throws PersistenceException {
        if (multChoiceAnswerChoiceId == null) {
            throw new PersistenceException("Answer ID cant be null");
        }
        if (!multipleChoiceAnswerChoiceDB.containsKey(multChoiceAnswerChoiceId)) {
            throw new PersistenceException("Answer with id " + multChoiceAnswerChoiceId + " is not persisted");
        }
        return multipleChoiceAnswerChoiceDB.get(multChoiceAnswerChoiceId);
    }

    @Override
    public List<MultipleChoiceAnswerChoice> listAll() throws PersistenceException {
        List<MultipleChoiceAnswerChoice> multipleChoiceAnswerChoiceList = new ArrayList<>();
        Iterator<MultipleChoiceAnswerChoice> it = multipleChoiceAnswerChoiceDB.values().iterator();
        while (it.hasNext()) {
            multipleChoiceAnswerChoiceList.add(it.next());
        }
        return multipleChoiceAnswerChoiceList;
    }
    
}
