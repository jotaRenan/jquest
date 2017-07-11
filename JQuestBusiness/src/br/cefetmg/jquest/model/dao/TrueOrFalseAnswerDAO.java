/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.TrueOrFalseAnswer;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author João Pedro Renan
 */
public interface TrueOrFalseAnswerDAO {
    public Long insert(TrueOrFalseAnswer tofAnswer) throws PersistenceException;
    public boolean update(TrueOrFalseAnswer tofAnswer) throws PersistenceException;
    public boolean remove(Long tofAnswerId) throws PersistenceException;
    public TrueOrFalseAnswer getToFAnswerById(Long tofAnswerId) throws PersistenceException;
    public TrueOrFalseAnswer getAnswersByUserAndQuestionId(Long userId) throws PersistenceException;
    public List<TrueOrFalseAnswer> listAll() throws PersistenceException;
}
