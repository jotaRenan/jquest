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
    private static ForumDAOImpl Instance = null;
    private final HashMap<ForumID, Forum> forumDB;
    
    private ForumDAOImpl() {
      this.forumDB = new HashMap<>();
    }
    /**
     * Singleton constructor that return the unique instance of this object.
     * @return OpenEndedAwnserDAOImpl unique instance.
     */
    public static ForumDAOImpl getInstance() {
        if(Instance == null){
            Instance = new ForumDAOImpl();
        }
        return Instance;
    }

   @Override
    synchronized public void insert(Forum forum)
            throws PersistenceException {
        if(forum == null)
            throw new PersistenceException("The object forum cannot be null.");
        if(forum.getQuestionId() == null || forum.getDiscussionSeq() == null)
            throw new PersistenceException("None of the QuestionId or discussionSeq can be null.");
        
        
        ForumID id = new ForumID(forum.getQuestionId(), 
                forum.getDiscussionSeq());
        
        if(forumDB.containsKey(id))
            throw new PersistenceException("Forum already inserted, or duplicated key.");
            
        forumDB.put(id, forum);
    }

    @Override
    synchronized public void update(Forum forum) throws PersistenceException {
        if(forum == null)
            throw new PersistenceException("The object forum cannot be null.");
        if(forum.getQuestionId() == null || forum.getDiscussionSeq() == null)
            throw new PersistenceException("None of the QuestionId or discussionSeq can be null.");
        
        
        ForumID id = new ForumID(forum.getQuestionId(), 
                forum.getDiscussionSeq());
        

      
        if(!forumDB.containsKey(id))
            throw new PersistenceException("There isn't a forum with this key on the persistence.");
        
        forumDB.replace(id, forum);
    }

    @Override
    synchronized public Forum remove(Long questionID, Long discussionSeq) throws PersistenceException {
        if(questionID == null || discussionSeq == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        ForumID id = new ForumID(questionID, discussionSeq);
         if(!forumDB.containsKey(id))
            throw new PersistenceException("There isn't a forum with this key on the persistence.");
        
        Forum aux = forumDB.get(id);
        forumDB.remove(id);
        return aux;
    }

    @Override
    synchronized public Forum getForumById(Long questionID, Long discussionSeq) throws PersistenceException {
        if(questionID == null || discussionSeq == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        ForumID id = new ForumID(questionID, discussionSeq);
         if(!forumDB.containsKey(id))
            throw new PersistenceException("There isn't a forum with this key on the persistence.");
        
        return forumDB.get(id);
    }

    @Override
    synchronized public List<Forum> listAll() throws PersistenceException {
        List<Forum> aux = new ArrayList(forumDB.values());
        if(aux.isEmpty())
            throw new PersistenceException("There isn't elements in the List.");
        
        return aux;
    }
}
class ForumID{
    private Long questionID;
    
    private Long discussionSeq;

    public ForumID() {
    }

    public ForumID(Long questionID, Long discussionSeq) {
        this.questionID = questionID;
        this.discussionSeq = discussionSeq;
    }

    /**
     * Get the value of questionID
     *
     * @return the value of questionID
     */
    public Long getQuestionID() {
        return questionID;
    }

    /**
     * Set the value of questionID
     *
     * @param questionID new value of questionID
     */
    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    /**
     * Get the value of discussionSeq
     *
     * @return the value of discussionSeq
     */
    public Long getDiscussionSeq() {
        return discussionSeq;
    }

    /**
     * Set the value of discussionSeq
     *
     * @param discussionSeq new value of discussionSeq
     */
    public void setDiscussionSeq(Long discussionSeq) {
        this.discussionSeq = discussionSeq;
    }

}