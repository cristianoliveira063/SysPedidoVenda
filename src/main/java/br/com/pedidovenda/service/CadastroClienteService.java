/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.service;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.repository.Clientes;
import br.com.pedidovenda.util.jpa.Transactional;
import br.com.pedidovenda.util.validator.Validador;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author CRISTIANO
 */
public class CadastroClienteService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Clientes clientes;
    
    @Transactional
    public Cliente adicionar(Cliente cliente) throws NegocioException {
        Cliente clienteExistente = clientes.porNumeroDocumento(cliente.getDocumentoReceitaFederal(),
                cliente.getTipo());
        if (Validador.isObjectValido(clienteExistente) && clienteExistente.notEquals(cliente)) {
            throw new NegocioException("Já existe um cliente com o documento informado.");
        }
        return clientes.adicionar(cliente);
    }
}
