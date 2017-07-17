/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.service;

import br.cefetmg.jquest.model.domain.QuestionAlternative;
import br.cefetmg.jquest.model.exception.BusinessException;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author João Pedro Renan
 */
public interface QuestionAlternativeManagement {
    public Long insert(QuestionAlternative closedEndedAlt) throws BusinessException, PersistenceException;
    public boolean update(QuestionAlternative closedEndedAlt) throws BusinessException, PersistenceException;
    public boolean remove(Long questionId, Long closedEndedAltId) throws PersistenceException;
    public QuestionAlternative getQuestionAlternativeById(Long questionId, Long closedEndedAltId) throws PersistenceException;
    public List<QuestionAlternative> getAlternativesByQuestionId(Long questionId) throws PersistenceException;
}
