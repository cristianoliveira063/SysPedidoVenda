/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.util.validator;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author CRISTIANO
 */
public class StringUtil {

    private static final String VAZIO = " ";

    public static String getDataHoraFormatada(Date data) {
        if (Validador.isDataValida(data)) {
            DateFormat dateFormat = DateFormat.getInstance();
            return dateFormat.format(data);
        } else {
            return "DATA INVÁLIDA";
        }
    }

    /**
     * Método responsável por validar se uma String de número documento, é um
     * número válido.
     *
     * @param numeroDocumento
     * @return boolean
     */
    public static boolean isNumeroDocumentoValido(String numeroDocumento) {
        String documento = StringUtil.retiraMascara(numeroDocumento);
        if (Validador.isStringValida(documento)) {
            return Validador.isNumericoValido(StringUtil.toLongWrapper(documento));
        }
        return false;
    }

    /**
     * Método utilizado para converter uma string válida em um Long. Se o valor
     * passado não for uma string válida, ou seja, diferente de vazia e
     * diferente de null, retorna um Long com valor 0.
     *
     * @param valor - valor original
     * @return Long - valor convertido
     * @throws NumberFormatException
     */
    public static Long toLongWrapper(String valor) throws NumberFormatException {
        if (Validador.isStringValida(valor)) {
            return Long.valueOf(valor);
        } else {
            return Long.valueOf("0");
        }
    }

    public static String retiraMascara(String str) {
        StringBuilder ret = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        if (Validador.isStringValida(str)) {
            ret.append(str);
            sb.append(str);
            String aux, charMasc = "./-:(){}[]";
            int i, indice, caracter;
            char c;
            aux = str;
            for (i = 0; i < charMasc.length(); i++) {
                c = charMasc.charAt(i);
                while ((indice = aux.indexOf(c)) != -1) {
                    sb.deleteCharAt(indice);
                    aux = sb.toString();
                }
            }
            ret = new StringBuilder();
            for (i = 0; i < aux.length(); i++) {
                caracter = (int) aux.charAt(i);
                if (caracter >= 32 && caracter <= 127) {
                    ret.append((char) caracter);
                }
            }
        }
        return ret.toString();
    }

    /**
     * Método utilizado para retornar textos sem espaços em excesso. O parametro
     * pode conter textos que provavelmente têm espaços em excesso.<br>
     * Exemplo de utilização:<br>
     * <pre>Entrada: -<b>    texto com     excesso        de  espaço.    </b>-<br></pre> Saída:
     * -<b>texto com excesso de espaço.</b>-<br>
     *
     * @param textoComExcessoDeEspaco
     * @return String - Texto com excessos de espaços retirados.
     */
    public static String retiraEspacos(String textoComExcessoDeEspaco) {
        if (Validador.isStringValida(textoComExcessoDeEspaco)) {
            textoComExcessoDeEspaco = textoComExcessoDeEspaco.trim();
            if (textoComExcessoDeEspaco.lastIndexOf(VAZIO) == -1) {
                return textoComExcessoDeEspaco;
            } else {
                return retiraEspacos(textoComExcessoDeEspaco.substring(0, textoComExcessoDeEspaco.lastIndexOf(VAZIO)).trim())
                        + textoComExcessoDeEspaco.substring(textoComExcessoDeEspaco.lastIndexOf(VAZIO), textoComExcessoDeEspaco.length());
            }
        } else {
            return "";
        }
    }

    /**
     * Método utilizado para retornar textos sem espaços em excesso e o conteúdo
     * convertido para maiúsculo.<br>
     * O parametro pode conter textos que provavelmente têm espaços em
     * excesso.<br>
     * Exemplo de utilização:<br>
     * <pre>Entrada: -<b>    texto com     excesso        de  espaço.    </b>-<br></pre> Saída:
     * -<b>TEXTO COM EXCESSO DE ESPAÇO.</b>-<br>
     *
     * @param textoComExcessoDeEspaco
     * @return String - Texto com excessos de espaços retirados e convertidos
     * para maiúsculo.
     */
    public static String retiraEspacosMaiusculo(String textoComExcessoDeEspaco) {
        if (Validador.isStringValida(textoComExcessoDeEspaco)) {
            return retiraEspacos(textoComExcessoDeEspaco.toUpperCase());
        } else {
            return "";
        }
    }

    /**
     * Método responsável por converter a primeira letra de uma String para
     * maiúsculo.
     *
     * @param nome
     * @return String
     */
    public static String convertePrimeiraLetraMaiuscula(String nome) {
        if (Validador.isStringValida(nome)) {
            return nome.substring(0, 1).toUpperCase() + nome.substring(1);
        }
        return nome;
    }

    /**
     * Método responsável por conveter a primeira letra de uma String para
     * minúsculo.
     *
     * @param nome
     * @return String
     */
    public static String convertePrimeiraLetraMinuscula(String nome) {
        if (Validador.isStringValida(nome)) {
            return nome.substring(0, 1).toLowerCase() + nome.substring(1);
        }
        return nome;
    }
}
