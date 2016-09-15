/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.model;

/**
 *
 * @author CRISTIANO
 */
public enum TipoPessoa {
    
    FISICA("Física"),JURIDICA("Jurídica");
    
    private String descricao;

    private TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
}
