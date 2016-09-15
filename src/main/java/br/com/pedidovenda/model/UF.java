/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.model;

/**
 *
 * @author CRISTIANO
 */
public enum UF {

    AC("Acre"), AL("Alagoas"), AP("Amapá"), AM("Amazonas"), BA("Bahia"), CE("Ceará"), DF("Distrito Federal"),
    ES("Espírito Santo"), GO("Goiás"), MA("Maranhão"), MT("Mato Grosso"), MS("Mato Grosso do sul"), MG("Minas Gerais"),
    PA("Pará"), PB("Paraíba"), PR("Paraná"), PE("Pernambuco"),
    PI("Piauí"), RJ("Rio de Janeiro"), RN("Rio Grande do Norte"), RS("Rio grande do Sul"), RO("Rondônia"),
    RR("Roraima"), SC("Santa Catarina"), SP("São Paulo"), SE("Sergipe"),
    TO("Tocantins");

    private final String valor;

    private UF(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
      
}
