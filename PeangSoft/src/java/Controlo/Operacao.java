/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlo;
import SQL.*;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Inject;

/**
 *
 * @author hp
 */
public class Operacao implements Serializable{
private static final long serialVersionUID = 1L;

@Inject
conexao conectar;
PreparedStatement statement;
    
    public Operacao() {
        
    }
    
    // metodo de inserir
    public boolean  inserir(String tabela, String campos, String valores) throws InstantiationException, SQLException, IllegalAccessException
    {
        String query="insert into " + tabela + "(" + campos + ") values ( "+ valores + ")" ;
        
        
        try{
            statement = conectar.ConectarBD().prepareStatement(query);
            statement.executeUpdate();
        }catch(Exception ex)
        { 
            System.out.println("Erro ao inserir:" +ex.getMessage());
            return false;
        }
   return true;     
    }

    
    public boolean  eliminar (String tabela, String condicao) throws InstantiationException, SQLException , IllegalAccessException
    {
        String query="delete from "+ tabela + "where " + condicao;
        try{
            statement=conectar.ConectarBD().prepareStatement(query);
            statement.execute();
            
        }catch( Exception ex)
        {
            System.out.println("Erro ao tentar eliminar:"+ ex.getMessage());
            return false;
        }
         return true;
                 
    }
    
    public boolean alterar (String tabela, String valores, String condicao) throws InstantiationException, SQLException , IllegalAccessException
    {
        String query= "update from " + tabela + "set" + valores + "where"+ condicao;
        
        try{
            statement=conectar.ConectarBD().prepareStatement(query);
            statement.executeUpdate();
        }catch(Exception ex){
            System.out.println("Erro ao tentar alterar:"+ ex.getMessage());
            return false;
        }
        return true;
 
   }
    
        
    public boolean alterar2 (String tabela, String valores) throws InstantiationException, SQLException , IllegalAccessException
    {
        String query= "update from " + tabela + "set" + valores;
        
        try{
            statement=conectar.ConectarBD().prepareStatement(query);
            statement.executeUpdate();
        }catch(Exception ex){
            System.out.println("Erro ao tentar alterar:"+ ex.getMessage());
            return false;
        }
        return true;
 
   }
    
    public ResultSet consultar (String consult)throws InstantiationException, SQLException , IllegalAccessException
    {
        ResultSet resultado=null;
        {
            try{
                statement=conectar.ConectarBD().prepareStatement(consult);
                resultado=statement.executeQuery();
                
            }catch(Exception ex){
                System.out.println("Erro ao consultar"+ ex.getMessage());
               
            }
        }
        return resultado;
   }
    
     public ResultSet consultar1 (String tabela, String campos)throws InstantiationException, SQLException , IllegalAccessException
    {
        ResultSet resultado=null;
        String query= "select" + campos +  "from" + tabela;
        {
            try{
                statement=conectar.ConectarBD().prepareStatement(query);
                resultado=statement.executeQuery();
                
            }catch(Exception ex){
                System.out.println("Erro ao consultar"+ ex.getMessage());
               
            }
        }
        return resultado;
   }
     
          public ResultSet consultar2 (String tabela, String campos,String condicao)throws InstantiationException, SQLException , IllegalAccessException
    {
        ResultSet resultado=null;
        String query= "select" + campos +  "from" + tabela + "where" + condicao;
        {
            try{
                statement=conectar.ConectarBD().prepareStatement(query);
                resultado=statement.executeQuery();
                
            }catch(Exception ex){
                System.out.println("Erro ao consultar"+ ex.getMessage());
               
            }
        }
        return resultado;
   }
          
          
         
}



