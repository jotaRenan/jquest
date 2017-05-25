/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

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

    public VoteManagementImpl() {
    }

    public VoteManagementImpl(VoteDAO DAO) {
        this.DAO = DAO;
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
        if(vote.getQuestionId() == null || vote.getDiscussionId()== null || vote.getCommentaryId() == null || vote.getUserId() == null)
            throw new PersistenceException("None of the QuestionId or discussionID or the comentaryID or userID can be null.");
        if(vote.equals(new Vote()))
            throw new BusinessException("The object Vote cannot be empty.");
        
        DAO.insert(vote);
        return vote.getCommentaryId();
    }

    @Override
    public void voteUpdate(Vote vote) throws BusinessException, PersistenceException {
        if(vote == null)
            throw new PersistenceException("The object vote cannot be null.");
        if(vote.getQuestionId() == null || vote.getDiscussionId()== null || vote.getCommentaryId() == null || vote.getUserId() == null)
            throw new PersistenceException("None of the QuestionId or discussionID or the comentaryID or userID can be null.");
        if(vote.equals(new Vote()))
            throw new BusinessException("The object Vote cannot be empty.");
        
        DAO.update(vote);
    }

    @Override
    public void voteRemove(Long questionID, Long discussionID, Long commentaryID, Long userID) throws PersistenceException {
        if(questionID == null || discussionID == null || commentaryID == null || userID == null)
            throw new PersistenceException("None of the QuestionId or discussionID or the comentaryID or userID can be null.");
        DAO.remove(questionID, discussionID, commentaryID, userID);
    }

    @Override
    public Vote getVoteById(Long questionID, Long discussionID, Long commentaryID, Long userID) throws PersistenceException {
       if(questionID == null || discussionID == null || commentaryID == null || userID == null)
            throw new PersistenceException("None of the QuestionId or discussionID or the comentaryID or userID can be null.");
        
        return DAO.getVoteById(questionID, discussionID, commentaryID, userID);
    }

    @Override
    public List<Vote> getAll() throws PersistenceException {
        List<Vote> aux = DAO.listAll();
        if(aux.isEmpty())
            throw new PersistenceException("There isn't elements in the List.");
        return aux;
    }
}
