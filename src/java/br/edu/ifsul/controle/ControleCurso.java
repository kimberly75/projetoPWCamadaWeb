/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.modelo.Curso;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Instituicao;
import br.edu.ifsul.util.UtilMessages;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author kimberly.geremia
 */
@Named(value = "controleCurso")
@ViewScoped
public class ControleCurso implements Serializable {

    @EJB
    private CursoDAO<Curso> dao;
    @EJB
    private InstituicaoDAO<Instituicao> daoInstituicao;
    @EJB
    private DisciplinaDAO<Disciplina> daoDisciplina;
    private Curso objeto;

    public ControleCurso() {

    }

    public String listar() {
        return "/privado/teste/listarCurso?faces-redirect=true";
    }

    public void novo() {
        System.out.println("chamou novo");
        objeto = new Curso();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao recuperar Curso: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            UtilMessages.mensagemInformacao("Curso removido com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao excluir Curso: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public void salvar() {
        System.out.println("chamou salvar");
        try {
            if (objeto.getId() == null) {
                System.out.println("entrou no if");
                dao.persist(objeto);
                System.out.println("persistiu");
            } else {
                System.out.println("caiu no else");
                dao.merge(objeto);
                System.out.println("mergeou");
            }
            UtilMessages.mensagemInformacao("Curso persistido com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao persistir Curso: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public CursoDAO<Curso> getDao() {
        return dao;
    }

    public void setDao(CursoDAO<Curso> dao) {
        this.dao = dao;
    }

    public Curso getObjeto() {
        return objeto;
    }

    public void setObjeto(Curso objeto) {
        this.objeto = objeto;
    }

    public InstituicaoDAO<Instituicao> getDaoInstituicao() {
        return daoInstituicao;
    }

    public void setDaoInstituicao(InstituicaoDAO<Instituicao> daoInstituicao) {
        this.daoInstituicao = daoInstituicao;
    }

    public DisciplinaDAO<Disciplina> getDaoDisciplina() {
        return daoDisciplina;
    }

    public void setDaoDisciplina(DisciplinaDAO<Disciplina> daoDisciplina) {
        this.daoDisciplina = daoDisciplina;
    }
}
