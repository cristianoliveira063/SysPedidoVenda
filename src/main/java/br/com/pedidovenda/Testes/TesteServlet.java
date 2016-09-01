/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.Testes;

import br.com.pedidovenda.modelFilter.ProdutoFilter;
import br.com.pedidovenda.repository.Produtos;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CRISTIANO
 */
@WebServlet(name = "TesteServlet", urlPatterns = {"/TesteServlet"})
public class TesteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Inject
    Produtos produtos;
    @Inject
    ProdutoFilter produtoFilter;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet de Teste executando...");
       // List<Produto> lista = produtos.listar();
        //long n = lista.stream().count();
       // lista = lista.stream().filter(p -> p.getSku().equals("SO7754")).collect(Collectors.toList());
        //System.out.println(n);
        //lista.forEach(p -> System.out.println(p.getCategoria().getDescricao()));
          
    }
}
