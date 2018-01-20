/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author hp
 */
public class Dias {
    static int anoAtual = GregorianCalendar.YEAR;
    public static int dataDiff(java.util.Date dataLow, java.util.Date dataHigh){  
        GregorianCalendar startTime = new GregorianCalendar();  
        GregorianCalendar endTime = new GregorianCalendar();  
        GregorianCalendar curTime = new GregorianCalendar();  
        GregorianCalendar baseTime = new GregorianCalendar();  
  
        startTime.setTime(dataLow);  
        endTime.setTime(dataHigh);  
       
        int dif_multiplier = 1;  
       
        // Verifica a ordem de inicio das datas  
        if( dataLow.compareTo( dataHigh ) < 0 ){  
            baseTime.setTime(dataHigh);  
            curTime.setTime(dataLow);  
            dif_multiplier = 1;  
        }else{  
            baseTime.setTime(dataLow);  
            curTime.setTime(dataHigh);  
            dif_multiplier = -1;  
        }  
       
        int result_years = 0;  
        int result_months = 0;  
        int result_days = 0;  
  
        // Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import acumulando  
        // no total de dias. Ja leva em consideracao ano bissesto  
        while( curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR) ||  
                curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)  )  
        {  
           
            int max_day = curTime.getActualMaximum( GregorianCalendar.DAY_OF_MONTH );  
            result_months += max_day;  
            curTime.add(GregorianCalendar.MONTH, 1);  
           
        }  
       
        // Marca que é um saldo negativo ou positivo  
        result_months = result_months*dif_multiplier;  
       
       
        // Retirna a diferenca de dias do total dos meses  
        result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime.get(GregorianCalendar.DAY_OF_MONTH));  
        
        return result_years+result_months+result_days;  
    }
    
    public static void main(String[] args) {
        
        Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		Date date1 = new Date();
                date1.after(date);
		calendar.setTime(date);
	
        System.out.println("Data atual "+calendar.getTime());
        System.out.println(""+Dias.dataDiff(date, date));
       // System.out.println(""+Dias.dataDiff(dataLow, dataHigh));
        
    }
}
