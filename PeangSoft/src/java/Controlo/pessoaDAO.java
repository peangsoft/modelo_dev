/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlo;

import Modelo.Categoria;
import Modelo.Contacto;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import Modelo.Pessoa;
import java.sql.ResultSet;
import SQL.conexao;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
public class pessoaDAO implements  Serializable {
    
    private static final long serialVersionUID = 1L; 
    @Inject
    private Pessoa p ;
    @Inject
    private ContactoDAO contact;
    
    
    private List<Pessoa> lista_Pessoa;
    private Operacao op ;
    private ResultSet rs ;

    public Pessoa getP() {
        return p;
    }

    public void setP(Pessoa p) {
        this.p = p;
    }

    public List<Pessoa> getLista_Pessoa() {
        return lista_Pessoa;
    }

    public void setLista_Pessoa(List<Pessoa> lista_Pessoa) {
        this.lista_Pessoa = lista_Pessoa;
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
       public void salvar( Pessoa p) throws InstantiationException, SQLException, IllegalAccessException
    {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String mensagem;
        boolean validar=false;
        
        try
        {
         validar=op.inserir("pessoa", "id," + "nome"+"apelido"+"genero"+"foto"+"data_nasc"+"naturalidade"+"contacto"+"nacionalidade"   ,"'" +p.getId() + "'," + "'" + p.getNome() +"',"+ "'"+ p.getApelido()+ "',"+"'"+ p.getGenero()+ "',"+"'"+ p.getFoto()+ "',"+"'"+ p.getData_nasc()+ "',"+"'"+ p.getNaturalidade()+ "',"+"'"+ p.getId_contact()+ "',"+"'"+ p.getNacionalidade()+ "'");
         
         if(validar== true){
            mensagem="pessoa guardado com sucesso.";
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensagem));
         }
         else {
          mensagem="pessoa  não guardado com sucesso.";
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensagem));
             
         }
        }
        catch (Exception ex)
        {
            System.out.println("Erro ao inserir:->"+ ex);
        }
        
        conexao.fecharBD();
        
    }

       public List <Pessoa> listar () throws InstantiationException, SQLException, IllegalAccessException
       {
           Pessoa p = new Pessoa();
            contact = new ContactoDAO();
           rs= op.consultar1("pessoa","*");
           
           while(rs.next()){
              
             
              
         
         
              p.setId(rs.getInt("id"));
           
             p.SetNome(rs.getString("email"));
              p.setApelido(rs.getString("apelido"));
                 p.setNaturalidade(rs.getString("naturalidade"));
                  p.setNacionalidade(rs.getString("nacionalidade"));
                   p.setFoto(rs.getString("foto"));
                    p.setGenero(rs.getString("genero"));
                     p.setData_nasc(rs.getString("data_nasc"));
               
                     
                     //Bila ajuda
               // p.setId_contact(contact.Uma_contacto(rs.getInt("contacto_id")));
                       
                                          
              lista_Pessoa.add(p);
              
           }
           
           conexao.fecharBD();
           return lista_Pessoa;
       }      
       
       
       
       public void alterar( Pessoa p) throws InstantiationException, SQLException , IllegalAccessException
       {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String msg;
            boolean valida=false;
           try{
               
               valida=op.alterar("pessoa","'" + p.getNome() +"',"+ "'"+ p.getApelido()+ "',"+"'"+ p.getGenero()+ "',"+"'"+ p.getFoto()+ "',"+"'"+ p.getData_nasc()+ "',"+"'"+ p.getNaturalidade()+ "',"+"'"+ p.getId_contact()+ "',"+"'"+ p.getNacionalidade()+ "'","'" +p.getId() + "'");
               if (valida== true){
                   msg="pessoa alterado com sucesso";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));}
                   else {
                   msg = "Erro ao tentar alterar pessoa";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
               }
           }catch(Exception ex)
           {
               System.out.println("Erro ao tentar alterar"+ ex.getMessage());
           }}
              
       
       
        public void alterar_semcondicao( Pessoa p) throws InstantiationException, SQLException , IllegalAccessException
       {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String msg;
            boolean valida=false;
           try{
               
               valida=op.alterar2("pessoa","'"+ p.getId()+ "'" );
               if (valida== true){
                   msg="pessoa alterado com sucesso";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));}
                   else {
                   msg = "Erro ao tentar alterar o pessoa";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
               }
           }catch(Exception ex)
           {
               System.out.println("Erro ao tentar alterar"+ ex.getMessage());
           }}

         public Contacto listarPessoa(String pessoa) throws InstantiationException, SQLException , IllegalAccessException
       {
            boolean valida=false;
            
            lista_Pessoa = new ArrayList<>();
            rs=op.consultar2("pessoa" ,"* LIKE","%'" + p + "%'");
            
            while(rs.next())
            {
                Pessoa p = new Pessoa();
                 p.setId(rs.getInt("id"));
           
             p.SetNome(rs.getString("email"));
              p.setApelido(rs.getString("apelido"));
                 p.setNaturalidade(rs.getString("naturalidade"));
                  p.setNacionalidade(rs.getString("nacionalidade"));
                   p.setFoto(rs.getString("foto"));
                    p.setGenero(rs.getString("genero"));
                     p.setData_nasc(rs.getString("data_nasc"));
               
                     
                     //Bila ajuda
               // p.setId_contact(contact.Uma_contacto(rs.getInt("contacto_id")));
                       
                lista_Pessoa.add(p);
                valida= true;
                
                
            }
           if(valida==false)
           {
                String msg = "o pessoa desejado nao encontra-se na BD.";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro",msg));
           }
          return null;
       }
        
        
        
public void eliminar( Pessoa p) throws InstantiationException, SQLException , IllegalAccessException
       {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String msg;
            boolean valida=false;
           try{
               
               valida=op.eliminar("pessoa", "'"+p.getId()+"'");
               if (valida== true){
                   msg="pessoa eliminado com sucesso";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação",msg));}
                   else {
                   msg = "Erro ao tentar eliminar o pessoa";
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro",msg));
               }
           }catch(Exception ex)
           {
               System.out.println("Erro ao tentar eliminar"+ ex.getMessage());
           }}


public Pessoa Uma_pessoa(int code) throws InstantiationException, SQLException, IllegalAccessException {

        rs = op.consultar2("pessoa", "*", "id='" + code + "'");
        Pessoa p  = new Pessoa();

        if (rs.next()) {

            p.setId(rs.getInt("id"));
           
             p.SetNome(rs.getString("email"));
              p.setApelido(rs.getString("apelido"));
                 p.setNaturalidade(rs.getString("naturalidade"));
                  p.setNacionalidade(rs.getString("nacionalidade"));
                   p.setFoto(rs.getString("foto"));
                    p.setGenero(rs.getString("genero"));
                     p.setData_nasc(rs.getString("data_nasc"));
               
                     
                     //Bila ajuda
               // p.setId_contact(contact.Uma_contacto(rs.getInt("contacto_id")));
                       
        }

        conexao.fecharBD();
        return p;
    }
}
