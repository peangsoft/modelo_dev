/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controlo.UtilizadorDAO;
import Modelo.Login;
import Modelo.Utilizador;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author ambilabila
 */
@Named
@RequestScoped
public class UtilizadorCDI implements Serializable {

    private static final long serialVersionUID = 1L;

    private BarChartModel grafico_atividades_user;

    @Inject
    private Utilizador utilizador;
    private List<Utilizador> utilizadores;

    private List<String> departamentos;
    private List<String> locais;
    private String departamento;
    private String id_departamento;

    private String tag;

    private String pesquisa;
    private String local;
    private String localID;
    

    private List<Utilizador> listaUtilizadores;

    FacesContext fContext = FacesContext.getCurrentInstance();
    ExternalContext extContext = fContext.getExternalContext();

    @Inject
    private UtilizadorDAO udao;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getLocalID() {
        return localID;
    }

    public void setLocalID(String localID) {
        this.localID = localID;
    }

    public List<String> getLocais() {
        return locais;
    }

    public void setLocais(List<String> locais) {
        this.locais = locais;
    }

    public List<Utilizador> getListaUtilizadores() {
        return listaUtilizadores;
    }

    public void setListaUtilizadores(List<Utilizador> listaUtilizadores) {
        this.listaUtilizadores = listaUtilizadores;
    }

    public BarChartModel getGrafico_atividades_user() {
        return grafico_atividades_user;
    }

    public void setGrafico_atividades_user(BarChartModel grafico_atividades_user) {
        this.grafico_atividades_user = grafico_atividades_user;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public List<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(List<Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }

    public UtilizadorDAO getUdao() {
        return udao;
    }

    public void setUdao(UtilizadorDAO udao) {
        this.udao = udao;
    }

    public List<String> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<String> departamentos) {
        this.departamentos = departamentos;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(String id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

// Metodos
    public void salvar(String op) {
        udao = new UtilizadorDAO();
        try {
            udao.salvar(utilizador, op);

            Login.PessoaEmail = utilizador.getEmail();
            Login.Pessoanome = utilizador.getNome_proprio();
            Login.Pessoadep = utilizador.getDepartamento();
            Login.Pessoalocal = utilizador.getLocal();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void salvar(String p, String a) {
        udao = new UtilizadorDAO();
        try {
            udao.salvar(utilizador, p, "nada");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void salvar() {
        udao = new UtilizadorDAO();
        try {
            udao.salvar(utilizador);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //-----------------------------------------------------------------------
    public void cancelarEditperssoa() throws IOException {
        extContext.redirect("index_user.xhtml");
    }
    //---------------------------------------------------------------------

    public void requisitarP() {

        try {

            Login.PessoaEmail = utilizador.getEmail();
            Login.Pessoanome = utilizador.getNome_proprio();
            Login.Pessoadep = utilizador.getDepartamento();

            extContext.redirect("homeRequisicao_adm.xhtml");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void alterar_local(String email) {
        try {
            utilizador.setEmail(email);
            udao.alterar_local(utilizador);

            extContext.redirect("index_adm.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterar() {
        try {
            udao.alterar(utilizador);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void listar() {
        try {

            utilizadores = udao.listar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Listautilizador(String user) {
        try {
            utilizador = udao.ListaUmUtilizador(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------
    public void ListarPessoa() {
        try {
            utilizador = udao.ListaUmaPessoa(pesquisa);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ListarPessoas() {
        try {
            utilizadores = udao.ListaPessoas(pesquisa);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String onFlowProcess(FlowEvent event) {

        return udao.onFlowProcess(event);
    }

// DEPARTAMENTO
    public void ID_departamento(String dep) throws InstantiationException, SQLException, IllegalAccessException {
        id_departamento = udao.listarCodDepartamento(dep);
    }

    public void NOME_departamento(String id) throws InstantiationException, SQLException, IllegalAccessException {
        departamento = udao.listarUmDepartamentos(id);
    }

    public void NOME_departamentos() throws InstantiationException, SQLException, IllegalAccessException {
        departamentos = udao.listarDepartamentos();
    }

//END DEPARTAMENTO
    public void NOME_locais() throws InstantiationException, SQLException, IllegalAccessException {
        locais = udao.listar_Local();
    }

    //--------------------- grafico estatisticos--------------------------
    public void createAnimatedModels(String email) throws InstantiationException, SQLException, IllegalAccessException {
        Axis yAxis;

        grafico_atividades_user = initBarModel(email);
        grafico_atividades_user.setAnimate(true);
        grafico_atividades_user.setShowDatatip(false);
        grafico_atividades_user.setShowPointLabels(true);
        grafico_atividades_user.setLegendPosition("e");

        yAxis = grafico_atividades_user.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private BarChartModel initBarModel(String email) throws InstantiationException, SQLException, IllegalAccessException {
        BarChartModel model = new BarChartModel();

        ChartSeries sugestoes = new ChartSeries();
        sugestoes.setLabel("Sugestões");
        sugestoes.set("2016", udao.quantidade_sugestao(email));
        sugestoes.set("2017", 0);
        sugestoes.set("2018", 0);
        sugestoes.set("2019", 0);
        sugestoes.set("2020", 0);

        ChartSeries pendentes = new ChartSeries();
        pendentes.setLabel("Pendentes");
        pendentes.set("2016", udao.quantidade_pendentes(email));
        pendentes.set("2017", 0);
        pendentes.set("2018", 0);
        pendentes.set("2019", 0);
        pendentes.set("2020", 0);

        ChartSeries respostas = new ChartSeries();
        respostas.setLabel("Respostas");
        respostas.set("2016", udao.quantidade_respostas(email));
        respostas.set("2017", 0);
        respostas.set("2018", 0);
        respostas.set("2019", 0);
        respostas.set("2020", 0);

        ChartSeries pedidos = new ChartSeries();
        pedidos.setLabel("Pedidos");
        pedidos.set("2016", udao.quantidade_pedidos(email));
        pedidos.set("2017", 0);
        pedidos.set("2018", 0);
        pedidos.set("2019", 0);
        pedidos.set("2020", 0);

        model.addSeries(pedidos);
        model.addSeries(respostas);
        model.addSeries(pendentes);
        model.addSeries(sugestoes);

        return model;
    }

//Local check
    public void redirecionar_local() throws IOException {
       if(tag!=null) 
        if (tag.equals("n")) {
            extContext.redirect("confirmar_local.xhtml");
        }
    }
    
    // tratal do local
    public String listar_Local_id(String local) throws InstantiationException, SQLException, IllegalAccessException {
        localID = udao.listar_Local_id(local);
        
        return localID;
    }
    public String listar_Local_nome(String id) throws InstantiationException, SQLException, IllegalAccessException {
        local = udao.listar_Local_nome(id);
        
        return local;
    }
}
