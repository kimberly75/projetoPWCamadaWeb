/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.util.UtilMessages;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author kimberly.geremia
 */
@Named(value = "controleEspecialidade")
@ViewScoped
public class ControleEspecialidade implements Serializable {

    @EJB
    private EspecialidadeDAO<Especialidade> dao;
    private Especialidade objeto;

    public ControleEspecialidade() {

    }

    public String listar() {
        return "/privado/especialidade/listar?faces-redirect=true";
    }

    public void novo() {
        System.out.println("chamou novo");
        objeto = new Especialidade();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao recuperar Especialidade: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            UtilMessages.mensagemInformacao("Especialidade removida com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao excluir Especialidade: " + UtilMessages.getMensagemErro(ex));
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
            UtilMessages.mensagemInformacao("Especialidade persistida com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao persistir Especialidade: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public EspecialidadeDAO<Especialidade> getDao() {
        return dao;
    }

    public void setDao(EspecialidadeDAO<Especialidade> dao) {
        this.dao = dao;
    }

    public Especialidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Especialidade objeto) {
        this.objeto = objeto;
    }
}
