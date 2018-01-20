/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controlo.ProdutoDAO;
import Controlo.UtilizadorDAO;
import Modelo.Categoria;
import Modelo.Produto;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author ambilabila
 */
@Named
@SessionScoped
public class ProdutoCDI implements Serializable {

    private static final long serialVersionUID = 1L;

    private Part part;
    private String statusMessage;
    private String fileName = "N";

    private String ID;
    private Produto PRODUTO;

    FacesContext contexto;
    private PieChartModel grafico_estoque;
    private int qtd_estoque;
    private int qtd_total_estoque;
    private String valor_estoque;

    @Inject
    private Produto produto;
    private Categoria categoria;
    private List<Produto> produtos;

    @Inject
    private ProdutoDAO pdao;
    private UtilizadorDAO ut;

    // --------------------- Gets e Sets--------------------
    public UtilizadorDAO getUt() {
        return ut;
    }

    public void setUt(UtilizadorDAO ut) {
        this.ut = ut;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Produto getPRODUTO() {
        return PRODUTO;
    }

    public void setPRODUTO(Produto PRODUTO) {
        this.PRODUTO = PRODUTO;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getQtd_total_estoque() {
        return qtd_total_estoque;
    }

    public void setQtd_total_estoque(int qtd_total_estoque) {
        this.qtd_total_estoque = qtd_total_estoque;
    }

    public String getValor_estoque() {
        return valor_estoque;
    }

    public void setValor_estoque(String valor_estoque) {
        this.valor_estoque = valor_estoque;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public PieChartModel getGrafico_estoque() {
        return grafico_estoque;
    }

    public void setGrafico_estoque(PieChartModel grafico_estoque) {
        this.grafico_estoque = grafico_estoque;
    }

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

    public FacesContext getContexto() {
        return contexto;
    }

    public void setContexto(FacesContext contexto) {
        this.contexto = contexto;
    }

    public ProdutoDAO getPdao() {
        return pdao;
    }

    public void setPdao(ProdutoDAO pdao) {
        this.pdao = pdao;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //---------------------------------------------------------------------
    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("***** partHeader: " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }

    public void uploadFile() throws IOException {

        FacesContext mensagem = FacesContext.getCurrentInstance();

        // Extract file name from content-disposition header of file part
        fileName = getFileName(part);

        InputStream inputStream = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            inputStream = part.getInputStream();
            //outputStream = new FileOutputStream(outputFilePath);

            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            // Coloca a imagem no campo desejado em bytes
            produto.setImagem(out.toByteArray());

            statusMessage = "Imagem " + fileName + " carregado";
            mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", statusMessage));
        } catch (IOException e) {
            e.printStackTrace();
            statusMessage = "Erro ao carregar a imagem " + fileName;
            mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", statusMessage));
        } finally {
            if (out != null) {
                out.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    public void salvar(String local) {
        contexto = FacesContext.getCurrentInstance();

        try {

            if (produto.getQuantidade() == 0) {
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", "A quantidade não pode ser zero(0), volte na secção anterior para editar a quantidade"));
            } else {

                ut = new UtilizadorDAO();
                String localID = ut.listar_Local_id(local);
                produto.setLocal(localID);

                if (part != null) {
                    uploadFile(); // carregar a imagem            
                }
                pdao.salvar(produto, categoria);

                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Produto adicionado ao estoque com sucesso!"));

                produto.setQuantidade(0);
                produto.setReferencia("");

                categoria.setNome("");
                categoria.setPreco("");

                part = null;
            }
        } catch (Exception e) {
            //   contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info:", e.getMessage()));
        }
    }
    //---------------------------------------------------------------------

    public void alterar() {
        try {
            pdao.alterar(produto);

            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Produto alterado com sucesso!"));

        } catch (Exception e) {
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info:", e.getMessage()));

        }
    }
    //---------------------------------------------------------------------

    public void listar_produto(String local) {
        try {
            ut = new UtilizadorDAO();
            String localID = ut.listar_Local_id(local);
            produtos = pdao.lista_produtos(localID);

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }
    //---------------------------------------------------------------------

    public void listar_produto_categoria(String ref) {
        try { 
 
            produtos = pdao.lista_produtos_categoria(ref);
            produto.setReferencia("");

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }

    public void listar_produto_categoria_pesquisa(String ref) {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        try {

            produtos = pdao.lista_produtos_categoria_pesquisa(ref);
            extContext.redirect("novaRequisicao.xhtml?categoria=pesquisa");

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }

    public void listar_produto_categoria_pesquisa_Novo(String ref) {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        try {

            produtos = pdao.lista_produtos_categoria_pesquisa(ref);
            extContext.redirect("novaRequisicao.xhtml?categoria=pesquisa");

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }
    //---------------------------------------------------------------------

    public void listar_Umproduto(int id) {
        try {

            produto = pdao.Um_produto(id);

        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            contexto.addMessage(null, mensagem);
        }
    }
    //---------------------------------------------------------------------

    public String onFlowProcess(FlowEvent event) {
        return pdao.onFlowProcess(event);
    }
    //-------------------------------------------------------------------

    public void alterar_qtd_produto(int prod, int qtd, String ref, String obs, int id_categoria, String preco) throws InstantiationException, SQLException, IllegalAccessException {

        pdao.alterar_qtd_produto(prod, qtd, ref, obs, id_categoria, preco);

        contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info:", "Produto em estoque, foi alterado com sucesso!"));
    }

    public void cancelar_alterar_qtd_produto() {
        contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info:", "Cancelou a alteração do produto no estoque!"));
    }

    public void tirar_produto_estoque(int id_prod) throws InstantiationException, SQLException, IllegalAccessException {

        pdao.tirar_produto_estoque(id_prod);
        contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info:", "O produto foi tirado do estoque com sucesso!"));
    }

    /**
     * Metodos para construir o report dos produtos do Estoque
     */
    public void criar_report_estoque(String criterio1, String criterio2, String criterio3) throws InstantiationException, SQLException, IllegalAccessException {
        grafico_estoque = new PieChartModel();

        grafico_estoque.set("Papel e Escrita -(" + 540 + ")", 540);
        grafico_estoque.set("Impressão e Consumíveis", 325);
        grafico_estoque.set("Arquivo e Organização", 702);
        grafico_estoque.set("Material e Escritório", 421);
        grafico_estoque.set("Outras", 100);

        grafico_estoque.setLegendPosition("e");
        grafico_estoque.setFill(true);
        grafico_estoque.setShowDataLabels(true);
        grafico_estoque.setDiameter(250);

        //  qtd_estoque = pdao.qtd_produto_estoque();
        qtd_total_estoque = pdao.qtd_total_produto_estoque();
        valor_estoque = "" + pdao.valor_total_produto_estoque();
    }
    //------------------------------------------------------------------------- 

    public void concluir() {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();

        try {
            produto = new Produto();
            extContext.redirect("index_adm.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ProdutoCDI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int Quantidade_produto(String local) throws InstantiationException, SQLException, IllegalAccessException {
        ut = new UtilizadorDAO();
        String localID = ut.listar_Local_id(local);

        qtd_total_estoque = pdao.qtd_produto_estoque(localID);
        return qtd_total_estoque;
    }

    /*/------------------------- Export report ________________________________
    
     public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
 
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "img" + File.separator + "logo_MDS.png";
         
        pdf.add(Image.getInstance(logo));
    } */
}
