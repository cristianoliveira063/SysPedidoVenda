/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CRISTIANO
 */
@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Inject
    private Usuario usuario;
    @NotNull
    private String  senha;
    
    @PostConstruct
    public void init(){
        
        
    }
    
    

    public Usuario getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
      
}
