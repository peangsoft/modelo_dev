
package Modelo;

import java.io.Serializable;

/**
 *
 * @author ambilabila
 */

public class Utilizador implements Serializable{
    
    private static  final long serialVersionUID = 1L; 
    
    private String id;
    private String nome_proprio;
    private String email;
    private String nivel;
    private String departamento;
    private String foto;
    private String local;
    
    
    public Utilizador() {
    }

    
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    
    public String getId() {
        return id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNome_proprio() {
        return nome_proprio;
    }

    public void setNome_proprio(String nome_proprio) {
        this.nome_proprio = nome_proprio;
    }

   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}
