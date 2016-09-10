/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.modelFilter.UsuarioFilter;
import br.com.pedidovenda.repository.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author CRISTIANO
 */
@Named
@ViewScoped
public class PesquisarUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioFilter usuarioFilter;
    @Inject
    private Usuarios usuarios;
    private List<Usuario> listUsuarios = new ArrayList<>();

    @PostConstruct
    public void init() {
        pesquisarUsuarios();
    }

    public void pesquisarUsuarios() {
        listUsuarios = usuarios.filtrar(usuarioFilter);
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public UsuarioFilter getUsuarioFilter() {
        return usuarioFilter;
    }

    public void setUsuarioFilter(UsuarioFilter usuarioFilter) {
        this.usuarioFilter = usuarioFilter;
    }

}
