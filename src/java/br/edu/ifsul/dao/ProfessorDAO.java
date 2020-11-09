/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Professor;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author kimberly.geremia
 */
@Stateful
public class ProfessorDAO<TIPO> extends GenericDAO<Professor> implements Serializable{
    public ProfessorDAO(){
        super();
        classePersistente = Professor.class;
    }
}
