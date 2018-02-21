/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlo;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import Modelo.Perfil;
import Modelo.Pessoa;
import java.sql.ResultSet;
import SQL.conexao;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author hp
 */
public class PerfilDAO implements Serializable{
    private static final long serialVersionUID = 1L;

    
    
    @Inject
    private Perfil perfil ;
    private List<Perfil> lista_perfil;
    private Operacao op ;
    private ResultSet rs ;

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfil> getLista_perfil() {
        return lista_perfil;
    }

    public void setLista_perfil(List<Perfil> lista_perfil) {
        this.lista_perfil = lista_perfil;
    }
    
 
    public Operacao getOp() {
        return op;
    }

    public void setOp(Operacao op) {
        this.op = op;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
   //-------------------------Metodos----------------------------------------------------------------- 
       public void salvar( Perfil perfil) throws InstantiationException, SQLException, IllegalAccessException
    {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String mensagem;
        boolean validar=false;
        
        try
        {
         validar=op.inserir("perfil", "id," + "perfil" ,"'" +perfil.getId() + "'," + "'" + perfil.getPerfil() + "'");
         
         if(validar== true){
            mensagem="Perfil guardado com sucesso.";
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensagem));
         }
         else {
          mensagem="Perfil  não guardado com sucesso.";
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensagem));
             
         }
        }
        catch (Exception ex)
        {
            System.out.println("Erro ao inserir:->"+ ex);
        }
        
        conexao.fecharBD();
        
    }

       public List <Perfil> listar () throws InstantiationException, SQLException, IllegalAccessException
       {
           rs= op.consultar1("perfil","*");
           
           while(rs.next()){
              Perfil p= new Perfil();
              
              
         
              p.setId(rs.getInt("id"));
           
             p.setPerfil(rs.getString("perfil"));
              lista_perfil.add(p);
           }
           
           conexao.fecharBD();
           return lista_perfil;
       }      
       
       
       
       public void alterar( Perfil p) throws InstantiationException, SQLException , IllegalAccessException
       {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String msg;
            boolean valida=false;
           try{
               
               valida=op.alterar("perfil","'"+ p.getPerfil()+ "'" , "'"+p.getId()+"'");
               if (valida== true){
                   msg="Perfil alterado com sucesso";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));}
                   else {
                   msg = "Erro ao tentar alterar o perfil";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
               }
           }catch(Exception ex)
           {
               System.out.println("Erro ao tentar alterar"+ ex.getMessage());
           }}
              
       
       
        public void alterar_semcondicao( Perfil p) throws InstantiationException, SQLException , IllegalAccessException
       {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String msg;
            boolean valida=false;
           try{
               
               valida=op.alterar2("perfil","'"+ p.getPerfil()+ "'" );
               if (valida== true){
                   msg="Perfil alterado com sucesso";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));}
                   else {
                   msg = "Erro ao tentar alterar o perfil";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
               }
           }catch(Exception ex)
           {
               System.out.println("Erro ao tentar alterar"+ ex.getMessage());
           }}

         public Perfil listarperfil(String perfil) throws InstantiationException, SQLException , IllegalAccessException
       {
            boolean valida=false;
            
            lista_perfil = new ArrayList<>();
            rs=op.consultar2("perfil" ,"* LIKE","%'" + perfil + "%'");
            
            while(rs.next())
            {
                Perfil p = new Perfil();
                p.setId(rs.getInt("id"));
                p.setPerfil(rs.getString("perfil"));
                
                lista_perfil.add(p);
                valida= true;
                
                
            }
           if(valida==false)
           {
                String msg = "o perfil desejado nao encontra-se na BD.";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro",msg));
           }
          return null;
       }
        
        
        
public void eliminar( Perfil p) throws InstantiationException, SQLException , IllegalAccessException
       {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String msg;
            boolean valida=false;
           try{
               
               valida=op.eliminar("perfil", "'"+p.getId()+"'");
               if (valida== true){
                   msg="Perfil eliminado com sucesso";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação",msg));}
                   else {
                   msg = "Erro ao tentar eliminar o perfil";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro",msg));
               }
           }catch(Exception ex)
           {
               System.out.println("Erro ao tentar eliminar"+ ex.getMessage());
           }}


public Perfil Uma_perfil(int code) throws InstantiationException, SQLException, IllegalAccessException {

        rs = op.consultar2("perfil", "*", "id='" + code + "'");
        Perfil p  = new Perfil();

        if (rs.next()) {

            p.setId(rs.getInt("id"));
           
             p.setPerfil(rs.getString("perfil"));
              
               
                     
                     //Bila ajuda
               // p.setId_contact(contact.Uma_contacto(rs.getInt("contacto_id")));
                       
        }

        conexao.fecharBD();
        return p;
    }

}
