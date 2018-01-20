/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
/**
 * @author Bilabila
 */
public class Configuracao {
    // PARA TABELAS
    final public static int NUM_REGISTO = 5;
    
    final public static int NUM_REGISTO_MAX = 15;

//------------------------------------------------------------------------------
 public static String getUserName(String fullname) {
    String user =""; int controlo = 0, posicao1 = -1, posicao2 = -1;
     
     for(int i = 0; i<fullname.length(); i++){
         
     if(fullname.charAt(i)== ' '){
         controlo++;
         if(controlo == 1) posicao1 = i;
         
         posicao2 = i;
     }
     }
     if(controlo == 1) user = fullname.replace(" ", ".");
     else if(controlo > 1){
         String primeiroNome = fullname.substring(0, posicao1);
         String ultimoNome = fullname.substring(posicao2);
         user = primeiroNome.trim()+"."+ultimoNome.trim();
     }else user = fullname;
     
     return user.toLowerCase();
     
     //trim() - remove espaços que uma string pode conter nos extremos
 }
 
  public static void main(String []a){
        System.out.println(getUserName("Augusto Manuel Olga Martins Bilabila"));
    }
}
