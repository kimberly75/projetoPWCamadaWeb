/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.InstituicaoDAO;
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
@Named(value = "controleInstituicao")
@ViewScoped
public class ControleInstituicao implements Serializable {
    @EJB
    private InstituicaoDAO<Instituicao> dao;
    private Instituicao objeto;
    
    public ControleInstituicao(){
        
    }
    
    public String listar() {
        return "/privado/instituicao/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Instituicao();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao recuperar Instituicao: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            UtilMessages.mensagemInformacao("Instituicao removida com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao excluir Instituicao: " + UtilMessages.getMensagemErro(ex));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            UtilMessages.mensagemInformacao("Instituicao persistida com sucesso!");
        }catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao persistir Instituicao: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public InstituicaoDAO<Instituicao> getDao() {
        return dao;
    }

    public void setDao(InstituicaoDAO<Instituicao> dao) {
        this.dao = dao;
    }

    public Instituicao getObjeto() {
        return objeto;
    }

    public void setObjeto(Instituicao objeto) {
        this.objeto = objeto;
    } 
}
