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
    private final HashMap<VoteID, Vote> voteDB;
    
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
        if(vote.getQuestionId() == null || vote.getDiscussionId()== null || vote.getCommentaryId() == null || vote.getUserId() == null)
            throw new PersistenceException("None of the QuestionId or discussionID or the comentaryID or userID can be null.");
        
        
        VoteID id = new VoteID(vote.getQuestionId(), 
                vote.getDiscussionId(), vote.getCommentaryId(), vote.getUserId());
        
        if(voteDB.containsKey(id))
            throw new PersistenceException("Vote already inserted, or duplicated key.");
            
        voteDB.put(id, vote);
    }

    @Override
    synchronized public void update(Vote vote) throws PersistenceException {
       if(vote == null)
            throw new PersistenceException("The object vote cannot be null.");
        if(vote.getQuestionId() == null || vote.getDiscussionId()== null || vote.getCommentaryId() == null || vote.getUserId() == null)
            throw new PersistenceException("None of the questionId or discussionID or the comentaryID or userID can be null.");
        
        
        VoteID id = new VoteID(vote.getQuestionId(), 
                vote.getDiscussionId(), vote.getCommentaryId(), vote.getUserId());
      
        if(!voteDB.containsKey(id))
            throw new PersistenceException("There isn't a vote with this key on the persistence.");
        
        voteDB.replace(id, vote);
    }

    @Override
    synchronized public Vote remove(Long questionID, Long discussionID, Long commentaryID, Long userID) throws PersistenceException {
       if(questionID == null || discussionID == null || commentaryID == null || userID == null)
            throw new PersistenceException("None of the questionId or discussionID or the comentaryID or userID can be null.");
        VoteID id = new VoteID(questionID, 
                discussionID, commentaryID, userID);
         if(!voteDB.containsKey(id))
            throw new PersistenceException("There isn't a vote with this key on the persistence.");
        
        Vote aux = voteDB.get(id);
        voteDB.remove(id);
        return aux;
    }

    @Override
    synchronized public Vote getVoteById(Long questionID, Long discussionID, Long commentaryID, Long userID) throws PersistenceException {
         if(questionID == null || discussionID == null || commentaryID == null || userID == null)
            throw new PersistenceException("None of the questionId or discussionID or the comentaryID or userID can be null.");
        VoteID id = new VoteID(questionID, 
                discussionID, commentaryID, userID);
         if(!voteDB.containsKey(id))
            throw new PersistenceException("There isn't a vote with this key on the persistence.");
        
        return voteDB.get(id);
    }

    @Override
    synchronized public List<Vote> listAll() throws PersistenceException {
        List<Vote> aux = new ArrayList(voteDB.values());
        if(aux.isEmpty())
            throw new PersistenceException("There isn't elements in the List.");
        
        return aux;
    }
}
class VoteID{
    private Long questionId;
    private Long discussionSeq;
    private Long commentarySeq;
    private Long userId;

    public VoteID() {
    }

    public VoteID(Long questionId, Long discussionSeq, Long commentarySeq, Long userId) {
        this.questionId = questionId;
        this.discussionSeq = discussionSeq;
        this.commentarySeq = commentarySeq;
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getDiscussionSeq() {
        return discussionSeq;
    }

    public void setDiscussionSeq(Long discussionSeq) {
        this.discussionSeq = discussionSeq;
    }

    public Long getCommentarySeq() {
        return commentarySeq;
    }

    public void setCommentarySeq(Long commentarySeq) {
        this.commentarySeq = commentarySeq;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    
}