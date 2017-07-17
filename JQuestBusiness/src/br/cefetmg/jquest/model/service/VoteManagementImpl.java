/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.CommentaryDAOImpl;
import br.cefetmg.jquest.model.dao.ForumDAOImpl;
import br.cefetmg.jquest.model.dao.QuestionDAOImpl;
import br.cefetmg.jquest.model.dao.UserDAOImpl;
import br.cefetmg.jquest.model.dao.VoteDAO;
import br.cefetmg.jquest.model.domain.Vote;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class VoteManagementImpl implements VoteManagement {
    private VoteDAO DAO;
    private final ForumManagement forumManagement;
    private final QuestionManagement questionManagement;
    private final UserManagement userManagement;
    private final CommentaryManagement commentaryManagement;


    public VoteManagementImpl(VoteDAO DAO) {
        this.DAO = DAO;
        forumManagement =  new ForumManagementImpl(ForumDAOImpl.getInstance());
        questionManagement = new QuestionManagementImpl(QuestionDAOImpl.getInstance());
        userManagement = new UserManagementImpl(UserDAOImpl.getInstance());
        commentaryManagement = new CommentaryManagementImpl(CommentaryDAOImpl.getInstance());
    }
    

    /**
     * Set the value of DAO
     *
     * @param DAO new value of DAO
     */
    public void setDAO(VoteDAO DAO) {
        this.DAO = DAO;
    }

    @Override
    public Long voteInsert(Vote vote) throws BusinessException, PersistenceException {
        if(vote == null)
            throw new PersistenceException("The object vote cannot be null.");
        
        if(vote.getCommentarySeq() == null || commentaryManagement.getcommentaryBySeq(vote.getQuestionId(), vote.getDiscussionSeq(), vote.getCommentarySeq()) == null)
            throw new PersistenceException("commentarySeq doesn't exist.");
        
        if(vote.getDiscussionSeq() == null || forumManagement.getForumById(vote.getDiscussionSeq(), vote.getQuestionId()) == null)
            throw new PersistenceException("discussionSeq doesn't exist.");
        
        if(vote.getQuestionId() == null || questionManagement.getQuestionById(vote.getQuestionId()) == null)
            throw new PersistenceException("questionId doesn't exist.");
        
        if(vote.getUserId() == null || userManagement.getUserById(vote.getUserId()) == null)
            throw new PersistenceException("userId doesn't exist.");
        
        DAO.insert(vote);
        return vote.getVoteID();
    }

    @Override
    public boolean voteUpdate(Vote vote) throws BusinessException, PersistenceException {
        if(vote == null)
            throw new PersistenceException("The object vote cannot be null.");
        
        if(vote.getVoteID() == null)
            throw new PersistenceException("The voteId cannot be null.");
        
        if(vote.getCommentarySeq() == null)
            throw new PersistenceException("The commentarySeq cannot be null.");
        
        if(vote.getDiscussionSeq() == null)
            throw new PersistenceException("The discussionSeq cannot be null.");
        
        if(vote.getQuestionId() == null)
            throw new PersistenceException("The questionId cannot be null.");
        
        if(vote.getUserId() == null)
            throw new PersistenceException("The userId cannot be null.");
        
        return DAO.update(vote);
    }

    @Override
    public boolean voteRemove(Long COD_question, Long SEQ_discussion, Long seqCommentary, Long userID) throws PersistenceException {
        if(seqCommentary == null)
            throw new PersistenceException("seqCommentary can´t be null.");
        if(userID == null)
            throw new PersistenceException("userID can´t be null.");
        return DAO.remove(COD_question, SEQ_discussion, seqCommentary, userID);
    }

    @Override
    public Vote getVoteById(Long COD_question, Long SEQ_discussion, Long seqCommentary, Long userID) throws PersistenceException {
        if(seqCommentary == null)
            throw new PersistenceException("seqCommentary can´t be null.");
        if(userID == null)
            throw new PersistenceException("userID can´t be null.");
        
        return DAO.getVoteById(COD_question, SEQ_discussion, seqCommentary, userID);
    }

    @Override
    public List<Vote> getAllVotesByCommentaryID(Long seqCommentary) throws PersistenceException {
        List<Vote> aux = DAO.listAllVotesByCommentaryID(seqCommentary);
        if(aux.isEmpty())
            throw new PersistenceException("There isn't elements in the List.");
        return aux;
    }
}
