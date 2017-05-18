/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.jquest.model.dao;

import br.cefetmg.jquest.model.domain.Question;
import java.util.List;

/**
 *
 * @author Jota Renan
 */
public interface QuestionDAO {
    public void insert(Question question);
    public void update(Question question);
    public Question remove(Long questionId);
    public Question getQuestionById(Long questionId);
    public List<Question> listAll(); 
}
