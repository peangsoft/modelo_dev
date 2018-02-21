/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlo.PerfilDAO;
import Controlo.SessionBean;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.jni.SSLContext;

/**
 *
 * @author hp
 */

// Esta é a class do login

@ManagedBean
@SessionScoped
public class Inicio implements Serializable{
    
    private static final long serialVersionUID = 1094801825228386363L;
    
    private int id;
    private String username;
    private String senha;
    private  String datalogin;
    private int idperfil;
    private int  idpessoa;
    private int estado;

    public Inicio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDatalogin() {
        return datalogin;
    }

    public void setDatalogin(String datalogin) {
        this.datalogin = datalogin;
    }

    public int getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(int idperfil) {
        this.idperfil = idperfil;
    }

    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    public  String desligar_sessao(){
        HttpSession sessao = SessionBean.getSession();
        sessao.invalidate();
        username=null;
        senha=null;
        return "login";
    }
    
    public void terminar_sessao() throws IOException{
        String fim =desligar_sessao();
        System.out.println(fim);
    }
    
     public void verificar_login() throws IOException {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();

        if (username == null && senha == null) {
            extContext.redirect("login.xhtml");
            
            
            
        
        }
    }
     
    public void efectuar_login(String username, String senha, String perfil) throws IOException, InstantiationException, SQLException, IllegalAccessException 
            {
                FacesContext fContext = FacesContext.getCurrentInstance();
                ExternalContext extContext = fContext.getExternalContext();
                PerfilDAO p = new PerfilDAO();
                Perfil perf =  new Perfil();
                if(this.username.equalsIgnoreCase(username) && this.senha.equalsIgnoreCase(senha))
                    estado=1;
                if (estado == 1 && p.Uma_perfil(id).equals(perf.getId()) && p.getPerfil().equals(perfil))
                {
                    extContext.redirect("pag_perfil.xhtml");
                }
                else 
                {
                    estado=0;
                    FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Dados incorretos",
                            "Por favor insira-os corretamente para aceder o sistema"));
                }
            }
}
