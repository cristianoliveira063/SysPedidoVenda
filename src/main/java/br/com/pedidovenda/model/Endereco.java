/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.model;

import br.com.pedidovenda.util.validator.StringUtil;
import br.com.pedidovenda.util.validator.Validador;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import br.com.pedidovenda.util.validator.CEP;

/**
 *
 * @author CRISTIANO
 */
@Entity
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    @NotNull
    @Size(max = 150)
    private String logradouro;
    @Column(nullable = false, length = 20)
    @NotNull
    @Size(max = 20)
    private String numero;
    @Column(length = 200)
    private String complemento;
    @Column(nullable = false, length = 60)
    @NotNull
    private String bairro;
    @Column(nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    private String cidade;
    @Column(nullable = false, length = 60)
    @Enumerated(EnumType.STRING)
    @NotNull
    private UF uf;
    @Column(nullable = false, length = 20)
    @NotNull
    @CEP
    private String cep;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = Validador.isStringValida(logradouro) ? StringUtil
                .convertePrimeiraLetraMaiuscula(logradouro) : logradouro;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = Validador.isStringValida(cidade) ? StringUtil
                .convertePrimeiraLetraMaiuscula(cidade) : cidade;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        if (Validador.isObjectValido(cliente)) {
            return cliente;
        }
        cliente = new Cliente();
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = Validador.isStringValida(bairro) ? StringUtil
                .convertePrimeiraLetraMaiuscula(bairro) : bairro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.syspedidovenda.model.Endereco[ id=" + getId() + " ]";
    }

}
