/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Grupo;
import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.repository.Grupos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class CadastroUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Usuario usuario;
    @NotNull
    private String senha;
    @Inject
    private Grupos grupos;
    private List<Grupo> listarGrupos = new ArrayList<>();
    @Inject
    private Grupo grupo;

    @PostConstruct
    public void init() {
        listarGrupos = grupos.listar();

    }

    public void adicionarGrupo() {
        System.out.println(grupo.getNome());
        usuario.getGrupos().add(grupo);
        //System.out.println(usuario.getGrupos().size());

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

    public List<Grupo> getListarGrupos() {
        return listarGrupos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}
