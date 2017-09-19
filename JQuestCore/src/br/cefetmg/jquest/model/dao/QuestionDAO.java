/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Question;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Jota Renan
 */
public interface QuestionDAO {
    public Long insert(Question question) throws PersistenceException;
    public boolean update(Question question) throws PersistenceException;
    public boolean remove(Long questionId) throws PersistenceException;
    public Question getQuestionById(Long questionId) throws PersistenceException;
    public List<Question> getQuestionsByModuleId(Long moduleId, Long domainId) throws PersistenceException;
    public List<Question> getQuestionsByDomainId(Long domainId) throws PersistenceException;
    public List<Question> getQuestionsByCreatorId(Long userId) throws PersistenceException;
    public List<Question> listAll() throws PersistenceException; 
}
