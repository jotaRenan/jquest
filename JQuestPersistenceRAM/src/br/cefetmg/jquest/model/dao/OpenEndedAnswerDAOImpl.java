/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;


import br.cefetmg.jquest.model.domain.OpenEndedAnswer;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class OpenEndedAnswerDAOImpl implements OpenEndedAnswerDAO{
    private static OpenEndedAnswerDAOImpl Instance = null;
    private final HashMap<OpenEndedAnswerID, OpenEndedAnswer> openEndedDB;
    
    private OpenEndedAnswerDAOImpl() {
      this.openEndedDB = new HashMap<>();
    }
    /**
     * Singleton constructor that return the unique instance of this object.
     * @return OpenEndedAwnserDAOImpl unique instance.
     */
    public static OpenEndedAnswerDAOImpl getInstance() {
        if(Instance == null){
            Instance = new OpenEndedAnswerDAOImpl();
        }
        return Instance;
    }

    @Override
    synchronized public void insert(OpenEndedAnswer openEndedAnswer)
            throws PersistenceException {
        if(openEndedAnswer == null)
            throw new PersistenceException("The object openEndedAwnser cannot be null.");
        if(openEndedAnswer.getquestionID() == null || openEndedAnswer.getuserID() == null || openEndedAnswer.getSeqAnswerUser() == null)
            throw new PersistenceException("None of the questionID or userID or seqAnswer can be null.");
        
        
        OpenEndedAnswerID id = new OpenEndedAnswerID(openEndedAnswer.getquestionID(), 
                openEndedAnswer.getuserID(), openEndedAnswer.getSeqAnswerUser());
        
        if(openEndedDB.containsKey(id))
            throw new PersistenceException("Question already inserted, or duplicated key.");
            
        openEndedDB.put(id, openEndedAnswer);
    }

    @Override
    synchronized public void update(OpenEndedAnswer openEndedAnswer) throws PersistenceException {
        if(openEndedAnswer == null)
         throw new PersistenceException("The object openEndedAwnser cannot be null.");
        
        if(openEndedAnswer.getquestionID() == null || openEndedAnswer.getuserID() == null || openEndedAnswer.getSeqAnswerUser() == null){
            throw new PersistenceException("None of the questionID or userID or seqAnswer can be null.");
        }
        
        OpenEndedAnswerID id = new OpenEndedAnswerID(openEndedAnswer.getquestionID(), 
                openEndedAnswer.getuserID(), openEndedAnswer.getSeqAnswerUser());
      
        if(!openEndedDB.containsKey(id))
            throw new PersistenceException("There isn't a question with this key on the persistence.");
        
        openEndedDB.replace(id, openEndedAnswer);
    }

    @Override
    synchronized public OpenEndedAnswer remove(Long questionID, Long userID, Long seqAnswerUser) throws PersistenceException {
        if(questionID == null || userID == null || seqAnswerUser == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        OpenEndedAnswerID id = new OpenEndedAnswerID(questionID, userID, seqAnswerUser);
         if(!openEndedDB.containsKey(id))
            throw new PersistenceException("There isn't a question with this key on the persistence.");
        
        OpenEndedAnswer aux = openEndedDB.get(id);
        openEndedDB.remove(id);
        return aux;
    }

    @Override
    synchronized public OpenEndedAnswer getOpenEndedAnswerById(Long questionID, Long userID, Long seqAnswerUser) throws PersistenceException {
        if(questionID == null || userID == null || seqAnswerUser == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        OpenEndedAnswerID id = new OpenEndedAnswerID(questionID, userID, seqAnswerUser);
         if(!openEndedDB.containsKey(id))
            throw new PersistenceException("There isn't a question with this key on the persistence.");
        
        return openEndedDB.get(id);
    }

    @Override
    synchronized public List<OpenEndedAnswer> listAll() throws PersistenceException {
        List<OpenEndedAnswer> aux = new ArrayList(openEndedDB.values());
        if(aux.isEmpty())
            throw new PersistenceException("There isn't elements in the List.");
        
        return aux;
    }
        
}
/**
 * A simple private class that supports the ID needed to the RAMPersistence.
 * @author Thalesgsn
 */
class OpenEndedAnswerID{
  private Long questionID; 
  private Long userID; 
  private Long seqAnswerUser;

    public OpenEndedAnswerID(Long questionID, Long userID, Long seqAnswerUser) {
        this.questionID = questionID;
        this.userID = userID;
        this.seqAnswerUser = seqAnswerUser;
    }

    public Long getquestionID() {
        return questionID;
    }

    public void setquestionID(Long questionID) {
        this.questionID = questionID;
    }

    public Long getuserID() {
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
    
}