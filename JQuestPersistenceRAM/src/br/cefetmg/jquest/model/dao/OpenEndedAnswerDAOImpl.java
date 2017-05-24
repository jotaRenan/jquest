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
    private final HashMap<UID, OpenEndedAnswer> openEndedDB;
    
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
        if(openEndedAnswer.getIDQuestion() == null || openEndedAnswer.getIdUser() == null || openEndedAnswer.getSeqAnswerUser() == null)
            throw new PersistenceException("None of the idQuestion or iduser or seqAnswer can be null.");
        
        
        UID id = new UID(openEndedAnswer.getIDQuestion(), 
                openEndedAnswer.getIdUser(), openEndedAnswer.getSeqAnswerUser());
        
        if(openEndedDB.containsKey(id))
            throw new PersistenceException("Question already inserted, or duplicated key.");
            
        openEndedDB.put(id, openEndedAnswer);
    }

    @Override
    synchronized public void update(OpenEndedAnswer openEndedAnswer) throws PersistenceException {
        if(openEndedAnswer == null)
         throw new PersistenceException("The object openEndedAwnser cannot be null.");
        
        if(openEndedAnswer.getIDQuestion() == null || openEndedAnswer.getIdUser() == null || openEndedAnswer.getSeqAnswerUser() == null){
            throw new PersistenceException("None of the idQuestion or iduser or seqAnswer can be null.");
        }
        
        UID id = new UID(openEndedAnswer.getIDQuestion(), 
                openEndedAnswer.getIdUser(), openEndedAnswer.getSeqAnswerUser());
      
        if(!openEndedDB.containsKey(id))
            throw new PersistenceException("There isn't a question with this key on the persistence.");
        
        openEndedDB.replace(id, openEndedAnswer);
    }

    @Override
    synchronized public OpenEndedAnswer remove(Long idQuestion, Long idUser, Long seqAnswerUser) throws PersistenceException {
        if(idQuestion == null || idUser == null || seqAnswerUser == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        UID id = new UID(idQuestion, idUser, seqAnswerUser);
         if(!openEndedDB.containsKey(id))
            throw new PersistenceException("There isn't a question with this key on the persistence.");
        
        OpenEndedAnswer aux = openEndedDB.get(id);
        openEndedDB.remove(id);
        return aux;
    }

    @Override
    synchronized public OpenEndedAnswer getOpenEndedAnswerById(Long idQuestion, Long idUser, Long seqAnswerUser) throws PersistenceException {
        if(idQuestion == null || idUser == null || seqAnswerUser == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        UID id = new UID(idQuestion, idUser, seqAnswerUser);
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
class UID{
  private Long idQuestion; 
  private Long idUser; 
  private Long seqAnswerUser;

    public UID(Long idQuestion, Long idUser, Long seqAnswerUser) {
        this.idQuestion = idQuestion;
        this.idUser = idUser;
        this.seqAnswerUser = seqAnswerUser;
    }

    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getSeqAnswerUser() {
        return seqAnswerUser;
    }

    public void setSeqAnswerUser(Long seqAnswerUser) {
        this.seqAnswerUser = seqAnswerUser;
    }
    
}