/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.util.Calendar;

/**
 *
 * @author Augusto Bilabila (30-01-2012)
 */
public class Calendario {
    
   String  atual ="";
   String  Data_atual = "";
    
    public Calendario ()
    {
            Calendar now = Calendar.getInstance();
            String mes = ""+(now.get(Calendar.MONTH)+1);
            String me = "";
            
            if (mes.equals("1")) me = "01";
            if (mes.equals("2")) me = "02";
            if (mes.equals("3")) me = "03";
            if (mes.equals("4")) me = "04";
            if (mes.equals("5")) me = "05";
            if (mes.equals("6")) me = "06";
            if (mes.equals("7")) me = "07";
            if (mes.equals("8")) me = "08";
            if (mes.equals("9")) me = "09";
            if (mes.equals("10")) me = "10";
            if (mes.equals("11")) me = "11";
            if (mes.equals("12")) me = "12";
                       
            atual = ""+now.get(Calendar.YEAR)+"-"+me+"-"+now.get(Calendar.DATE)+" "+horaAtual (""+now.getTime());
            Data_atual = ""+now.get(Calendar.DATE)+"-"+me+"-"+now.get(Calendar.YEAR)+" "+horaAtual (""+now.getTime());
    }
    
    public Calendario (String Data)
    {
        
    }

 private String horaAtual (String h){
     String a = ""; int conta=0;
     for(int i=0; i<h.length();i++){              
        if(conta==3) a += h.charAt(i);
        if(h.charAt(i)==' ') conta++;
        if(conta==4) i = h.length();
     }
      return a.trim();
  }
 //-----------------------------------------------------------------------------
  public String dataAtual (){            
      return atual;
  }

    public String getData_atual() {
        return Data_atual;
    }
  
  
  public String dia (){  
      return (atual.substring(0, (atual.indexOf("d")))).trim();
  }
  
  public String DataCerta (String DATA){  
      String result=null;
      
      // Data = Ano-Mes-Dia
        String ano = DATA.substring(0,4);
        String mes =DATA.substring(5,7);
        String dia =DATA.substring(8,10);

        //Construção da data invertida
        result = ""+dia+"-"+mes+"-"+ano;
        
        
        return result; 
}
  //........................................................................
  public String DataInvertida(String DATAcerta){  
      String result=null;
      
      // Data = Dia-Mes-Ano
        String ano =(DATAcerta.substring(6));
        String mes = (DATAcerta.substring(3,5));
        String dia = (DATAcerta.substring(0,2));
        
        //Construção da data invertida
        result = ""+ano+"-"+mes+"-"+dia;        
        
        return result;
}
    public static void main(String []a){
        Calendario obj = new Calendario();
        System.out.println(obj.DataCerta("2012-11-29"));
        System.out.println(obj.DataInvertida("29-11-2012"));
       // System.out.println(obj.dataAtual());
       // System.out.println((obj.getData_atual()).substring(obj.getData_atual().indexOf(" ")));
        //System.out.println((obj.getData_atual()));
    }
  }