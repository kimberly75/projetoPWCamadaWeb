/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.util.ConverterOrdem;
import br.edu.ifsul.util.JasperConfiguration;
import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.Stateful;

/**
 *
 * @author kimberly.geremia
 */
@Stateful
public class EspecialidadeDAO<TIPO> extends GenericDAO<Especialidade> implements Serializable{
    public EspecialidadeDAO(){
        super();
        classePersistente = Especialidade.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem(listaOrdem);
    }
}
