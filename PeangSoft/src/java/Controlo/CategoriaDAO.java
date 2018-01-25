/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlo;

import Modelo.Categoria;
import SQL.Operacao;
import SQL.conectarBD;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ambilabila
 */
public class CategoriaDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public CategoriaDAO() {
        operacao = new Operacao();
    }

    @Inject
    private Categoria categoria;
    private List<Categoria> categorias;
    private List<String> fornecedores;
    private List<String> modelos;

    @Inject
    private Operacao operacao;
    private ResultSet rs = null;
//------------ GET AND SETTS---------------------------

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

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
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

    //----------------------- MÉTODOS --------------------------------------
    public List<Categoria> lista_categorias() throws InstantiationException, SQLException, IllegalAccessException {

        categorias = new ArrayList<Categoria>();

        rs = operacao.consulta1("tbcategoria", "*");
        while (rs.next()) {
            categoria = new Categoria();

            categoria.setId(rs.getInt("id"));
            categoria.setNome(rs.getString("nome"));
            categoria.setFornecedor(rs.getString("fornecedor"));
            categoria.setPreco(rs.getString("preco").replace(',', '.'));
            categoria.setModelo(rs.getString("modelo"));
            categoria.setEstado(rs.getString("estado"));

            categorias.add(categoria);
        }

        conectarBD.fechar();
        return categorias;
    }

    /**
     * Usado em novoProduto,
     */
    public List<Categoria> lista_categorias(String pesquisa) throws InstantiationException, SQLException, IllegalAccessException {

        categorias = new ArrayList<Categoria>();

        if (pesquisa != null || pesquisa.equals("")) {
            rs = operacao.consulta2("tbcategoria", "*", "nome LIKE '%" + pesquisa + "%' OR fornecedor LIKE '%" + pesquisa + "%' OR modelo LIKE '%" + pesquisa.toUpperCase() + "%'");
        } else {
            rs = operacao.consulta1("tbcategoria", "*");
        }
        while (rs.next()) {
            categoria = new Categoria();

            categoria.setId(rs.getInt("id"));
            categoria.setNome(rs.getString("nome"));
            categoria.setFornecedor(rs.getString("fornecedor"));
            categoria.setPreco(rs.getString("preco").replace(',', '.'));
            categoria.setModelo(rs.getString("modelo"));
            categoria.setEstado(rs.getString("estado"));

            categorias.add(categoria);
        }
        conectarBD.fechar();
        return categorias;
    }

    //----------------------- MÉTODOS --------------------------------------
    public List<String> lista_fornecedores() throws InstantiationException, SQLException, IllegalAccessException {

        fornecedores = new ArrayList<String>();

        rs = operacao.consulta1("tbcategoria", "distinct(fornecedor)");
        while (rs.next()) {
            fornecedores.add(rs.getString("fornecedor"));
        }

        conectarBD.fechar();
        return fornecedores;
    }

    //---------------------------------------------------------------------------  
    public List<String> lista_modelos() throws InstantiationException, SQLException, IllegalAccessException {

        modelos = new ArrayList<String>();

        rs = operacao.consulta1("tbcategoria", "distinct(modelo)");
        while (rs.next()) {
            modelos.add(rs.getString("modelo"));
        }

        conectarBD.fechar();
        return modelos;
    }
    //---------------------------------------------------------------------------  

    public Categoria Uma_categoria(int code) throws InstantiationException, SQLException, IllegalAccessException {

        rs = operacao.consulta2("tbcategoria", "*", "id='" + code + "'");
        categoria = new Categoria();

        if (rs.next()) {

            categoria.setId(rs.getInt("id"));
            categoria.setNome(rs.getString("nome"));
            categoria.setFornecedor(rs.getString("fornecedor"));
            categoria.setPreco(rs.getString("preco").replace(',', '.'));
            categoria.setModelo(rs.getString("modelo"));
            categoria.setEstado(rs.getString("estado"));
        }

        conectarBD.fechar();
        return categoria;
    }
    //---------------------------------------------------------------------------  

    //**************************************************************************
    public void salvar(Categoria categoria) throws InstantiationException, SQLException, IllegalAccessException {

        boolean verificar = false;

        try {
            verificar = operacao.inserir("tbcategoria",
                    "nome,"
                    + "fornecedor,"
                    + "preco, "
                    + "modelo",
                    "'" + categoria.getNome() + "',"
                    + "'" + categoria.getFornecedor() + "',"
                    + "'" + categoria.getPreco().replace(',', '.') + "',"
                    + "'" + categoria.getModelo() + "'");

        } catch (Exception e) {
            System.out.print("Erro na BD");
        }
        conectarBD.fechar();
    }

    //**************************************************************************
    public void alterar(Categoria categoria) {

        boolean verificar = false;
    }
    
    public void alterar_preco(Categoria categoria) throws InstantiationException, SQLException, IllegalAccessException {

         operacao.atualizar2("tbcategoria", "preco = '"+categoria.getPreco()+"'", "id ='"+ categoria.getId() + "'");
    }

    //**************************************************************************
    public void eliminar(int id) throws InstantiationException, SQLException, IllegalAccessException {
        String sql = "eliminar_artigo(" + id + ")";
        operacao.procedimento(sql);
    }

    /**
     *
     * @param id
     * @param nome
     * @param fornecedor
     * @param preco
     * @param modelo
     * @throws InstantiationException
     * @throws SQLException
     * @throws IllegalAccessException Alterar um artigo existente na BD.
     */
    public void alterar_artigo(int id, String nome, String fornecedor, String preco, String modelo) throws InstantiationException, SQLException, IllegalAccessException {

        String sql = "alterar_artigo(" + id + ",'" + nome + "','" + fornecedor + "','" + preco.replace(',', '.') + "','" + modelo + "')";

        operacao.procedimento(sql);

        conectarBD.fechar();

    }

    //**********************************************************************
    public boolean ver_artigo(String nome) throws InstantiationException, SQLException, IllegalAccessException {

        rs = operacao.consulta2("tbcategoria", "nome", "nome='" + nome + "'");

        if (rs.next()) {
            conectarBD.fechar();
            return true;

        } else {
            conectarBD.fechar();
            return false;
        }

    }
}
