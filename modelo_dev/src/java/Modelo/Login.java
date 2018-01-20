/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlo.LoginDAO;
import Controlo.SessionBean;
import Controlo.UtilizadorDAO;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ambilabila
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String email;
    private String msg;
    private String user;
    private String parametro;
    private String dep;
    private String nivel;
    private String local = "";
    private String idlocal = "";

    public static String PessoaEmail;
    public static String Pessoanome;
    public static String Pessoadep;
    public static String Pessoalocal;

    public static String getPessoalocal() {
        return Pessoalocal;
    }

    public static void setPessoalocal(String Pessoalocal) {
        Login.Pessoalocal = Pessoalocal;
    }

    public String getPessoaEmail() {
        return PessoaEmail;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPessoanome() {
        return Pessoanome;
    }

    public String getPessoadep() {
        return Pessoadep;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(String idlocal) {
        this.idlocal = idlocal;
    }
       

    //validate login
    public String validateUsernamePassword() throws InstantiationException, SQLException, IllegalAccessException {
        boolean valid = LoginDAO.validate(user, email);

        if (true) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user);
            return "index";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Nome de utilizador ou senha incorreta",
                            "Por favor insira-os corretamente para aceder o sistema"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        user = null;
        email = null;
        return "login";
    }

    public void parametros() throws IOException, InstantiationException, SQLException, IllegalAccessException {
        UtilizadorDAO dAO = new UtilizadorDAO();

        try {
            if (parametro != null) {
                String[] st = parametro.split(",");
                user = st[0];
                msg = st[1];
                email = st[2];

                FacesContext fContext = FacesContext.getCurrentInstance();
                ExternalContext extContext = fContext.getExternalContext();

                // Verificar se o utilizador já tem o seu registo no sistema/ administrador / utilizador normal  
                user = user.trim();
                email = email.trim();

                if (msg.length() < 15) {
                    msg = "img/user-icon.png";
                }

                boolean validacao = dAO.ConfirmaUmUtilizador(email);
                nivel = dAO.getUtilizador().getNivel();
                dep = dAO.getUtilizador().getDepartamento();
             
                if (dAO.getUtilizador().getLocal() != null) {
                    local = dAO.getUtilizador().getLocal();
                    idlocal = dAO.listar_Local_id(local);
                } 
               
                if (validacao) {

                    if (dAO.getUtilizador().getNivel().equals("Gestor")) {
                        if (local.equals("")||local==null) {       
                            extContext.redirect("index_adm.xhtml?local=n");
                        } else {
                            extContext.redirect("index_adm.xhtml");
                        }

                    } else {
                        
                        if (local.equals("") || local == null) {
                        extContext.redirect("index_user.xhtml?local=n");
                        } else {
                        extContext.redirect("index_user.xhtml");
                        }
                    }
                } else {

                    extContext.redirect("utilizadorNovo.xhtml");
                }

            }

        } catch (java.lang.StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    public void verificar_autenticacao() throws IOException {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();

        if (user == null | email == null) {
            extContext.redirect("login.xhtml");
        }

    }

    public void verificar_autenticacao_adm() throws IOException {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();

        if (user == null | email == null) {
            extContext.redirect("login.xhtml");
        } else if (!nivel.equals("Gestor")) {
            extContext.redirect("area_restrita.xhtml");
        }
    }

    public void terminar_utilizador() throws IOException {
        String fim = logout();
        System.out.println(fim);
    }

}
