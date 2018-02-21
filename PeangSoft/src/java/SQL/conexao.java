/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.io.Serializable;
import java.sql.*;


/**
 *
 * @author hp
 */
public class conexao implements Serializable{
    
        private static final long serialVersionUID=1L;
        
        private static final String DRIVER="com.mysql.jdbc.Driver";
        
        private static  String User="root";
        private static String passwd="Ivodia_240390";
        
         private static String BD="jdbc:mysql://localhost:3306/PeangSoft_bd";
         
      
         private static  Connection conn;
         
         public static Connection ConectarBD() throws InstantiationException, SQLException, IllegalAccessException
                 
         {
             
             try{
                Class.forName(DRIVER);
                 conn=DriverManager.getConnection(BD, User, passwd);
                 System.out.println("conexao aberta");
                 
             }catch(Exception ex)
             {
                 System.out.println("Erro na conexao"+ ex);
             }
         return conn;
}

         
         public static void  fecharBD() throws InstantiationException, SQLException, IllegalAccessException {
             
             try{
                 conn.close();
                 System.out.println("conexao fechada");
                 
             } catch(Exception ex)
             {
                 System.out.println("Erro ao  desconectar BD" + ex);
             }
        }
         
         
         public static void main(String[] args) {
        conexao con = new conexao();
        
        try{
            con.ConectarBD();
       }
        catch(Exception ex){
         System.out.println("Erro ao conectar BD" + ex);
       }
            
       
    }
}
        
    

