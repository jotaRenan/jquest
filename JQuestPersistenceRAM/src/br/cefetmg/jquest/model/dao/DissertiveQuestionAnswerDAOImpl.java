/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;


import br.cefetmg.jquest.model.domain.DissertiveQuestionAnswer;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */

public class DissertiveQuestionAnswerDAOImpl implements DissertiveQuestionAnswerDAO{
    private static DissertiveQuestionAnswerDAOImpl dissertiveQuestionAnswerDAO = null;
    private final HashMap<Long, DissertiveQuestionAnswer> dissertiveQuestionAnswerDB;
    private static long DissertiveQuestionAnswerCount = 0;
    
    private DissertiveQuestionAnswerDAOImpl() {
      this.dissertiveQuestionAnswerDB = new HashMap<>();
    }
    /**
     * Singleton constructor that return the unique instance of this object.
     * @return OpenEndedAwnserDAOImpl unique instance.
     */
    public static DissertiveQuestionAnswerDAOImpl getInstance() {
        if(dissertiveQuestionAnswerDAO == null){
            dissertiveQuestionAnswerDAO = new DissertiveQuestionAnswerDAOImpl();
        }
        return dissertiveQuestionAnswerDAO;
    }

    @Override
    public Long insert(DissertiveQuestionAnswer dissertiveQuestionAnswer) throws PersistenceException {
        if (dissertiveQuestionAnswer == null) {
            throw new PersistenceException("The object openEndedAwnser cannot be null.");
        }
        
        Long dissertiveQuestionId = ++DissertiveQuestionAnswerCount;
        dissertiveQuestionAnswer.setSeqAnswerUser(dissertiveQuestionId);
        
        if (dissertiveQuestionAnswer != null && dissertiveQuestionAnswerDB.containsKey(dissertiveQuestionId)) {
            throw new PersistenceException("Duplicate key");
        }
        dissertiveQuestionAnswerDB.put(dissertiveQuestionId, dissertiveQuestionAnswer);
        return dissertiveQuestionId;
    }
    
    @Override
    synchronized public void update(DissertiveQuestionAnswer dissertiveQuestionAnswer) throws PersistenceException {
        if (dissertiveQuestionAnswer == null) {
            throw new PersistenceException("Answer cannot be null");
        }
        Long answerId = dissertiveQuestionAnswer.getSeqAnswerUser();
        if (answerId == null) {
            throw new PersistenceException("Entity Id cannot be null");
        }
        if (!dissertiveQuestionAnswerDB.containsKey(answerId)) {
            throw new PersistenceException("Answer with id " + dissertiveQuestionAnswer.getSeqAnswerUser()+ " is not persisted");
        }
        dissertiveQuestionAnswerDB.replace(answerId, dissertiveQuestionAnswer);
    }

    @Override
    synchronized public DissertiveQuestionAnswer remove(Long seqAnswerUser) throws PersistenceException {
        if (seqAnswerUser == null) {
            throw new PersistenceException("Answer sequence cant be null");
        }
        if (!dissertiveQuestionAnswerDB.containsKey(seqAnswerUser)){
            throw new PersistenceException("Answer with id " + seqAnswerUser + " is not persisted");
        }
        return dissertiveQuestionAnswerDB.remove(seqAnswerUser);
    }

    @Override
    synchronized public DissertiveQuestionAnswer getDissertiveQuestionAnswerById(Long seqAnswerUser) throws PersistenceException {
        if (seqAnswerUser == null) {
            throw new PersistenceException("Answer ID cant be null");
        }
        if (!dissertiveQuestionAnswerDB.containsKey(seqAnswerUser)) {
            throw new PersistenceException("Answer with id " + seqAnswerUser + " is not persisted");
        }
        return dissertiveQuestionAnswerDB.get(seqAnswerUser);
    }

    @Override
    synchronized public List<DissertiveQuestionAnswer> listAll() throws PersistenceException {
        List<DissertiveQuestionAnswer> aux = new ArrayList(dissertiveQuestionAnswerDB.values());
        return aux;
    }
        
}
