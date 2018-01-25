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
import javax.enterprise.context.SessionScoped;
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
@SessionScoped
public class UtilizadorCDI1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private BarChartModel grafico_atividades_user;

    @Inject
    private Utilizador utilizador;
    private List<Utilizador> utilizadores;

    private List<String> departamentos;
    private String departamento;
    private String id_departamento;

    private String pesquisa;

    private List<Utilizador> listaUtilizadores;

    @Inject
    private UtilizadorDAO udao;

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
        System.out.println("Entrei aqui" + utilizador.getNome_proprio());
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

// Metodos
    public void salvar(String op) {
        udao = new UtilizadorDAO();
        try {
            udao.salvar(utilizador, op);

            Login.PessoaEmail = utilizador.getEmail();
            Login.Pessoanome = utilizador.getNome_proprio();
            Login.Pessoadep = utilizador.getDepartamento();

            utilizador = new Utilizador();
            pesquisa = "";

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
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();

        extContext.redirect("index_user.xhtml");
    }
    //---------------------------------------------------------------------

    public void requisitarP() {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();

        try {

            Login.PessoaEmail = utilizador.getEmail();
            Login.Pessoanome = utilizador.getNome_proprio();
            Login.Pessoadep = utilizador.getDepartamento();

            utilizador = new Utilizador();
            pesquisa = "";
            extContext.redirect("homeRequisicao_adm.xhtml");

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
}
