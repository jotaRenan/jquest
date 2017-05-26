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
    public void update(MultipleChoiceAnswer multipleChoiceAnswer) throws BusinessException, PersistenceException;
    public MultipleChoiceAnswer remove(Long multipleChoiceAnswerId) throws PersistenceException;
    public MultipleChoiceAnswer getToFAnswerById(Long multipleChoiceAnswerId) throws PersistenceException;
    public List<MultipleChoiceAnswer> getAll() throws PersistenceException;
}
