/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ArquivoDAO;
import br.edu.ifsul.modelo.Arquivo;
import br.edu.ifsul.util.UtilMessages;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author kimberly.geremia
 */
@Named(value = "controleArquivo")
@ViewScoped
public class ControleArquivo implements Serializable {

    @EJB
    private ArquivoDAO<Arquivo> dao;
    private Arquivo objeto;

    public ControleArquivo() {

    }

    public String listar() {
        return "/privado/teste/listarArquivo?faces-redirect=true";
    }

    public void novo() {
        objeto = new Arquivo();
    }
    
     public void enviarArquivo(FileUploadEvent event){
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            objeto.setArquivo(event.getFile().getContent());
            String nomeArquivo = event.getFile().getFileName();
            nomeArquivo = nomeArquivo.replaceAll("[ ]","_");
            objeto.setNomeArquivo(nomeArquivo);
            UtilMessages.mensagemInformacao("Arquivo enviado com sucesso!");
        } catch (Exception e){
            UtilMessages.mensagemErro("Erro ao enviar arquivo: " + UtilMessages.getMensagemErro(e));
        }
    }

    public void downloadArquivo(){
        byte[] download = (byte[]) objeto.getArquivo();
        HttpServletResponse response = (HttpServletResponse) 
                FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition","attachment; filename=" + objeto.getNomeArquivo());
        response.setContentLength(download.length);
        try {
            response.setContentType("application/octet-stream");
            response.getOutputStream().write(download);
            response.getOutputStream().flush();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e){
            UtilMessages.mensagemErro("Erro no download da foto: " + UtilMessages.getMensagemErro(e));
        }
    }
    
    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao recuperar Arquivo: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            UtilMessages.mensagemInformacao("Arquivo removido com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao excluir Arquivo: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            UtilMessages.mensagemInformacao("Arquivo persistido com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao persistir Arquivo: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public ArquivoDAO<Arquivo> getDao() {
        return dao;
    }

    public void setDao(ArquivoDAO<Arquivo> dao) {
        this.dao = dao;
    }

    public Arquivo getObjeto() {
        return objeto;
    }

    public void setObjeto(Arquivo objeto) {
        this.objeto = objeto;
    }

}
