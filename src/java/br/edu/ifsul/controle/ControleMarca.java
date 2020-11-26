/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MarcaDAO;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.util.UtilMessages;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author kimberly.geremia
 */
@Named(value = "controleMarca")
@ViewScoped
public class ControleMarca implements Serializable {

    @EJB
    private MarcaDAO<Marca> dao;
    private Marca objeto;

    public ControleMarca() {

    }

    public String listar() {
        return "/privado/teste/listarMarca?faces-redirect=true";
    }

    public void novo() {
        objeto = new Marca();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao recuperar Marca: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            UtilMessages.mensagemInformacao("Marca removida com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao excluir Marca: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            UtilMessages.mensagemInformacao("Marca persistida com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao persistir Marca: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public MarcaDAO<Marca> getDao() {
        return dao;
    }

    public void setDao(MarcaDAO<Marca> dao) {
        this.dao = dao;
    }

    public Marca getObjeto() {
        return objeto;
    }

    public void setObjeto(Marca objeto) {
        this.objeto = objeto;
    }

}
