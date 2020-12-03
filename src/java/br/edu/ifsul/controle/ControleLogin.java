/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.UtilMessages;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kimberly.geremia
 */
@Named(value = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {

    private Usuario usuarioAutenticado;

    @EJB
    private UsuarioDAO<Usuario> dao;

    private String usuario;
    private String senha;

    public ControleLogin() {
    }

    public String efetuarLogin() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(this.usuario, this.senha);
            if (request.getUserPrincipal() != null) {
                usuarioAutenticado = dao.getObjectById(request.getUserPrincipal().getName());
                UtilMessages.mensagemInformacao("Login realizado com sucesso");
                usuario = "";
                senha = "";
            }
            return "/index";
        } catch (Exception e) {
            UtilMessages.mensagemErro("Usuário ou senha invalidos!" + UtilMessages.getMensagemErro(e));
            return "/login";
        }
    }

    public String efetuarLogout() {
        try {
            usuarioAutenticado = null;
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();
            UtilMessages.mensagemInformacao("Logout realizado com sucesso");
            return "/index";
        } catch (Exception e) {
            UtilMessages.mensagemErro("Erro!" + UtilMessages.getMensagemErro(e));
            return "/index";
        }
    }

    public String irPaginaLogin() {
        return "/login?faces-redirect=true";
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
