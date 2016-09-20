/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidovenda.util.validation;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author CRISTIANO
 */
public class Validador {
    
   
    public static boolean isObjectValido(Object object) {
        return object != null;

    }
     
    public static boolean  isNotObjectValido(Object object){      
          return object == null;
    }

    public static boolean isArrayValido(Object[] array) {
        return (array != null) && (array.length >= 1);
    }

    public static boolean isCollectionValida(Collection collection) {
        return (collection != null) && (!collection.isEmpty());
    }
     public static boolean isEmailValido(String email) {
        if ((email == null) || (email.trim().length() == 0)) {
            return false;
        }
        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean isMapValido(Map map) {
        return (map != null) && (!map.isEmpty());
    }

    public static boolean isStringValida(String valorString) {
        return (valorString != null) && (!valorString.trim().equals(""));
    }

    public static boolean isDataValida(Date data) {
        return (data != null) && (data.getTime() >= -2208972740000L);
    }

    public static boolean isNumericoValido(byte numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(short numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(int numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(long numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(float numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(double numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(Byte numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(Short numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(Integer numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(Long numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(Float numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(Double numero) {
        return validarNumero(numero);
    }

    public static boolean isNumericoValido(BigDecimal numero) {
        return validarNumero(numero);
    }

    private static boolean validarNumero(double numero) {
        return numero != 0.0D;
    }

    private static boolean validarNumero(Number numero) {
        return (numero != null) && (numero.doubleValue() != 0.0D);
    }
    
       /**
    * Verifica se cadeia possui somente dígitos.
    * @param cadeia cadeia a ser checada.
    * @return   true, caso a cadeia contenha somente dígitos; false caso contrário.
   */
   public static boolean isSomenteDigito(String cadeia)
   {
      boolean ret = true;

      for (int ind = 0; ret && ind < cadeia.length(); ind++)
         if (cadeia.charAt(ind) < '0' || cadeia.charAt(ind) > '9')
            ret = false;

      return ret;
   }
      /**
    * Verifica se cadeia possui somente letras.
    * @param   cadeia cadeia de caracteres a ser checada.
    * @return  true, caso a cadeia contenha somente letras; false caso contrário.
   */
   public static boolean isSomenteLetra(String cadeia)
   {
      boolean ret = true;

      for (int ind = 0; ret && ind < cadeia.length(); ind++)
         if (!Character.isLetter(cadeia.charAt(ind)))
            ret = false;

      return ret;
   }
     /**
   * Verifica se um CNPJ é de filial
     * @param cnpj
   * @return   true, se CNPJ é de filial; false, caso contrário.
  */
   public static boolean isCnpjFilial(String cnpj)
   {
      StringBuffer sb = new StringBuffer(cnpj);
      String cnpjFmt = null;
      sb = sb.reverse();
      for (int i = cnpj.length(); i < 14; i++)
         sb.append('0');
      sb = sb.reverse();
      cnpjFmt = sb.toString();
      
      return  Integer.parseInt(cnpjFmt.substring(cnpjFmt.length() - 6, cnpjFmt.length() - 2))  != 1;
   }
   
     /**
   * Verifica a validade de um CPF.
   * @param  cpf string com CPF a ser validado.
   * @return true, se cpf é válido; false, caso contrário.
  */
   public static boolean isCpfVald(String cpf)
   {
      int dv1, dv2;

      if (cpf == null || !isSomenteDigito(cpf) || cpf.length() < 3)
         return false;

      dv1 = Character.digit(cpf.charAt(cpf.length() - 2), 10);
      dv2 = Character.digit(cpf.charAt(cpf.length() - 1), 10);

      return cpf.length() <= 11 &&
              !isCadeiaRepetida(cpf) &&
              dv1 == calcDvModulo11(cpf, cpf.length() - 2, 2, 11) &&
              dv2 == calcDvModulo11(cpf, cpf.length() - 1, 2, 11);
   }
     /**
    * Verifica se cadeia é composta apenas por caracteres repetidos (tudo 0 ou tudo 1, ...).
    * @param    cadeia cadeia a ser checada.
    * @return   true, caso cadeia seja formado somente com caracteres repetidos, false caso
    * contrário.
   */
   public static boolean isCadeiaRepetida(String cadeia)
   {
      int  ind = 1;
      char ch = cadeia.charAt(0);

      while (ind < cadeia.length())
      {
         if (ch == cadeia.charAt(ind))
            ind++;
         else
            return false;
      }
      return true;
   }
   
      /**
    * Calcula do dígito verificador módulo 11 de uma cadeia.
    * @param cadeia  cadeia ser calculado o dígito verificador;
    * @param tam     número de dígitos a serem utilizados no cálculo;
    * @param fatorInicial   valor base no fator de multiplicação;
    * @param fatorFinal valor final do fator de multiplicação;
    * @return  valor  dígito verificador.
   */
   public static int calcDvModulo11(String cadeia, int tam, int fatorInicial, int fatorFinal)
   {
      int fator = fatorInicial;
      int total = 0;

      if (cadeia == null || !isSomenteDigito(cadeia))
         return -1;

      for (int ind = 0; ind < tam; ind++)
      {
         total += Character.digit(cadeia.charAt(tam - ind -1), 10) * fator;
         fator++;

         if (fator > fatorFinal)
            fator = fatorInicial;
      }

      total %= 11;

      if (total < 2)
         return 0;

      return (11 - total);
   }
   
     /**
   * Verifica a validade de um CNPJ
   * @param cnpj string com CNPJ a ser validado.
   * <p>Formato de CNPJ válido:<li>RRRRRRRRFFFFDD (tamanho 14)
   *                          <li>RRRRRRRR -  raiz do CGC
   *                          <li>FFFF     - filial (deve ser < 7100)
   *                          <li>DD       - dígito verificador
   * @return   true, se CNPJ é válido; false, caso contrário.
  */
   public static boolean isCnpjVald(String cnpj)
   {
      int filial, posIniFilial, dv1, dv2;

      if (cnpj == null || !isSomenteDigito(cnpj) || cnpj.length() < 3)
         return false;

      posIniFilial = cnpj.length() - 6;
      posIniFilial = posIniFilial > 0 ? posIniFilial : 0;

      filial = Integer.parseInt(cnpj.substring(posIniFilial, cnpj.length() - 2));

      dv1 = Character.digit(cnpj.charAt(cnpj.length() - 2), 10);
      dv2 = Character.digit(cnpj.charAt(cnpj.length() - 1), 10);

      return (cnpj.length() <= 14 &&
              !isCadeiaRepetida(cnpj) &&
              dv1 == calcDvModulo11(cnpj, cnpj.length() - 2, 2, 9) &&
              dv2 == calcDvModulo11(cnpj, cnpj.length() - 1, 2, 9) &&
              filial > 0);
   }
     /**
   * Verifica a validade de um CNPJ
   * @param cnpj string com CNPJ a ser validado.
   * @return   true, se CNPJ é válido; false, caso contrário.
  */
   public static boolean isCnpjVald(long cnpj)
   {
      return isCnpjVald(String.valueOf(cnpj));
   }

    
}
