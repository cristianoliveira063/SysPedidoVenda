/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.modelFilter;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.util.validator.SKU;
import br.com.pedidovenda.util.validator.Validador;
import java.io.Serializable;

/**
 *
 * @author CRISTIANO
 */
public class ProdutoFilter implements Serializable {

    private static final long serialVersionUID = 1L;
    @SKU
    private String sku;
    private String nome;
    private Categoria categoria;
    private Filter filter;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.toUpperCase();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Filter getFilter() {
        if (Validador.isObjectValido(filter)) {
            return filter;
        }
        filter = new Filter();
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
