/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.Question;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Jota Renan
 */
public interface QuestionManagement {
    public Long questionInsert(Question question) throws BusinessException, PersistenceException;
    public void questionUpdate(Question question) throws BusinessException, PersistenceException;
    public void questionRemove(Long questionId) throws PersistenceException;
    public Question getQuestionById(Long questionId) throws PersistenceException;
    public List<Question> getQuestionByModuleId(Long moduleId) throws PersistenceException;
    public List<Question> getQuestionByDomainId(Long domainId) throws PersistenceException;
    public List<Question> getAll() throws PersistenceException;
}
