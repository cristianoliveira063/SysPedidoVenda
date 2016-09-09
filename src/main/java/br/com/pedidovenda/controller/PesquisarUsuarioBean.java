/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.repository.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author CRISTIANO
 */
@Named
@ViewScoped
public class PesquisarUsuarioBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String nome;
    @Email
    private String email;
    @Inject
    private Usuarios usuarios;
    private List<Usuario> listUsuarios = new ArrayList<>();
    
    @PostConstruct
    public void init(){       
        listUsuarios = usuarios.listar();        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }
    
    
       
}
