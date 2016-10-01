/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author CRISTIANO
 */
@Embeddable
public class EnderecoEntrega implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "entrega_logradouro", nullable = false, length = 150)
    @NotNull
    private String logradouro;

    @Column(name = "entrega_numero", nullable = false, length = 20)
    @NotNull
    @Size(max = 20)
    private String numero;
    @Column(name = "entrega_complemento", length = 150)
    @Size(max = 150)
    private String complemento;
    @Column(name = "entrega_cidade", nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    private String cidade;
    @Column(name = "entrega_uf", nullable = false, length = 60)
    @NotNull
    private UF uf;
    @Column(name = "entrega_cep", nullable = false, length = 20)
    @NotNull
    @Size(max = 20)
    private String cep;

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
        this.logradouro = logradouro;
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
        this.cidade = cidade;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public List<UF> getUfs() {
        return Arrays.asList(UF.values());
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

}
