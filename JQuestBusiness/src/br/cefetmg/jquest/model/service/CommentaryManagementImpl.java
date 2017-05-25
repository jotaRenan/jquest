/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.CommentaryDAO;
import br.cefetmg.jquest.model.domain.Commentary;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author GABRIEL HADDAD
 */
public class CommentaryManagementImpl implements CommentaryManagement{
    
    private final CommentaryDAO commentaryDAO;

    public CommentaryManagementImpl(CommentaryDAO commentaryDAO) {
        this.commentaryDAO = commentaryDAO;
    }
    
    
        
    @Override
    public Long commentaryInsert(Commentary commentary) throws BusinessException, PersistenceException {
        if (commentary == null)
            throw new BusinessException("Commentary cannot be null");
        
        if (commentary.getCommentarySeq()== null)
            throw new BusinessException("Commentary's sequence cannot be null");
            
        if (commentary.getDiscussionId()== null)
            throw new BusinessException("Commentary's discussionID cannot be null");
        
        if (commentary.getQuestionId()== null)
            throw new BusinessException("Commentary's questionID cannot be null");
        
        if (commentary.getUserId()== null)
            throw new BusinessException("Commentary's userID cannot be null");

        if ((commentary.getTextCommentary().equals("")) || (commentary.getTextCommentary() == null) )
            throw new BusinessException("Commentary's text cannot be null");        
        
        commentaryDAO.insert(commentary);
        return commentary.getCommentarySeq();
    }

    @Override
    public void commentaryUpdate(Commentary commentary) throws BusinessException, PersistenceException {
        if (commentary == null)
            throw new BusinessException("Commentary cannot be null");
        
        if (commentary.getCommentarySeq()== null)
            throw new BusinessException("Commentary's sequence cannot be null");
            
        if (commentary.getDiscussionId()== null)
            throw new BusinessException("Commentary's discussionID cannot be null");
        
        if (commentary.getQuestionId()== null)
            throw new BusinessException("Commentary's questionID cannot be null");
        
        if (commentary.getUserId()== null)
            throw new BusinessException("Commentary's userID cannot be null");
        
        if (commentary.getTextCommentary()== null
                || commentary.getTextCommentary().isEmpty())
            throw new BusinessException("Commentary's text cannot be null");  

        commentaryDAO.update(commentary);
    }

    @Override
    public Commentary commentaryRemove(Long commentarySeq) throws PersistenceException {
        if (commentarySeq == null) {
            throw new PersistenceException("Commentary's sequence cannot be null");
        }
        return commentaryDAO.remove(commentarySeq);
    }

    @Override
    public Commentary getcommentaryBySeq(Long commentarySeq) throws PersistenceException {
        if (commentarySeq == null)
            throw new PersistenceException("Commentary's sequence cannot be null");
        
        return commentaryDAO.getCommentaryBySeq(commentarySeq); //if the id isn't valid it throws an exception
    }

    @Override
    public List<Commentary> getCommentarysByForumId(Long forumId) throws PersistenceException {
        if (forumId == null) {
            throw new PersistenceException("Forum's id cannot be null");
        }

        return commentaryDAO.getCommentarysByForumId(forumId);
    }
    
    @Override
    public List<Commentary> getAll() throws PersistenceException {
        List<Commentary> list = commentaryDAO.listAll();
        return list;
    }
    
}
