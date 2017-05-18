/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Domain;
import java.util.List;

/**
 *
 * @author Jota Renan
 */
public interface DomainDAO {
    public void insert(Domain domain);
    public void update(Domain domain);
    public Domain remove(Long domainId);
    public Domain getDomainById(Long domainId);
    public List<Domain> listAll(); 
}
