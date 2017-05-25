/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.ClosedEndedAlternative;
import br.cefetmg.jquest.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author João Pedro Renan
 */
public interface ClosedEndedAlternativeDAO {
    public Long insert(ClosedEndedAlternative closedEndedAlt) throws PersistenceException;
    public void update(ClosedEndedAlternative closedEndedAlt) throws PersistenceException;
    public ClosedEndedAlternative remove(Long closedEndedAltId) throws PersistenceException;
    public ClosedEndedAlternative getClosedEndedAlternativeById(Long closedEndedAltId) throws PersistenceException;
    public List<ClosedEndedAlternative> listAll() throws PersistenceException;
}
