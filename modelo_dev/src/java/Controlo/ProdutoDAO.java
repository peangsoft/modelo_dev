/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlo;

import Modelo.Categoria;
import Modelo.Produto;
import SQL.Operacao;
import SQL.conectarBD;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author ambilabila
 */
public class ProdutoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean skip;

    public ProdutoDAO() {
        operacao = new Operacao();
    }

    private Produto produto;
    private List<Produto> produtos;

    @Inject
    private CategoriaDAO cdao;

    @Inject
    private Operacao operacao;
    private ResultSet rs = null;

//------------ GET AND SETTS---------------------------
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
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
    //----------------------- MÉTODOS --------------------------------------

    public List<Produto> lista_produtos(String localID) throws InstantiationException, SQLException, IllegalAccessException {

        Categoria categoria = new Categoria();
        produtos = new ArrayList<Produto>();

        rs = operacao.consulta2("tbproduto", "*", "local='"+localID+"'");
        while (rs.next()) {
            produto = new Produto();

            produto.setId(rs.getInt("id"));
            produto.setReferencia(rs.getString("referencia"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produto.setId_categoria(rs.getInt("id_categoria"));
            produto.setObs(rs.getString("obs"));
            produto.setCategoria(cdao.Uma_categoria(rs.getInt("id_categoria")));

            produtos.add(produto);
        }

        conectarBD.fechar();
        return produtos;
    }

    public List<Produto> lista_produtos_categoria(String ref) throws InstantiationException, SQLException, IllegalAccessException {

        if (ref == null | ref.equals("") | ref.equals("pesquisa")) {

        } else {
            Categoria categoria;
            produtos = new ArrayList<Produto>();

            rs = operacao.consulta2("tbproduto", "*", "referencia='" + ref + "'");
            while (rs.next()) {
                produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setReferencia(rs.getString("referencia"));
                produto.setQuantidadeMax(rs.getInt("quantidade"));
                produto.setId_categoria(rs.getInt("id_categoria"));
                produto.setObs(rs.getString("obs"));

                categoria = new Categoria();
                categoria = cdao.Uma_categoria(rs.getInt("id_categoria"));

                produto.setCategoria(categoria);

                produtos.add(produto);
            }

        }
        conectarBD.fechar();
        return produtos;
    }

    public List<Produto> lista_produtos_categoria_pesquisa(String ref) throws InstantiationException, SQLException, IllegalAccessException {

        Categoria categoria;
        produtos = new ArrayList<Produto>();

        rs = operacao.consulta2("tbproduto, tbcategoria", "*", "((tbproduto.referencia like '%" + ref + "%' OR tbproduto.obs like '%" + ref + "%'"
                + "OR tbcategoria.nome like '%" + ref + "%' OR tbcategoria.modelo like '%" + ref + "%' OR tbcategoria.preco like '%" + ref + "%'"
                + "OR tbcategoria.fornecedor like '%" + ref + "%') AND (tbproduto.id_categoria = tbcategoria.id))");

        while (rs.next()) {
            produto = new Produto();

            produto.setId(rs.getInt("id"));
            produto.setReferencia(rs.getString("referencia"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produto.setId_categoria(rs.getInt("id_categoria"));
            produto.setObs(rs.getString("obs"));

            categoria = new Categoria();
            categoria = cdao.Uma_categoria(rs.getInt("id_categoria"));

            produto.setCategoria(categoria);

            produtos.add(produto);
        }

        conectarBD.fechar();
        return produtos;
    }

    //**************************************************************************
    public Produto Um_produto(int id) throws InstantiationException, SQLException, IllegalAccessException {
        rs = operacao.consulta2("tbproduto", "*", "id='" + id + "'");
        produto = new Produto();

        while (rs.next()) {
            produto.setId(rs.getInt("id"));
            produto.setReferencia(rs.getString("referencia"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produto.setId_categoria(rs.getInt("id_categoria"));
            produto.setObs(rs.getString("obs"));

            Categoria categoria = new Categoria();
            categoria = cdao.Uma_categoria(rs.getInt("id_categoria"));

            produto.setCategoria(categoria);
        }
        conectarBD.fechar();
        return produto;
    }
    //**************************************************************************

    public void salvar(Produto produto, Categoria categoria) {

        boolean verificar = false;

        try {
            verificar = operacao.insertFile("tbproduto",
                    "referencia,quantidade,"
                    + "id_categoria,local,obs,imagem",
                    "'" + produto.getReferencia() + "',"
                    + "'" + produto.getQuantidade() + "',"
                    + "'" + produto.getId_categoria() + "',"
                    + "'" + produto.getLocal() + "',"
                    + "'" + produto.getObs() + "',"
                    + "? ",produto.getImagem());
            
           

        } catch (Exception e) {
            System.out.print("Erro na BD "+e.getMessage());
        }

    }

    //**************************************************************************
    public void alterar(Produto produto) {

        boolean verificar = false;
    }

    //**************************************************************************
    public void eliminar(String id) {

        boolean verificar = false;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    //----------------------------------------------------------
    public void alterar_qtd_produto(int id_produto, int qtd, String ref, String obs, int id_categoria, String preco) throws InstantiationException, SQLException, IllegalAccessException {

        String sql = "alterar_qtd_produto(" + id_produto + "," + qtd + ",'" + ref + "','" + obs + "'," + id_categoria + ",'" + preco + "')";

        operacao.procedimento(sql);

        conectarBD.fechar();
    }

    public void tirar_produto_estoque(int id_produto) throws InstantiationException, SQLException, IllegalAccessException {

        String sql = "eliminar_produto_estoque(" + id_produto + ")";
        operacao.procedimento(sql);

    }

    /**
     * Metodos para construir o report dos produtos do Estoque
     */
    public int qtd_produto_estoque(String localID) throws InstantiationException, SQLException, IllegalAccessException {

        rs = operacao.consulta2("tbproduto", "count(id) as qtd", "local='"+localID+"'");
        rs.next();
        int qtd = rs.getInt("qtd");

        conectarBD.fechar();
        return qtd;
    }

    public int qtd_total_produto_estoque() throws InstantiationException, SQLException, IllegalAccessException {

        rs = operacao.consulta1("tbproduto", "sum(quantidade) as soma_qtd");
        rs.next();
        int qtd = rs.getInt("soma_qtd");

        conectarBD.fechar();
        return qtd;
    }

    public String valor_total_produto_estoque() throws InstantiationException, SQLException, IllegalAccessException {
        double soma = 0.0;
        rs = operacao.consulta2("tbproduto as P,tbcategoria as C", "C.preco as valor_estoque, P.quantidade as qtd", "P.id_categoria = C.id");
        while (rs.next()) {
            double valor = Double.parseDouble(rs.getString("valor_estoque").replace(',', '.'));

            soma += valor * rs.getInt("qtd");
        }

        conectarBD.fechar();
        return ("" + String.format("%.2f", soma));
    }

}
