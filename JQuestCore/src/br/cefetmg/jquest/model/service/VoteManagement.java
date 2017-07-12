/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.Vote;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface VoteManagement {
    //@TODO: Javadocs
    public Long voteInsert(Vote vote) throws BusinessException, PersistenceException;
    public boolean voteUpdate(Vote vote) throws BusinessException, PersistenceException;
    public boolean voteRemove(Long seqCommentary, Long userID) throws PersistenceException;
    public Vote getVoteById(Long seqCommentary, Long userID) throws PersistenceException;
    public List<Vote> getAllVotesByCommentaryID(Long seqCommentary) throws PersistenceException;
}
