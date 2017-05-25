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
import java.util.Objects;

/**
 *
 * @author Thalesgsn
 */
public class DissertiveQuestionAnswerDAOImpl implements DissertiveQuestionAnswerDAO{
    private static DissertiveQuestionAnswerDAOImpl Instance = null;
    private final HashMap<DissertiveQuestionAnswerID, DissertiveQuestionAnswer> openEndedDB;
    
    private DissertiveQuestionAnswerDAOImpl() {
      this.openEndedDB = new HashMap<>();
    }
    /**
     * Singleton constructor that return the unique instance of this object.
     * @return OpenEndedAwnserDAOImpl unique instance.
     */
    public static DissertiveQuestionAnswerDAOImpl getInstance() {
        if(Instance == null){
            Instance = new DissertiveQuestionAnswerDAOImpl();
        }
        return Instance;
    }

    @Override
    synchronized public void insert(DissertiveQuestionAnswer dissertiveQuestionAnswer)
            throws PersistenceException {
        if(dissertiveQuestionAnswer == null)
            throw new PersistenceException("The object openEndedAwnser cannot be null.");
        if(dissertiveQuestionAnswer.getQuestionID() == null || dissertiveQuestionAnswer.getUserID() == null || dissertiveQuestionAnswer.getSeqAnswerUser() == null)
            throw new PersistenceException("None of the questionID or userID or seqAnswer can be null.");
        
        
        DissertiveQuestionAnswerID id = new DissertiveQuestionAnswerID(dissertiveQuestionAnswer.getQuestionID(), 
                dissertiveQuestionAnswer.getUserID(), dissertiveQuestionAnswer.getSeqAnswerUser());
        
        if(openEndedDB.containsKey(id))
            throw new PersistenceException("Question already inserted, or duplicated key.");
            
        openEndedDB.put(id, dissertiveQuestionAnswer);
    }

    @Override
    synchronized public void update(DissertiveQuestionAnswer dissertiveQuestionAnswer) throws PersistenceException {
        if(dissertiveQuestionAnswer == null)
         throw new PersistenceException("The object openEndedAwnser cannot be null.");
        
        if(dissertiveQuestionAnswer.getQuestionID() == null || dissertiveQuestionAnswer.getUserID() == null || dissertiveQuestionAnswer.getSeqAnswerUser() == null){
            throw new PersistenceException("None of the questionID or userID or seqAnswer can be null.");
        }
        
        DissertiveQuestionAnswerID id = new DissertiveQuestionAnswerID(dissertiveQuestionAnswer.getQuestionID(), 
                dissertiveQuestionAnswer.getUserID(), dissertiveQuestionAnswer.getSeqAnswerUser());
      
        if(!openEndedDB.containsKey(id))
            throw new PersistenceException("There isn't a question with this key on the persistence.");
        
        openEndedDB.replace(id, dissertiveQuestionAnswer);
    }

    @Override
    synchronized public DissertiveQuestionAnswer remove(Long questionID, Long userID, Long seqAnswerUser) throws PersistenceException {
        if(questionID == null || userID == null || seqAnswerUser == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        DissertiveQuestionAnswerID id = new DissertiveQuestionAnswerID(questionID, userID, seqAnswerUser);
         if(!openEndedDB.containsKey(id))
            throw new PersistenceException("There isn't a question with this key on the persistence.");
        
        DissertiveQuestionAnswer aux = openEndedDB.get(id);
        openEndedDB.remove(id);
        return aux;
    }

    @Override
    synchronized public DissertiveQuestionAnswer getDissertiveQuestionAnswerById(Long questionID, Long userID, Long seqAnswerUser) throws PersistenceException {
        if(questionID == null || userID == null || seqAnswerUser == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        DissertiveQuestionAnswerID id = new DissertiveQuestionAnswerID(questionID, userID, seqAnswerUser);
         if(!openEndedDB.containsKey(id))
            throw new PersistenceException("There isn't a question with this key on the persistence.");
        
        return openEndedDB.get(id);
    }

    @Override
    synchronized public List<DissertiveQuestionAnswer> listAll() throws PersistenceException {
        List<DissertiveQuestionAnswer> aux = new ArrayList(openEndedDB.values());
        if(aux.isEmpty())
            throw new PersistenceException("There isn't elements in the List.");
        
        return aux;
    }
        
}
/**
 * A simple private class that supports the ID needed to the RAMPersistence.
 * @author Thalesgsn
 */
class DissertiveQuestionAnswerID{
  private Long questionID; 
  private Long userID; 
  private Long seqAnswerUser;

    public DissertiveQuestionAnswerID(Long questionID, Long userID, Long seqAnswerUser) {
        this.questionID = questionID;
        this.userID = userID;
        this.seqAnswerUser = seqAnswerUser;
    }

    public Long getQuestionID() {
        return questionID;
    }

    public void setquestionID(Long questionID) {
        this.questionID = questionID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setuserID(Long userID) {
        this.userID = userID;
    }

    public Long getSeqAnswerUser() {
        return seqAnswerUser;
    }

    public void setSeqAnswerUser(Long seqAnswerUser) {
        this.seqAnswerUser = seqAnswerUser;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.questionID);
        hash = 79 * hash + Objects.hashCode(this.userID);
        hash = 79 * hash + Objects.hashCode(this.seqAnswerUser);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DissertiveQuestionAnswerID other = (DissertiveQuestionAnswerID) obj;
        return true;
    }
    
}