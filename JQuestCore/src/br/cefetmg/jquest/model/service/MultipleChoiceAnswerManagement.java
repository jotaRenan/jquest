/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.MultipleChoiceAnswer;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author João Pedro Renan
 */
public interface MultipleChoiceAnswerManagement {
    public Long insert(MultipleChoiceAnswer multipleChoiceAnswer) throws BusinessException, PersistenceException;
    public boolean update(MultipleChoiceAnswer multipleChoiceAnswer) throws BusinessException, PersistenceException;
    public boolean remove(Long multipleChoiceAnswerId) throws PersistenceException;
    public MultipleChoiceAnswer getAnswerById(Long multipleChoiceAnswerId, Long questionId) throws PersistenceException;
    public MultipleChoiceAnswer getAnswersByUserAndQuestionId(Long userId, Long questionId) throws PersistenceException;
    public MultipleChoiceAnswer getAllByUserId(Long userId) throws PersistenceException;
    public List<MultipleChoiceAnswer> getAll() throws PersistenceException;
}
