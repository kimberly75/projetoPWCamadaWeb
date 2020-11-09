/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Nota;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author kimberly.geremia
 */
@Stateful
public class NotaDAO<TIPO> extends GenericDAO<Nota> implements Serializable{
    public NotaDAO(){
        super();
        classePersistente = Nota.class;
    }
}
