/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author CRISTIANO
 */
public class MessageView {

    /**
     * Exibe uma mensagem de sucesso no sistema.
     *
     * @param msg
     */
    public static void info(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }

    /**
     * Exibe uma mensagem de alerta no sistema.
     *
     * @param msg
     */
    public static void warn(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null));
    }

    /**
     * Exibe uma mensagem de erro no sistema.
     *
     * @param msg
     */
    public static void error(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }

    /**
     * Exibe uma mensagem de erro critico no sistema.
     *
     * @param msg
     */
    public static void fatal(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, null));
    }

}
