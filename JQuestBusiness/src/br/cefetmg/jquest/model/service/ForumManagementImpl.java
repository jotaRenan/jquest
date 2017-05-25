/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.ForumDAO;
import br.cefetmg.jquest.model.domain.Forum;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class ForumManagementImpl implements ForumManagement {
    
    private ForumDAO DAO;

    public ForumManagementImpl() {
    }

    public ForumManagementImpl(ForumDAO DAO) {
        this.DAO = DAO;
    }
    

    /**
     * Set the value of DAO
     *
     * @param DAO new value of DAO
     */
    public void setDAO(ForumDAO DAO) {
        this.DAO = DAO;
    }

    @Override
    public Long forumInsert(Forum forum) throws BusinessException, PersistenceException {
        if(forum == null){
            throw new BusinessException("The object Forum cannot be null.");
        }
        if(forum.getQuestionId()== null || forum.getDiscussionSeq()== null){
            throw new BusinessException("None of the QuestionID or discussionSeq or seqAnswer can be null.");
        }
        if(forum.equals(new Forum())){
            throw new BusinessException("The object Forum cannot be empty.");
        }
        if(forum.getName().isEmpty() || forum.getName() == null){
            throw new BusinessException("The forum name text cannot be empty, neither null.");
        }
        
        DAO.insert(forum);
        return forum.getDiscussionSeq();
    }

    @Override
    public void forumUpdate(Forum forum) throws BusinessException, PersistenceException {
         if(forum == null){
            throw new BusinessException("The object Forum cannot be null.");
        }
        if(forum.getQuestionId()== null || forum.getDiscussionSeq()== null){
            throw new BusinessException("None of the QuestionID or discussionSeq can be null.");
        }
        if(forum.equals(new Forum())){
            throw new BusinessException("The object Forum cannot be empty.");
        }
        if(forum.getName().isEmpty() || forum.getName() == null){
            throw new BusinessException("The forum name text cannot be empty, neither null.");
        }
        
        DAO.update(forum);
    }

    @Override
    public Forum forumRemove(Long discussionSeq) throws PersistenceException {
        if(discussionSeq == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        return DAO.remove(discussionSeq);
    }

    @Override
    public Forum getForumById(Long discussionSeq) throws PersistenceException {
        if(discussionSeq == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        
        return DAO.getForumById(discussionSeq);
    }

    @Override
    public List<Forum> getAll() throws PersistenceException {
        List<Forum> aux = DAO.listAll();
        if(aux.isEmpty())
            throw new PersistenceException("There isn't elements in the List.");
        return aux;
    }
}
