/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.modelFilter.PedidoFilter;
import br.com.pedidovenda.repository.Pedidos;
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
public class PesquisarPedidosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private LazyDataModel<Pedido> lazyPedidosDataModel;
    @Inject
    private PedidoFilter pedidoFilter;
    @Inject
    private Pedido pedidoSelecionado;
    @Inject
    private Pedidos pedidos;

    @PostConstruct
    public void init() {
         pesquisar();
    }

    public LazyDataModel<Pedido> pesquisar() {
        lazyPedidosDataModel = new LazyDataModel<Pedido>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<Pedido> load(int first, int pageSize, String sortField,
                    SortOrder sortOrder, Map<String, Object> filters) {
                pedidoFilter.getFilter().setPrimeiroRegistro(first);
                pedidoFilter.getFilter().setPropriedadeOrdenacao(sortField);
                pedidoFilter.getFilter().setQuantidadeRegistros(pageSize);
                pedidoFilter.getFilter().setAscendente(SortOrder.ASCENDING.equals(sortOrder));
                setRowCount(pedidos.quantidadeFiltrados(pedidoFilter));
                return pedidos.filtrar(pedidoFilter);
            }

            @Override
            public Object getRowKey(Pedido object) {
                return object.getId();
            }

            @Override
            public Pedido getRowData(String rowKey) {
                List<Pedido> list = (List<Pedido>) getWrappedData();
                for (Pedido p : list) {
                    if (p.getId() == Long.parseLong(rowKey)) {
                        return p;
                    }
                }
                return null;
            }
        };
        return null;
    }

    public LazyDataModel<Pedido> getLazyPedidosDataModel() {
        return lazyPedidosDataModel;
    }

    public void setLazyPedidosDataModel(LazyDataModel<Pedido> lazyPedidosDataModel) {
        this.lazyPedidosDataModel = lazyPedidosDataModel;
    }

    public PedidoFilter getPedidoFilter() {
        return pedidoFilter;
    }

    public void setPedidoFilter(PedidoFilter pedidoFilter) {
        this.pedidoFilter = pedidoFilter;
    }

    public Pedido getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    public void setPedidoSelecionado(Pedido pedidoSelecionado) {
        this.pedidoSelecionado = pedidoSelecionado;
    }

}
