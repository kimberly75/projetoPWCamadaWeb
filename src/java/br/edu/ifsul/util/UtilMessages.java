/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author kimberly.geremia
 */
public class UtilMessages {
    public static String getMensagemErro(Exception e){
        while (e.getCause() != null){
            e = (Exception) e.getCause();
        }
        String returnMessage = e.getMessage();
        if (returnMessage.contains("O registro viola restrição de chave estrangeira")){
            returnMessage = "Registro possui vínculos no sistema, os exclua primeiro";
        }
        return returnMessage;
    }
    
    public static void mensagemInformacao(String mensagem){
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "");
        contexto.addMessage(null, msg);
    }
    
    public static void mensagemErro(String mensagem){
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
        contexto.addMessage(null, msg);
    }
}
