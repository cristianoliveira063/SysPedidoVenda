/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.modelFilter;

import br.com.pedidovenda.model.TipoPessoa;
import br.com.pedidovenda.util.validator.Documento;
import br.com.pedidovenda.util.validator.Validador;
import java.io.Serializable;

/**
 *
 * @author CRISTIANO
 */
public class ClienteFilter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Documento
    private String documentoReceitaFederal;
    private String nome;
    private TipoPessoa tipo;
    private Filter filter;

    public String getDocumentoReceitaFederal() {
        return documentoReceitaFederal;
    }

    public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
        this.documentoReceitaFederal = documentoReceitaFederal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    public Filter getFilter() {
        if(Validador.isObjectValido(filter)){           
            return filter;          
        }
        filter = new Filter();
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
    
    
    

}
