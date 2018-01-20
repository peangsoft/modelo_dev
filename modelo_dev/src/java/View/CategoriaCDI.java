/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controlo.CategoriaDAO;
import Modelo.Categoria;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ambilabila
 */
@Named
@SessionScoped
public class CategoriaCDI implements Serializable {

    private static final long serialVersionUID = 1L;
    FacesContext contexto;

    @Inject
    private Categoria categoria;
    private List<Categoria> categorias;
    private List<String> fornecedores;
    private List<String> modelos;

    @Inject
    private CategoriaDAO cdao;

    private boolean verificador = true; 
    private String pesquisa =""; 

    // --------------------- Gets e Sets--------------------

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    
    public boolean isVerificador() {
        return verificador;
    }

    public void setVerificador(boolean verificador) {
        this.verificador = verificador;
    }

    public FacesContext getContexto() {
        return contexto;
    }

    public void setContexto(FacesContext contexto) {
        this.contexto = contexto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public CategoriaDAO getCdao() {
        return cdao;
    }

    public void setCdao(CategoriaDAO cdao) {
        this.cdao = cdao;
    }

    public List<String> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<String> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public List<String> getModelos() {
        return modelos;
    }

    public void setModelos(List<String> modelos) {
        this.modelos = modelos;
    }

//---------------------------------------------------------------------
    public void salvar() {
        contexto = FacesContext.getCurrentInstance();
        try {
            if (verificador) {
                cdao.salvar(categoria);

                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Artigo guardado com sucesso!"));
                categoria.setNome("");
                categoria.setPreco("");
            } else {
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção, impossível guardar:", "O artigo já existe na base de dados!"));
            }

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }

    public void alterar() {
        contexto = FacesContext.getCurrentInstance();
        try {
            cdao.alterar(categoria);

            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Categoria alterada com sucesso!"));

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }
    
    public void alterar_Preco() {
        contexto = FacesContext.getCurrentInstance();
        try {
            cdao.alterar_preco(categoria);
            
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }

    public void listar_categoria() {
        try {

            categorias = cdao.lista_categorias();

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }
    public void listar_categoria(String pesquisa) {
        try {
            
            categorias = cdao.lista_categorias(pesquisa);  
            
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }
    //---------------------------------------------------------------------

    public void listar_Umacategoria(int code) {
        try {

            categoria = cdao.Uma_categoria(code);

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }
    //--------------------------------------------------------------------- 

    public void listar_categorias_filtro() {
        try {
            String chekes = "";

            categorias = cdao.lista_categorias();

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }

    public List<String> listar_fornecedores() {
        try {

            fornecedores = cdao.lista_fornecedores();

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
        return fornecedores;
    }

    //-------------------------------------------------------------------
    public void lista_modelos() {
        try {

            modelos = cdao.lista_modelos();

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }

    //---------------------------------------------------------------------
    public List<String> lista_modelo() {
        try {

            modelos = cdao.lista_modelos();

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
        return modelos;
    }

    public void cancelar_alteracao_categoria() throws InstantiationException, SQLException, IllegalAccessException {
        contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info:", "Alteração cancelada!"));
    }

    public void cancelar_Novo_artigo() throws InstantiationException, SQLException, IllegalAccessException, IOException {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();

        extContext.redirect("index_adm.xhtml");
    }

    public void eliminar_artigo(int id) throws InstantiationException, SQLException, IllegalAccessException, IOException {

        cdao.eliminar(id);

        contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Artigo eliminado com sucesso!"));
    }

    public void alterar_artigo(int id, String nome, String fornecedor, String preco, String modelo) throws InstantiationException, SQLException, IllegalAccessException {
        cdao.alterar_artigo(id, nome, fornecedor, preco, modelo);

        contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Alteração feita com sucesso!"));
    }

    //--------------------------------------------------------------------------------
    public void ver_artigo(String nome) throws InstantiationException, SQLException, IllegalAccessException {
        //  cdao.alterar_artigo(id, nome, fornecedor, preco, modelo);
        boolean ver;
        ver = cdao.ver_artigo(nome);
        if (ver) {
            verificador = false;
            contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", "Artigo já existente na base de dados!"));
        } else {
            verificador = true;
        }

    }
    
}
