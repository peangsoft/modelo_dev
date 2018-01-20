package Controlo;

import Modelo.Utilizador;
import SQL.Operacao;
import SQL.conectarBD;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;
import util.Calendario;
import util.Converte_password;

/**
 *
 * @author ambilabila
 */
public class UtilizadorDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Utilizador utilizador;
    private List<Utilizador> utilizadores;
    private List<String> departamentos = new ArrayList<String>();

    private String departamento;
    private String id_departamento;

    private String mensagem;
    private boolean skip;

    @Inject
    private Operacao operacao;

    private ResultSet rs;

    public UtilizadorDAO() {
        operacao = new Operacao();

    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
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

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    // Actions
    // Salvar um novo utilizador
    public void salvar(Utilizador utilizador, String op) throws IOException, InstantiationException, SQLException, IllegalAccessException {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();

        boolean verificar;

        try {

            verificar = operacao.inserir("tbutilizador",
                    "nome,"
                    + "email,"
                    + "local,"
                    + "nivel,"
                    + "departamento",
                    "'" + utilizador.getNome_proprio() + "','"
                    + "" + utilizador.getEmail() + "','"
                    + "" + utilizador.getLocal() + "','"
                    + "Utente',"
                    + "'" + listarCodDepartamento(utilizador.getDepartamento()) + "'");

            if (op.equals("U")) {

                if (verificar == true) {

                    mensagem = "Perfil guardado, poderá fazer suas aquisições de forma tranquila";
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(mensagem));

                    extContext.redirect("index_user.xhtml");

                } else {

                    mensagem = "Os dados não foram guardados, tente novamente ou contacte o administrador!";
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(mensagem));
                }

            } else if (verificar == true) {

                mensagem = "Perfil guardado, poderá fazer suas aquisições de forma tranquila";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(mensagem));

                extContext.redirect("homeRequisicao_adm.xhtml");

            } else {
                System.out.print("Não Guardado");
                mensagem = "Os dados não foram guardados, tente novamente ou contacte o administrador!";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(mensagem));
            }

        } catch (InstantiationException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conectarBD.fechar();
    }

    public void salvar(Utilizador utilizador) throws IOException, InstantiationException, SQLException, IllegalAccessException {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();

        boolean verificar;

        try {

            verificar = operacao.inserir("tbutilizador",
                    "nome,"
                    + "email,"
                    + "local,"
                    + "nivel,"
                    + "departamento",
                    "'" + utilizador.getNome_proprio() + "','"
                    + "" + utilizador.getEmail() + "','"
                    + "" + utilizador.getLocal() + "','"
                    + "Utente',"
                    + "'" + listarCodDepartamento(utilizador.getDepartamento()) + "'");

            if (verificar == true) {

                mensagem = "Perfil guardado, já poderá fazer suas aquisições.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(mensagem));

                extContext.redirect("index_user.xhtml");

            } else {

                mensagem = "Os dados não foram guardados, tente novamente ou contacte o administrador!";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(mensagem));
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conectarBD.fechar();
    }

    public void salvar(Utilizador utilizador, String user, String p) throws IOException, InstantiationException, SQLException, IllegalAccessException {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();

        boolean verificar;

        try {

            verificar = operacao.inserir("tbutilizador",
                    "nome,"
                    + "email,"
                    + "local,"
                    + "nivel,"
                    + "departamento",
                    "'" + utilizador.getNome_proprio() + "','"
                    + "" + utilizador.getEmail() + "','"
                    + "" + utilizador.getLocal() + "','"
                    + "Utente',"
                    + "'" + listarCodDepartamento(utilizador.getDepartamento()) + "'");

            if (verificar == true) {

                mensagem = "Perfil guardado, já poderá fazer suas aquisições.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(mensagem));

                if (user.equals("P")) {
                    extContext.redirect("index_user.xhtml");
                } else {
                    extContext.redirect("index_adm.xhtml");
                }

            } else {

                mensagem = "Os dados não foram guardados, tente novamente ou contacte o administrador!";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(mensagem));
            }

        } catch (InstantiationException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conectarBD.fechar();
    }

    public void alterar_local(Utilizador utilizador) throws InstantiationException, SQLException, IllegalAccessException {
        try {

            operacao.atualizar2("tbutilizador",
                    "local = '" + utilizador.getLocal() + "'",
                    "email = '" + utilizador.getEmail() + "'"
            );
            
            mensagem = "O local foi guardado";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(mensagem));

        } catch (InstantiationException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            
            mensagem = "Houve erro ao tentar guardar o local, tente novamente ou contacte o administrador";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(mensagem));
        }
    }

    public void alterar(Utilizador utilizador) throws InstantiationException, SQLException, IllegalAccessException {

        boolean verificar = false;

        try {

            verificar = operacao.atualizar2("tbutilizador",
                    "departamento = '" + listarCodDepartamento(utilizador.getDepartamento()) + "'",
                    "email = '" + utilizador.getEmail() + "'"
            );

        } catch (InstantiationException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UtilizadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (verificar == true) {
            // System.out.println("Perfil Alterado");
            mensagem = "Os dados foram Alterado\nUtilizador: ";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(mensagem));
        } else {
            // System.out.print("Não Alterado");
            mensagem = "Os dados não foram Alterados";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(mensagem));
        }
        conectarBD.fechar();
    }

    public void eliminar(Utilizador utilizador) {

    }

    public void ativar(Utilizador utilizador) {

    }

    public void Dar_Acesso(Utilizador utilizador) {

    }

    public List<Utilizador> listar() throws InstantiationException, SQLException, IllegalAccessException {

        rs = operacao.consulta1("tbutilizador", "*");

        while (rs.next()) {

            utilizador = new Utilizador();

            utilizador.setNome_proprio(rs.getString("nome"));
            utilizador.setEmail(rs.getString("email"));
            utilizador.setId(rs.getString("id"));
            utilizador.setLocal(listar_umLocal(rs.getString("local")));
            utilizador.setNivel(rs.getString("nivel"));
            utilizador.setDepartamento(listarUmDepartamentos(rs.getString("departamento")));

            utilizadores.add(utilizador);

        }
        conectarBD.fechar();
        return utilizadores;

    }

    public Utilizador ListaUmUtilizador(String user) throws InstantiationException, SQLException, IllegalAccessException {

        utilizador = new Utilizador();
        rs = operacao.consulta2("tbutilizador", "*", "email = '" + user + "'");

        if (rs.next()) {

            utilizador.setNome_proprio(rs.getString("nome"));
            utilizador.setEmail(rs.getString("email"));
            utilizador.setId(rs.getString("id"));
            utilizador.setNivel(rs.getString("nivel"));
            utilizador.setFoto(rs.getString("foto"));
            utilizador.setLocal(listar_umLocal(rs.getString("local")));
            utilizador.setDepartamento(listarUmDepartamentos(rs.getString("departamento")));

        }
        conectarBD.fechar();
        return utilizador;

    }

    public Utilizador ListaUmaPessoa(String pesquisar) throws InstantiationException, SQLException, IllegalAccessException {
        int verificador = 0;

        rs = operacao.consulta2("tbutilizador", "*", "email LIKE '%" + pesquisar + "%' or nome LIKE '%" + pesquisar + "%'");

        if (rs.next()) {

            utilizador.setNome_proprio(rs.getString("nome"));
            utilizador.setEmail(rs.getString("email"));
            utilizador.setId(rs.getString("id"));
            utilizador.setNivel(rs.getString("nivel"));
            utilizador.setFoto(rs.getString("foto"));
            utilizador.setLocal(listar_umLocal(rs.getString("local")));
            utilizador.setDepartamento(listarUmDepartamentos(rs.getString("departamento")));

            verificador = 1;
        }
        if (verificador == 0) {
            rs = operacao.consulta2("tbpessoas_mds", "*", "email LIKE '%" + pesquisar + "%' or nome LIKE '%" + pesquisar + "%'or nome_completo LIKE '%" + pesquisar + "%'");
            if (rs.next()) {
                utilizador.setNome_proprio(rs.getString("nome_completo"));
                utilizador.setEmail(rs.getString("email"));
                verificador = 1;
            }
        }
        if (verificador == 0) {
            mensagem = "A pessoa que procura não está na base de dados, registe abaixo, por favor!";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", mensagem));
        }
        conectarBD.fechar();
        return utilizador;

    }

    public List<Utilizador> ListaPessoas(String pesquisar) throws InstantiationException, SQLException, IllegalAccessException {
        int verificador = 0;
        utilizadores = new ArrayList<Utilizador>();

        rs = operacao.consulta2("tbutilizador", "*", "email LIKE '%" + pesquisar + "%' or nome LIKE '%" + pesquisar + "%'");

        while (rs.next()) {
            utilizador = new Utilizador();

            utilizador.setNome_proprio(rs.getString("nome"));
            utilizador.setEmail(rs.getString("email"));
            utilizador.setId(rs.getString("id"));
            utilizador.setNivel(rs.getString("nivel"));
            utilizador.setLocal(listar_umLocal(rs.getString("local")));
            utilizador.setFoto(rs.getString("foto"));
            utilizador.setDepartamento(listarUmDepartamentos(rs.getString("departamento")));

            utilizadores.add(utilizador);

        }

        rs = operacao.consulta2("tbpessoas_mds", "*", "email LIKE '%" + pesquisar + "%' or nome LIKE '%" + pesquisar + "%'or nome_completo LIKE '%" + pesquisar + "%'");

        while (rs.next()) {
            utilizador = new Utilizador();

            utilizador.setNome_proprio(rs.getString("nome_completo"));
            utilizador.setEmail(rs.getString("email"));

            utilizadores.add(utilizador);

            verificador = 1;
        }

        if (verificador == 0) {
            mensagem = "A pessoa que procura não está na base de dados, registe abaixo, por favor!";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", mensagem));
        }

        conectarBD.fechar();
        return utilizadores;

    }

    public boolean ConfirmaUmUtilizador(String user) throws InstantiationException, SQLException, IllegalAccessException {

        boolean confirma = false;

        ResultSet rs = operacao.consulta2("tbutilizador", "*", "email = '" + user + "'");
        utilizador = new Utilizador();

        if (rs.next()) {

            utilizador.setNome_proprio(rs.getString("nome"));
            utilizador.setEmail(rs.getString("email"));
            utilizador.setId(rs.getString("id"));
            utilizador.setNivel(rs.getString("nivel"));
            utilizador.setFoto(rs.getString("foto"));
            utilizador.setDepartamento(listarUmDepartamentos(rs.getString("departamento")));
            utilizador.setLocal(listar_umLocal(rs.getString("local")));

            confirma = true;

        }
        conectarBD.fechar();
        return confirma;

    }

    public void atualizar_foto(String foto, String email) throws InstantiationException, SQLException, IllegalAccessException {
        if (foto != null || email != null) {
            String sql = "atualizar_foto_utilizador('" + foto + "','" + email + "')";
            operacao.procedimento(sql);
        }

    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    //000000000000000000000000|DEPARTAMENTO|000000000000000000000000000000000
    public List<String> listarDepartamentos() throws InstantiationException, SQLException, IllegalAccessException {

        rs = operacao.consulta1("tbdepartamento", "*");

        while (rs.next()) {

            departamentos.add(rs.getString("nome"));

        }
        return departamentos;
    }

    public String listarUmDepartamentos(String id) throws InstantiationException, SQLException, IllegalAccessException {

        ResultSet rs = operacao.consulta2("tbdepartamento", "*", "id='" + id + "'");

        while (rs.next()) {

            departamento = rs.getString("nome");

        }

        return departamento;
    }

    public String listarCodDepartamento(String nome) throws InstantiationException, SQLException, IllegalAccessException {

        rs = operacao.consulta2("tbdepartamento", "*", "nome='" + nome + "'");

        while (rs.next()) {

            id_departamento = rs.getString("id");

        }
        conectarBD.fechar();
        return id_departamento;
    }

    //000000000000000000000000|END DEPARTAMENTO|00000000000000000000000000000
    //////////////////////// REPORTS USERS // 00000000000000000000000000000000
    public int quantidade_pedidos(String email) throws InstantiationException, SQLException, IllegalAccessException {

        rs = operacao.consulta2("tbrequisicao", "count(id) AS qtd", "requerente='" + email + "'");
        rs.next();
        int qtd = rs.getInt("qtd");

        conectarBD.fechar();
        return qtd;
    }

    public int quantidade_respostas(String email) throws InstantiationException, SQLException, IllegalAccessException {

        rs = operacao.consulta2(" tbentrega as E,tbrequisicao as R", "count(E.id) AS qtd", "R.requerente='" + email + "' AND R.id = E.id");
        rs.next();
        int qtd = rs.getInt("qtd");

        conectarBD.fechar();
        return qtd;
    }

    public int quantidade_pendentes(String email) throws InstantiationException, SQLException, IllegalAccessException {

        rs = operacao.consulta2("tbrequisicao", "count(id) AS qtd", "requerente='" + email + "' AND estado = 'Pendente'");
        rs.next();
        int qtd = rs.getInt("qtd");

        conectarBD.fechar();
        return qtd;
    }

    public int quantidade_sugestao(String email) throws InstantiationException, SQLException, IllegalAccessException {

        rs = operacao.consulta2("tbsugestao", "count(id) AS qtd", "requerente='" + email + "'");
        rs.next();
        int qtd = rs.getInt("qtd");

        conectarBD.fechar();
        return qtd;
    }

    // Listar pessoas 
    public List<String> listar_pessoa() throws InstantiationException, SQLException, IllegalAccessException {
        List<String> lista = new ArrayList<>();

        rs = operacao.consulta1("tbutilizador", "*");

        while (rs.next()) {
            lista.add(rs.getString("email"));
        }
        conectarBD.fechar();
        return lista;

    }

    public String listar_umLocal(String id) throws InstantiationException, SQLException, IllegalAccessException {
        String local = "";

        ResultSet rs = operacao.consulta2("tblocal", "local", "id='" + id + "'");
        
        while (rs.next()) {
            local = rs.getString("local");
        }

        return local;

    }
// tratar do local
    public List<String> listar_Local() throws InstantiationException, SQLException, IllegalAccessException {
        List<String> locais = new ArrayList<>();

        rs = operacao.consulta1("tblocal", "local");

        while (rs.next()) {
            locais.add(rs.getString("local"));
        }
        conectarBD.fechar();
        return locais;

    }
    
    public String listar_Local_id(String local) throws InstantiationException, SQLException, IllegalAccessException {
        String codigo = "";

        rs = operacao.consulta2("tblocal", "id", "local='"+local+"'");

        while (rs.next()) {
            codigo = rs.getString("id");
        }
        conectarBD.fechar();
        return codigo;

    }
    
    public String listar_Local_nome(String id) throws InstantiationException, SQLException, IllegalAccessException {
        String local = "";

        rs = operacao.consulta2("tblocal", "local", "id='"+id+"'");

        while (rs.next()) {
            local = rs.getString("local");
        }
        conectarBD.fechar();
        return local;

    }

}
