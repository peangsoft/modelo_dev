/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlo;


import java.sql.SQLException;
import Modelo.Inicio;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author hp
 */
public class InicioDAO implements Serializable{

   
private Inicio log;

    public Inicio getLog() {
        return log;
    }

    public void setLog(Inicio log) {
        this.log = log;
    }
    
    
    
    
    /* public boolean autenticar(String username, String senha,String perfil)throws InstantiationException, SQLException , IllegalAccessException
    {
         FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        String msg;
        
        log = new Inicio();
        
        boolean valida=false;
        
        try{
        if(log.getUsername().equalsIgnoreCase(username) && log.getSenha().equalsIgnoreCase(senha))
        {
            log.setUsername(username);
            log.setSenha(senha);
            log.setEstado(1);
            log.setIdperfil();
            
           
            
             
        }
        
        if(log!=null && log.getEstado()==1)
        {
            msg="login efectuado com sucesso";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação",msg));
            return true;
        }
        
        
        
        else {
             msg = "Erro ao tentar efectuar o login";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro",msg));
        
      
        }
    }catch( Exception e)
    {
        System.out.println("errro->"+e.getMessage());
    }
    return false;
    }
     */
     
}
