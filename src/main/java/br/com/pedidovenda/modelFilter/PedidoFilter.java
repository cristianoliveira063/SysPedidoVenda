/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.modelFilter;

import br.com.pedidovenda.model.StatusPedido;
import br.com.pedidovenda.util.validator.Validador;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author CRISTIANO
 */
public class PedidoFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long numeroDe;
    private Long numeroAte;
    private Date dataCriacaoDe;
    private Date dataCriacaoAte;
    private String nomeVendedor;
    private String nomeCliente;
    private StatusPedido[] status = StatusPedido.values();
    private Filter filter;

    public Long getNumeroDe() {
        return numeroDe;
    }

    public void setNumeroDe(Long numeroDe) {
        this.numeroDe = numeroDe;
    }

    public Long getNumeroAte() {
        return numeroAte;
    }

    public void setNumeroAte(Long numeroAte) {
        this.numeroAte = numeroAte;
    }

    public Date getDataCriacaoDe() {
        return dataCriacaoDe;
    }

    public void setDataCriacaoDe(Date dataCriacaoDe) {
        this.dataCriacaoDe = dataCriacaoDe;
    }

    public Date getDataCriacaoAte() {
        return dataCriacaoAte;
    }

    public void setDataCriacaoAte(Date dataCriacaoAte) {
        this.dataCriacaoAte = dataCriacaoAte;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public StatusPedido[] getStatus() {
        return status;
    }

    public void setStatus(StatusPedido[] status) {
        this.status = status;
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

}
