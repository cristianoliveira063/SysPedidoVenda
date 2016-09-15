/*
 * PARG Desenvolvimento de Sistemas
 * Pablo Alexander - pablo@parg.com.br
 * 
 * Obtem um CEP no ViaCEP
 */
package br.com.pedidovenda.util.cep;

/**
 * Interface para os eventos
 *
 * 
 */
public interface ViaCEPEvents {
    /**
     * Quando o CEP for encontrado com sucesso
     * @param cep retorna o objeto ViaCEP
     */
    public void onCEPSuccess(ViaCEP cep);
    
    /**
     * Quando ocorrer qualquer erro ao encontrar o CEP
     * @param cep retorna o CEP que foi requisitado
     */
    public void onCEPError(String cep);
}
