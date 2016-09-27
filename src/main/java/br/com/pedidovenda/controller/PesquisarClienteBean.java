/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.modelFilter.ClienteFilter;
import br.com.pedidovenda.repository.Clientes;
import br.com.pedidovenda.service.NegocioException;
import br.com.pedidovenda.util.jsf.MessageView;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author CRISTIANO
 */
@Named
@ViewScoped
public class PesquisarClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private LazyDataModel<Cliente> lazyClienteDataModel;
    @Inject
    private ClienteFilter clienteFilter;
    private Cliente clienteSelecionado;
    @Inject
    private Clientes clientes;

    @PostConstruct
    public void init() {
        System.out.println("Pesquisar clientes executando");
        pesquisar();
    }
    
    public void excluir(){      
        try {
            clientes.remover(clienteSelecionado);
              MessageView.info("Cliente " + clienteSelecionado.getNome()
				+ " excluído com sucesso.");  
        } catch (NegocioException e) {
              MessageView.fatal(e.getMessage());
        }      
    }

    public LazyDataModel<Cliente> pesquisar() {

        lazyClienteDataModel = new LazyDataModel<Cliente>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<Cliente> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, Object> filters) {
                clienteFilter.getFilter().setPrimeiroRegistro(first);
                clienteFilter.getFilter().setPropriedadeOrdenacao(sortField);
                clienteFilter.getFilter().setQuantidadeRegistros(pageSize);
                clienteFilter.getFilter().setAscendente(SortOrder.ASCENDING.equals(sortOrder));
                setRowCount(clientes.quantidadeFiltrados(clienteFilter));
                return clientes.filtrar(clienteFilter);
            }

            @Override
            public Object getRowKey(Cliente object) {
                return object.getId();
            }

            @Override
            public Cliente getRowData(String rowKey) {
                List<Cliente> list = (List<Cliente>) getWrappedData();
                for (Cliente c : list) {
                    if (c.getId() == Long.parseLong(rowKey)) {
                        return c;
                    }
                }
                return null;
            }

        };

        return null;
    }

    public LazyDataModel<Cliente> getLazyClienteDataModel() {
        return lazyClienteDataModel;
    }

    public ClienteFilter getClienteFilter() {
        return clienteFilter;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

}
