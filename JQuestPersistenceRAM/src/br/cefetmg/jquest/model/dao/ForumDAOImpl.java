/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Forum;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class ForumDAOImpl implements ForumDAO {
    private static ForumDAOImpl ForumDAO = null;
    private final HashMap<Long, Forum> forumDB;
    private static long forumCount = 0;
    
    private ForumDAOImpl() {
      this.forumDB = new HashMap<>();
    }
    /**
     * Singleton constructor that return the unique instance of this object.
     * @return OpenEndedAwnserDAOImpl unique instance.
     */
    public static ForumDAOImpl getInstance() {
        if(ForumDAO == null){
            ForumDAO = new ForumDAOImpl();
        }
        return ForumDAO;
    }

   @Override
    synchronized public Long insert(Forum forum) throws PersistenceException{
      if (forum == null) {
            throw new PersistenceException("The object openEndedAwnser cannot be null.");
       }
        Long forumSeq = forum.getDiscussionSeq();
        
        if (forum != null && forumDB.containsKey(forumSeq)) {
            throw new PersistenceException("Duplicate key");
        }
        forumSeq = ++forumCount;
        forum.setDiscussionSeq(forumSeq);
        forumDB.put(forumSeq, forum);
        return forumSeq;
    }

    @Override
    synchronized public void update(Forum forum) throws PersistenceException {
        if(forum == null)
            throw new PersistenceException("The object forum cannot be null.");
        if(forum.getQuestionId() == null || forum.getDiscussionSeq() == null)
            throw new PersistenceException("None of the QuestionId or discussionSeq can be null.");
        
        Long seq = forum.getDiscussionSeq();
        if (seq == null) {
            throw new PersistenceException("Entity Id cannot be null");
        }

        if(!forumDB.containsKey(seq))
            throw new PersistenceException("There isn't a forum with this key on the persistence.");
        
        forumDB.replace(seq, forum);
    }
    

    @Override
    synchronized public Forum remove(Long discussionSeq) throws PersistenceException {
        if (discussionSeq == null) {
            throw new PersistenceException("None of the parameters can be null.");
        }
        if (!forumDB.containsKey(discussionSeq)){
            throw new PersistenceException("There isn't a forum with this key on the persistence.");
        }
        return forumDB.remove(discussionSeq);
    }
    

    @Override
    synchronized public Forum getForumById(Long discussionSeq) throws PersistenceException {
        if(discussionSeq == null){
            throw new PersistenceException("None of the parameters can be null.");
        }

        if (!forumDB.containsKey(discussionSeq)) {
            throw new PersistenceException("There isn't a forum with this key on the persistence.");
        }
        return forumDB.get(discussionSeq);
        
    }

    @Override
    synchronized public List<Forum> listAll() throws PersistenceException {
        List<Forum> aux = new ArrayList(forumDB.values());
        return aux;
    }
}
