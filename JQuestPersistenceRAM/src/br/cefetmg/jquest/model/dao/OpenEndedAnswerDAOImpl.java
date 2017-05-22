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
    public void insert(OpenEndedAnswer openEndedAnswer)
            throws PersistenceException {
        UID id = new UID(openEndedAnswer.getIDQuestion(), 
                openEndedAnswer.getIdUser(), openEndedAnswer.getSeqAnswerUser());
        openEndedDB.put(id, openEndedAnswer);
    }

    @Override
    public void update(OpenEndedAnswer openEndedAnswer) throws PersistenceException {
        UID id = new UID(openEndedAnswer.getIDQuestion(), 
                openEndedAnswer.getIdUser(), openEndedAnswer.getSeqAnswerUser());
        openEndedDB.replace(id, openEndedAnswer);
    }

    @Override
    public OpenEndedAnswer remove(long idQuestion, long idUser, long seqAnswerUser) throws PersistenceException {
        UID id = new UID(idQuestion, idUser, seqAnswerUser);
        OpenEndedAnswer aux = openEndedDB.get(id);
        openEndedDB.remove(id);
        return aux;
    }

    @Override
    public OpenEndedAnswer getOpenEndedAnswerById(long idQuestion, long idUser, long seqAnswerUser) throws PersistenceException {
        UID id = new UID(idQuestion, idUser, seqAnswerUser);
        return openEndedDB.get(id);
    }

    @Override
    public List<OpenEndedAnswer> listAll() throws PersistenceException {
        List<OpenEndedAnswer> aux = new ArrayList(openEndedDB.values());
        return aux;
    }
        
}
/**
 * A simple private class that supports the ID needed to the RAMPersistence.
 * @author Thalesgsn
 */
class UID{
  private long idQuestion; 
  private long idUser; 
  private long seqAnswerUser;

    public UID(long idQuestion, long idUser, long seqAnswerUser) {
        this.idQuestion = idQuestion;
        this.idUser = idUser;
        this.seqAnswerUser = seqAnswerUser;
    }

    public long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getSeqAnswerUser() {
        return seqAnswerUser;
    }

    public void setSeqAnswerUser(long seqAnswerUser) {
        this.seqAnswerUser = seqAnswerUser;
    }
    
}