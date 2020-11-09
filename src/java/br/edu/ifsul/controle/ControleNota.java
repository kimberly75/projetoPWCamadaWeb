/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.dao.NotaDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Nota;
import br.edu.ifsul.util.UtilMessages;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author kimberly.geremia
 */
@Named(value = "controleNota")
@ViewScoped
public class ControleNota implements Serializable {
    @EJB
    private NotaDAO<Nota> dao;
    @EJB
    private AlunoDAO<Aluno> daoAluno;
    @EJB
    private DisciplinaDAO<Disciplina> daoDisciplina;
    private Nota objeto;
    
    public ControleNota(){
        
    }
    
    public String listar() {
        return "/privado/nota/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Nota();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao recuperar Nota: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            UtilMessages.mensagemInformacao("Nota removida com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao excluir Nota: " + UtilMessages.getMensagemErro(ex));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            UtilMessages.mensagemInformacao("Nota persistida com sucesso!");
        }catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao persistir Nota: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public NotaDAO<Nota> getDao() {
        return dao;
    }

    public void setDao(NotaDAO<Nota> dao) {
        this.dao = dao;
    }

    public Nota getObjeto() {
        return objeto;
    }

    public void setObjeto(Nota objeto) {
        this.objeto = objeto;
    } 

    public AlunoDAO<Aluno> getDaoAluno() {
        return daoAluno;
    }

    public void setDaoAluno(AlunoDAO<Aluno> daoAluno) {
        this.daoAluno = daoAluno;
    }

    public DisciplinaDAO<Disciplina> getDaoDisciplina() {
        return daoDisciplina;
    }

    public void setDaoDisciplina(DisciplinaDAO<Disciplina> daoDisciplina) {
        this.daoDisciplina = daoDisciplina;
    }
    
    
}
