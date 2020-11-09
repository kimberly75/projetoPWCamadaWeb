/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Aluno;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author kimberly.geremia
 */
@Stateful
public class AlunoDAO<TIPO> extends GenericDAO<Aluno> implements Serializable{
    public AlunoDAO(){
        super();
        classePersistente = Aluno.class;
    }
}
