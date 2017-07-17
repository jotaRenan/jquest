/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.dao.ForumDAO;
import br.cefetmg.jquest.model.dao.QuestionDAOImpl;
import br.cefetmg.jquest.model.dao.UserDAOImpl;
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
    private final QuestionManagement questionManagement;
    private final UserManagement userManagement;

    public ForumManagementImpl(ForumDAO DAO) {
        this.DAO = DAO;
        questionManagement = new QuestionManagementImpl(QuestionDAOImpl.getInstance());
        userManagement = new  UserManagementImpl(UserDAOImpl.getInstance());
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
        if(forum.getQuestionId()== null || questionManagement.getQuestionById(forum.getQuestionId()) == null){
            throw new BusinessException("questionID doesn't exist.");
        }
        if( forum.getUserId()== null || userManagement.getUserById(forum.getUserId()) == null){
            throw new BusinessException("usaerID doesn't exist.");
        }
        if(forum.getName() == null || forum.getName().isEmpty()) {
            throw new BusinessException("The forum name text cannot be empty, neither null.");
        }
        
        DAO.insert(forum);
        return forum.getDiscussionSeq();
    }

    @Override
    public boolean forumUpdate(Forum forum) throws BusinessException, PersistenceException {
         if(forum == null){
            throw new BusinessException("The object Forum cannot be null.");
        }
        if(forum.getQuestionId()== null || forum.getDiscussionSeq()== null){
            throw new BusinessException("None of the QuestionID or discussionSeq can be null.");
        }
        if(forum.getName() == null || forum.getName().isEmpty()){
            throw new BusinessException("The forum name text cannot be empty, neither null.");
        }
        
       return DAO.update(forum);
    }

    @Override
    public boolean forumRemove(Long seqDiscussion, Long codQuestion) throws PersistenceException {
        if(seqDiscussion == null && codQuestion == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        return DAO.remove(seqDiscussion, codQuestion);
    }

    @Override
    public Forum getForumById(Long seqDiscussion, Long codQuestion) throws PersistenceException {
        if(seqDiscussion == null && codQuestion == null){
            throw new PersistenceException("None of the parameters can be null.");
        }
        
        return DAO.getForumById(seqDiscussion, codQuestion);
    }

    @Override
    public List<Forum> getAllForunsByQuestionID(Long codQuestion) throws PersistenceException {
        if (codQuestion == null) {
            throw new PersistenceException("Question Id cannot be null.");
        }
        
        List<Forum> aux = DAO.listAllForunsByQuestionID(codQuestion);
        return aux;
    }
}
