/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.TrueOrFalseAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author João Pedro Renan
 */
public interface TrueOrFalseAnswerManagement {
    public Long insert(TrueOrFalseAnswer tofAnswer) throws BusinessException, PersistenceException;
    public boolean update(TrueOrFalseAnswer tofAnswer) throws BusinessException, PersistenceException;
    public boolean remove(Long tofAnswerId) throws BusinessException, PersistenceException;
    public TrueOrFalseAnswer getToFAnswerById(Long tofAnswerId, Long questionId) throws BusinessException, PersistenceException;
    public TrueOrFalseAnswer getAnswersByUserAndQuestionId(Long userId, Long questionId) throws BusinessException, PersistenceException;
    public TrueOrFalseAnswer getAllByUserId(Long userId) throws BusinessException, PersistenceException;
    public List<TrueOrFalseAnswer> getAll() throws PersistenceException;
}
