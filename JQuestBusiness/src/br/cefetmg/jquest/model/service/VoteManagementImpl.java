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
        
        if(vote.getCommentarySeq() == null)
            throw new PersistenceException("The commentarySeq cannot be null.");
        
        if(vote.getDiscussionSeq() == null)
            throw new PersistenceException("The discussionSeq cannot be null.");
        
        if(vote.getQuestionId() == null)
            throw new PersistenceException("The questionId cannot be null.");
        
        if(vote.getUserId() == null)
            throw new PersistenceException("The userId cannot be null.");
        
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
    public boolean voteRemove(Long seqCommentary,Long userID) throws PersistenceException {
        if(seqCommentary == null)
            throw new PersistenceException("seqCommentary can´t be null.");
        if(userID == null)
            throw new PersistenceException("userID can´t be null.");
        return DAO.remove(seqCommentary, userID);
    }

    @Override
    public Vote getVoteById(Long seqCommentary,Long userID) throws PersistenceException {
        if(seqCommentary == null)
            throw new PersistenceException("seqCommentary can´t be null.");
        if(userID == null)
            throw new PersistenceException("userID can´t be null.");
        
        return DAO.getVoteById(seqCommentary, userID);
    }

    @Override
    public List<Vote> getAll() throws PersistenceException {
        List<Vote> aux = DAO.listAll();
        if(aux.isEmpty())
            throw new PersistenceException("There isn't elements in the List.");
        return aux;
    }
}
