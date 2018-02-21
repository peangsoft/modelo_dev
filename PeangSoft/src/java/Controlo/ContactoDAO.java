/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlo;

import Modelo.Categoria;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import Modelo.Contacto;
import SQL.conectarBD;
import java.sql.ResultSet;
import SQL.conexao;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class ContactoDAO implements  Serializable{
    
    private static final long serialVersionUID = 1L;

    
    
    @Inject
    private Contacto contact ;
    private List<Contacto> lista_contacContactos;
    private Operacao op ;
    private ResultSet rs ;

    public Contacto getContact() {
        return contact;
    }

    public void setContact(Contacto contact) {
        this.contact = contact;
    }

    public List<Contacto> getLista_contacContactos() {
        return lista_contacContactos;
    }

    public void setLista_contacContactos(List<Contacto> lista_contacContactos) {
        this.lista_contacContactos = lista_contacContactos;
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
       public void salvar( Contacto contacto) throws InstantiationException, SQLException, IllegalAccessException
    {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String mensagem;
        boolean validar=false;
        
        try
        {
         validar=op.inserir("contacto", "id," + "telefone"+"email"+"morada"  ,"'" +contacto.getId() + "'," + "'" + contacto.getTelefone() +"',"+ "'"+ contacto.getEmail()+"',"+ "'"+ contacto.getMorada()+ "'");
         
         if(validar== true){
            mensagem="Contacto guardado com sucesso.";
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensagem));
         }
         else {
          mensagem="Contacto  não guardado com sucesso.";
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensagem));
             
         }
        }
        catch (Exception ex)
        {
            System.out.println("Erro ao inserir:->"+ ex);
        }
        
        conexao.fecharBD();
        
    }

       public List <Contacto> listar () throws InstantiationException, SQLException, IllegalAccessException
       {
           rs= op.consultar1("contacto","*");
           
           while(rs.next()){
              Contacto cont= new Contacto();
              
              
         
              cont.setId(rs.getInt("id"));
           
             cont.setEmail(rs.getString("email"));
                cont.setMorada(rs.getString("morada"));
                   cont.setTelefone(rs.getString("telefone"));
              lista_contacContactos.add(cont);
           }
           
           conexao.fecharBD();
           return lista_contacContactos;
       }      
       
       public Contacto Uma_contacto(int code) throws InstantiationException, SQLException, IllegalAccessException {

        rs = op.consultar2("contacto", "*", "id='" + code + "'");
        Contacto c  = new Contacto();

        if (rs.next()) {

            c.setId(rs.getInt("id"));
            c.setEmail(rs.getString("email"));
            c.setMorada(rs.getString("morada"));
            c.setTelefone(rs.getString("telefone"));
           
        }

        conexao.fecharBD();
        return c;
    }
       
       
       public void alterar( Contacto cont) throws InstantiationException, SQLException , IllegalAccessException
       {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String msg;
            boolean valida=false;
           try{
               
               valida=op.alterar("contacto","'"+ cont.getEmail() +"'"+ cont.getMorada()+"'"+ cont.getTelefone()+"'" , "'"+cont.getId()+"'");
               if (valida== true){
                   msg="Contacto alterado com sucesso";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));}
                   else {
                   msg = "Erro ao tentar alterar o contacto";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
               }
           }catch(Exception ex)
           {
               System.out.println("Erro ao tentar alterar"+ ex.getMessage());
           }}
              
       
       
        public void alterar_semcondicao( Contacto cont) throws InstantiationException, SQLException , IllegalAccessException
       {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String msg;
            boolean valida=false;
           try{
               
               valida=op.alterar2("contacto","'"+ cont.getId()+ "'" );
               if (valida== true){
                   msg="contacto alterado com sucesso";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));}
                   else {
                   msg = "Erro ao tentar alterar o contacto";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
               }
           }catch(Exception ex)
           {
               System.out.println("Erro ao tentar alterar"+ ex.getMessage());
           }}

         public Contacto listarContacto(String contact) throws InstantiationException, SQLException , IllegalAccessException
       {
            boolean valida=false;
            
            lista_contacContactos = new ArrayList<>();
            rs=op.consultar2("contacto" ,"* LIKE","%'" + contact + "%'");
            
            while(rs.next())
            {
                Contacto cont = new Contacto();
                cont.setId(rs.getInt("id"));
                cont.setEmail(rs.getString("email"));
                 cont.setTelefone(rs.getString("telefone"));
                  cont.setMorada(rs.getString("morada"));
                
                lista_contacContactos.add(cont);
                valida= true;
                
                
            }
           if(valida==false)
           {
                String msg = "o contacto desejado nao encontra-se na BD.";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro",msg));
           }
          return null;
       }
        
        
        
public void eliminar( Contacto cont) throws InstantiationException, SQLException , IllegalAccessException
       {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String msg;
            boolean valida=false;
           try{
               
               valida=op.eliminar("contacto", "'"+cont.getId()+"'");
               if (valida== true){
                   msg="contacto eliminado com sucesso";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação",msg));}
                   else {
                   msg = "Erro ao tentar eliminar o contacto";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro",msg));
               }
           }catch(Exception ex)
           {
               System.out.println("Erro ao tentar eliminar"+ ex.getMessage());
           }}

}
