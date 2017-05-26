/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Vote;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class VoteDAOImpl implements VoteDAO{
        private static VoteDAOImpl Instance = null;
    private final HashMap<Long, Vote> voteDB;
    
    private VoteDAOImpl() {
      this.voteDB = new HashMap<>();
    }
    /**
     * Singleton constructor that return the unique instance of this object.
     * @return OpenEndedAwnserDAOImpl unique instance.
     */
    public static VoteDAOImpl getInstance() {
        if(Instance == null){
            Instance = new VoteDAOImpl();
        }
        return Instance;
    }

   @Override
    synchronized public void insert(Vote vote)
            throws PersistenceException {
        if(vote == null)
            throw new PersistenceException("The object vote cannot be null.");
        if(vote.getVoteID() == null)
            throw new PersistenceException("VoteID can't be null.");
           
        if(voteDB.containsKey(vote.getVoteID()))
            throw new PersistenceException("Vote already inserted, or duplicated key.");
            
        voteDB.put(vote.getVoteID(), vote);
    }

    @Override
    synchronized public void update(Vote vote) throws PersistenceException {
       if(vote == null)
            throw new PersistenceException("The object vote cannot be null.");
        if(vote.getVoteID() == null)
            throw new PersistenceException("VoteID can't be null.");      
        if(!voteDB.containsKey(vote.getVoteID()))
            throw new PersistenceException("There isn't a vote with this key on the persistence.");
        
        voteDB.replace(vote.getVoteID(), vote);
    }

    @Override
    synchronized public Vote remove(Long voteID) throws PersistenceException {
       if(voteID == null)
            throw new PersistenceException("None of the questionId or discussionID or the comentaryID or userID can be null.");
         if(!voteDB.containsKey(voteID))
            throw new PersistenceException("There isn't a vote with this key on the persistence.");
        
        Vote aux = voteDB.get(voteID);
        voteDB.remove(voteID);
        return aux;
    }

    @Override
    synchronized public Vote getVoteById(Long voteID) throws PersistenceException {
         if(voteID == null)
            throw new PersistenceException("voteID can´t be null.");

         if(!voteDB.containsKey(voteID))
            throw new PersistenceException("There isn't a vote with this key on the persistence.");
        
        return voteDB.get(voteID);
    }

    @Override
    synchronized public List<Vote> listAll() throws PersistenceException {
        List<Vote> aux = new ArrayList(voteDB.values());
        return aux;
    }
}

