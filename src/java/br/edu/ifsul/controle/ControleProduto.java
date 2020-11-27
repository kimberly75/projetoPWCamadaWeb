/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ArquivoDAO;
import br.edu.ifsul.dao.MarcaDAO;
import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.modelo.Arquivo;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.util.UtilMessages;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author kimberly.geremia
 */
@Named(value = "controleProduto")
@ViewScoped
public class ControleProduto implements Serializable {

    @EJB
    private ProdutoDAO<Produto> dao;
    @EJB
    private MarcaDAO<Marca> daoMarca;
    
    private Produto objeto;

    private Arquivo arquivo;

    public ControleProduto() {

    }

    public String listar() {
        return "/privado/teste/listarProduto?faces-redirect=true";
    }

    public void novo() {
        objeto = new Produto();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao recuperar Produto: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            UtilMessages.mensagemInformacao("Produto removido com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao excluir Produto: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            UtilMessages.mensagemInformacao("Produto persistido com sucesso!");
        } catch (Exception ex) {
            UtilMessages.mensagemErro("Erro ao persistir Produto: " + UtilMessages.getMensagemErro(ex));
        }
    }

    public ProdutoDAO<Produto> getDao() {
        return dao;
    }

    public void setDao(ProdutoDAO<Produto> dao) {
        this.dao = dao;
    }

    public Produto getObjeto() {
        return objeto;
    }

    public void setObjeto(Produto objeto) {
        this.objeto = objeto;
    }

    public MarcaDAO<Marca> getDaoMarca() {
        return daoMarca;
    }

    public void setDaoMarca(MarcaDAO<Marca> daoMarca) {
        this.daoMarca = daoMarca;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public void novoArquivo() {
        arquivo = new Arquivo();
    }

    public void salvarArquivo() {
        objeto.addArquivo(arquivo);
        UtilMessages.mensagemInformacao("Arquivo adicionado");
    }

    public void removerArquivo(int index) {
        objeto.removeArquivo(index);
    }

    public void enviarArquivo(FileUploadEvent event) {
        try {
            arquivo.setArquivo(event.getFile().getContent());
            arquivo.setNomeArquivo(event.getFile().getFileName().replaceAll("[ ]", "_"));
            UtilMessages.mensagemInformacao("Arquivo enviado");
        } catch (Exception e) {
            UtilMessages.mensagemErro("Erro ao inserir arquivo: " + UtilMessages.getMensagemErro(e));
        }

    }

    public void downloadArquivo(int index) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext ec = facesContext.getExternalContext();
        HttpServletResponse httpResponse = (HttpServletResponse) ec.getResponse();
        Arquivo arqDownload = objeto.getArquivos().get(index);
        try {
            httpResponse.addHeader("Content-Disposition", "attachment; filename=" + arqDownload.getNomeArquivo());
            httpResponse.setContentLength(arqDownload.getArquivo().length);
            httpResponse.setContentType("application/octet-stream");
            httpResponse.getOutputStream().write(arqDownload.getArquivo());
            httpResponse.getOutputStream().flush();
            facesContext.responseComplete();
        } catch (Exception e) {
            UtilMessages.mensagemErro("Download do arquivo com erro");
        }
    }

    public void visualizarArquivo(int index) {
        arquivo = objeto.getArquivos().get(index);
    }
}
