/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;


import br.cefetmg.jquest.model.domain.OpenEndedAwnser;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class OpenEndedAwnserDAOImpl implements OpenEndedAwnserDAO{
    private static OpenEndedAwnserDAOImpl Instance = null;
    private final HashMap<UID, OpenEndedAwnser> openEndedDB;
    
    private OpenEndedAwnserDAOImpl() {
      this.openEndedDB = new HashMap<>();
    }
    
    public static OpenEndedAwnserDAOImpl getInstance() {
        if(Instance == null){
            Instance = new OpenEndedAwnserDAOImpl();
        }
        return Instance;
    }

    @Override
    public void insert(OpenEndedAwnser openEndedAwnser)
            throws PersistenceException {
        UID id = new UID(openEndedAwnser.getIDQuestion(), 
                openEndedAwnser.getIdUser(), openEndedAwnser.getSeqAwnserUser());
        openEndedDB.put(id, openEndedAwnser);
    }

    @Override
    public void update(OpenEndedAwnser openEndedAwnser) throws PersistenceException {
        UID id = new UID(openEndedAwnser.getIDQuestion(), 
                openEndedAwnser.getIdUser(), openEndedAwnser.getSeqAwnserUser());
        openEndedDB.replace(id, openEndedAwnser);
    }

    @Override
    public OpenEndedAwnser remove(long idQuestion, long idUser, long seqAwnserUser) throws PersistenceException {
        UID id = new UID(idQuestion, idUser, seqAwnserUser);
        OpenEndedAwnser aux = openEndedDB.get(id);
        openEndedDB.remove(id);
        return aux;
    }

    @Override
    public OpenEndedAwnser getOpenEndedAwnserById(long idQuestion, long idUser, long seqAwnserUser) throws PersistenceException {
        UID id = new UID(idQuestion, idUser, seqAwnserUser);
        return openEndedDB.get(id);
    }

    @Override
    public List<OpenEndedAwnser> listAll() throws PersistenceException {
        List<OpenEndedAwnser> aux = new ArrayList(openEndedDB.values());
        return aux;
    }
        
}

class UID{
  private long idQuestion; 
  private long idUser; 
  private long seqAwnserUser;

    public UID(long idQuestion, long idUser, long seqAwnserUser) {
        this.idQuestion = idQuestion;
        this.idUser = idUser;
        this.seqAwnserUser = seqAwnserUser;
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

    public long getSeqAwnserUser() {
        return seqAwnserUser;
    }

    public void setSeqAwnserUser(long seqAwnserUser) {
        this.seqAwnserUser = seqAwnserUser;
    }
    
}