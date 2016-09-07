/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.service;

import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.Produtos;
import br.com.pedidovenda.util.jpa.Transactional;
import br.com.pedidovenda.util.validation.Validador;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author CRISTIANO
 */
public class CadastroProdutoService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Produtos produtos;

    @Transactional
    public Produto salvar(Produto produto) throws NegocioException {
        System.out.println(produto.getSku());
        Produto produtoExistente = produtos.porSku(produto.getSku());
        if (Validador.isObjectValido(produtoExistente) && produtoExistente.notEquals(produto)) {
            throw new NegocioException("Já existe um produto com o SKU informado.");
        }
        return produtos.adicionar(produto);
    }      
}
