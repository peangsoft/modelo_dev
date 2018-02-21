/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author hp
 */
public class Pessoa implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    
    private int id;
    private int id_contact;
    private String nome;
    private String apelido;
    private String genero;
    private String foto;
    private String nacionalidade;
    private String naturalidade;
    private String data_nasc;

    public Pessoa() {
    }
    
    
    
    public int getId()
    {
        return id;
    }
    
   
     
     
     public String getNome()
     { 
         return nome;
     }
      public String getGenero()
     { 
         return genero;
     }
      
     
             
    public void  setId (int id){
         this.id=id;
    }
           
    
    public void SetNome( String nome )
    {
        this.nome=nome;
    }

    public int getId_contact() {
        return id_contact;
    }

    public void setId_contact(int id_contact) {
        this.id_contact = id_contact;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

        public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }
    
    
    
    
    
}
