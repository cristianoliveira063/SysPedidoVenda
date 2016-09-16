/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.util.cep;

/**
 *
 * @author CRISTIANO
 */
public class Teste {
    
    public static void main(String[] args) throws ViaCEPException {
        
        ViaCEP cep =  new ViaCEP();
        cep.buscar("78093559");
        System.out.println(cep.getLogradouro());
        
        
    }
}
