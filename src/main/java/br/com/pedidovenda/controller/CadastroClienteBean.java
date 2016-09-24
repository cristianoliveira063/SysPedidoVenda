/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.model.Endereco;
import br.com.pedidovenda.model.TipoPessoa;
import br.com.pedidovenda.model.UF;
import br.com.pedidovenda.service.CadastroClienteService;
import br.com.pedidovenda.service.NegocioException;
import br.com.pedidovenda.util.jsf.MessageView;
import br.com.pedidovenda.util.validator.Validador;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Cristiano Aparecido
 *
 */
@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Cliente cliente;
    private Endereco endereco;
    @CPF
    @NotNull
    private String cpf;
    @CNPJ
    @NotNull
    private String cnpj;
    @Inject
    private CadastroClienteService clienteService;

    public void salvar() {
        try {
            endereco.setCliente(cliente);
            clienteService.adicionar(cliente);
            reset();
            MessageView.info("Cadastro salvo com sucesso!");
        } catch (NegocioException ex) {
            MessageView.error(ex.getMessage());
        }
    }

    public void reset() {
        this.cliente = new Cliente();
        this.cnpj = new String();
        this.cpf = new String();
    }

    public void novoEndereco() {
        this.endereco = new Endereco();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<TipoPessoa> getTipoPessoas() {
        return Arrays.asList(TipoPessoa.values());
    }

    public boolean isPessoaFisica() {
        return Validador.isObjectValido(cliente.getTipo()) && cliente.getTipo().equals(TipoPessoa.FISICA);
    }

    public boolean isPessoaJuridica() {

        return Validador.isObjectValido(cliente.getTipo()) && cliente.getTipo().equals(TipoPessoa.JURIDICA);
    }

    public Endereco getEndereco() {
        if (Validador.isObjectValido(endereco)) {
            return endereco;
        }
        endereco = new Endereco();
        return endereco;
    }

    public List<UF> getUfs() {
        return Arrays.asList(UF.values());
    }
    
    public List<UF>getUfs(String query){
        return Arrays.asList(UF.values());      
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cliente.setDocumentoReceitaFederal(cpf);
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.getCliente().setDocumentoReceitaFederal(cnpj);
        this.cnpj = cnpj;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

     
   
}
