/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author hp
 */
public class Seleciona_Elemento_Combo {
    
    public void Seleciona_Elemento(String arrayElementos[], ResultSet rs, String campo, int qtdElemento) throws SQLException{
         
        for(int i =0; i<qtdElemento; i++){
          if(rs.getString(campo).equals(arrayElementos[i])){
            System.out.print("<option value=\"<%="+arrayElementos[i]+"%>\" selected=\"selected\"><%="+arrayElementos[i]+"%></option>");
            System.out.print("");
            }else{
              System.out.print("<option value=\"<%="+arrayElementos[i]+"%>\"><%="+arrayElementos[i]+"%></option>");
        }
    }
}
    }
