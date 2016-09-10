/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.modelFilter;

import java.io.Serializable;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author CRISTIANO
 */
public class UsuarioFilter implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String nome;
    @Email
    private String email;

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
    
    
    
    
    
    
    
    
}
