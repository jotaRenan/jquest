/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.DissertiveQuestionAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;
import br.cefetmg.jquest.model.dao.DissertiveQuestionAnswerDAO;
import br.cefetmg.jquest.model.dao.QuestionDAOImpl;
import br.cefetmg.jquest.model.dao.UserDAOImpl;

/**
 *
 * @author Thalesgsn
 */
public class DissertiveQuestionAnswerManagementImpl implements DissertiveQuestionAnswerManagement{
    
    private DissertiveQuestionAnswerDAO DAO;
    private final QuestionManagement questionManagement;
    private final UserManagement userManagement;

    /**
     * Constructor that injects the DAO persistence dependency.
     * @param DAO The Data Acess Object that carry the persistence dependency. 
     */
    public DissertiveQuestionAnswerManagementImpl(DissertiveQuestionAnswerDAO DAO) {
        this.DAO = DAO;
        questionManagement = new QuestionManagementImpl(QuestionDAOImpl.getInstance());
        userManagement = new UserManagementImpl(UserDAOImpl.getInstance());
    }
    
    /**
     * Set the value of DAO
     *
     * @param DAO new value of DAO
     */
    public void setDAO(DissertiveQuestionAnswerDAO DAO) {
        this.DAO = DAO;
    }

    @Override
    public Long DissertiveQuestionAnswerInsert(DissertiveQuestionAnswer dissertiveQuestionAnswer) throws BusinessException, PersistenceException {
        if(dissertiveQuestionAnswer == null){
            throw new BusinessException("The object DissertiveQuestionAnswer cannot be null.");
        }
        if(dissertiveQuestionAnswer.getUserID() == null || userManagement.getUserById(dissertiveQuestionAnswer.getUserID()) == null)
            throw new BusinessException(" userID doesn't exist.");
        
        if(dissertiveQuestionAnswer.getQuestionID() == null || questionManagement.getQuestionById(dissertiveQuestionAnswer.getQuestionID()) == null){
            throw new BusinessException("questionId doesn't exist.");
        }
        if(dissertiveQuestionAnswer.getTxtAnswer() == null || dissertiveQuestionAnswer.getTxtAnswer().isEmpty()){
            throw new BusinessException("The Answer text cannot be null or empty.");
        }
        
        DAO.insert(dissertiveQuestionAnswer);
        return dissertiveQuestionAnswer.getSeqAnswerUser();
    }

    @Override
    public boolean DissertiveQuestionAnswerUpdate(DissertiveQuestionAnswer dissertiveQuestionAnswer) throws BusinessException, PersistenceException {
        if(dissertiveQuestionAnswer == null)
            throw new BusinessException("The object cannot be null.");
        
        if(dissertiveQuestionAnswer.getUserID() == null || userManagement.getUserById(dissertiveQuestionAnswer.getUserID()) == null)
            throw new BusinessException(" userID doesn't exist.");
        
        if(dissertiveQuestionAnswer.getQuestionID() == null || questionManagement.getQuestionById(dissertiveQuestionAnswer.getQuestionID()) == null){
            throw new BusinessException("questionId doesn't exist.");
        }
        if(dissertiveQuestionAnswer.getTxtAnswer() == null || dissertiveQuestionAnswer.getTxtAnswer().isEmpty()){
            throw new BusinessException("The Answer text cannot be null or empty.");
        }
        
       return DAO.update(dissertiveQuestionAnswer);
    }

    @Override
    public boolean DissertiveQuestionAnswerRemove(Long COD_userIDUseLog, Long COD_question, Long seqAnswerUser) throws PersistenceException {
        if(COD_userIDUseLog == null)
            throw new PersistenceException("The COD_userIDUseLog can't be null.");
        if(COD_question == null)
            throw new PersistenceException("The COD_question can't be null.");
        if(seqAnswerUser == null)
            throw new PersistenceException("The seqanswerUser can't be null.");
         
       return DAO.remove(COD_userIDUseLog, COD_question, seqAnswerUser);
    }

    @Override
    public DissertiveQuestionAnswer getDissertiveQuestionAnswerById(Long COD_userIDUseLog, Long COD_question, Long seqAnswerUser) throws PersistenceException {
        if(COD_userIDUseLog == null)
            throw new PersistenceException("The COD_userIDUseLog can't be null.");
        if(COD_question == null)
            throw new PersistenceException("The COD_question can't be null.");
        if(seqAnswerUser == null)
            throw new PersistenceException("The seqanswerUser can't be null.");
         
        return DAO.getDissertiveQuestionAnswerById(COD_userIDUseLog, COD_question, seqAnswerUser);
    }

    @Override
    public List<DissertiveQuestionAnswer> getPendentDissertativeQuestions() throws PersistenceException {
        return  DAO.listPendentDissertativeQuestions();
    }


}
