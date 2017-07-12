/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.MultipleChoiceAnswer;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author João Pedro Renan
 */
public interface MultipleChoiceAnswerDAO {
    public Long insert(MultipleChoiceAnswer multChoiceAnswer) throws PersistenceException;
    public boolean update(MultipleChoiceAnswer multChoiceAnswer) throws PersistenceException;
    public boolean remove(Long multChoiceAnswerId) throws PersistenceException;
    public MultipleChoiceAnswer getAnswerById(Long multChoiceAnswerId, Long questionId) throws PersistenceException;
    public MultipleChoiceAnswer getAnswersByUserAndQuestionId(Long userId, Long questionId) throws PersistenceException;
    public MultipleChoiceAnswer getAllByUserId(Long userId) throws PersistenceException;
    public List<MultipleChoiceAnswer> listAll() throws PersistenceException;
}
