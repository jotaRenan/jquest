/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.MultipleChoiceAnswerChoice;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author João Pedro Renan
 */
public interface MultipleChoiceAnswerChoiceManagement {
    public Long insert(MultipleChoiceAnswerChoice multChoiceAnswerChoice) throws BusinessException, PersistenceException;
    public void update(MultipleChoiceAnswerChoice multChoiceAnswerChoice) throws BusinessException, PersistenceException;
    public MultipleChoiceAnswerChoice remove(Long multChoiceAnswerChoiceId) throws PersistenceException;
    public MultipleChoiceAnswerChoice getToFAnswerById(Long multChoiceAnswerChoiceId) throws PersistenceException;
    public List<MultipleChoiceAnswerChoice> listAll() throws PersistenceException;
}
