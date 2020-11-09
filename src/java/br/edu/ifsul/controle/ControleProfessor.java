/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.dao.ProfessorDAO;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.modelo.Professor;
import br.edu.ifsul.util.UtilMessages;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author kimberly.geremia
 */
@Named(value = "controleProfessor")
@ViewScoped
public class ControleProfessor implements Serializable {
    @EJB
    private ProfessorDAO<Professor> dao;
    @EJB
    private EspecialidadeDAO<Especialidade> daoEspecialidade;
    private Professor objeto;
    
    public ControleProfessor(){
        
    }
    
    public String listar() {
        return "/privado/professor/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Professor();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao recuperar Professor: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            UtilMessages.mensagemInformacao("Professor removido com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao excluir Professor: " + UtilMessages.getMensagemErro(ex));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                System.out.println("entrou persist");
                dao.persist(objeto);
            } else {
                System.out.println("entrou merge");
                dao.merge(objeto);
            }
            UtilMessages.mensagemInformacao("Professor persistido com sucesso!");
        }catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao persistir Professor: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public ProfessorDAO<Professor> getDao() {
        return dao;
    }

    public void setDao(ProfessorDAO<Professor> dao) {
        this.dao = dao;
    }

    public Professor getObjeto() {
        return objeto;
    }

    public void setObjeto(Professor objeto) {
        this.objeto = objeto;
    } 

    public EspecialidadeDAO<Especialidade> getDaoEspecialidade() {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDAO<Especialidade> daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }
    
    
}
