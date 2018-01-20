package SQL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import javax.inject.Inject;

/**
 *
 * @author hp 12-08-12
 */
public class Operacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    conectarBD conecta;

    PreparedStatement Statement;

    public Operacao() {

    }

    //metodo que permite inserir
    public boolean inserir(String tabela, String campos, String valores) throws InstantiationException, SQLException, IllegalAccessException {
        String SQL = "insert into " + tabela + "( " + campos + ") values (" + valores + ")";
        //System.out.println(SQL);
        try {
            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);
            Statement.executeUpdate();

        } catch (Exception ex) {
            System.out.println(" Erro na insercao " + ex.getMessage());
            return false;
        }
         
        return true;
    }

    /*
    INICIO:
    LIDANDO COM FICHEIROS
     */
    //------------------------------------------------------------------------
    // Gravando Ficheiros na DB
    // 3º(2) Campo eh sempre o ficheiro.
    public boolean insertFile(String tabela, String campos, String valores, byte[] bytes) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException {

        try {
            String SQL = "insert into " + tabela + "( " + campos + ") values (" + valores + ")";
           // System.out.println(SQL);
            //PreparedStatement ps = c.prepareStatement("INSERT INTO arquivo( id, nome, arquivo ) VALUES ( nextval('seq_arquivo'), ?, ? )");

            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);

            //converte o objeto file em array de bytes
           /* InputStream is = new FileInputStream(f);
            byte[] bytes = new byte[1024];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = f.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            */
            Statement.setBytes(1, bytes);
            Statement.execute();
            Statement.close();

            conectarBD.fechar();

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //---------------------------------------------------------------------------
    // BUSCAR O FICHEIRO E RETORNAR EM BYTES
    /*
        
     */

    public File getFile(String tabela, String campos, String condicao) throws InstantiationException, IllegalAccessException {
        //converte o objeto file em array de bytes
        File f = null;
        try {
            String SQL = "select " + campos + " from " + tabela + " where " + condicao + "";
            //System.out.println(SQL);

            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);
            //  PreparedStatement ps = c.prepareStatement("SELECT id, nome, arquivo FROM arquivo WHERE id = ?");
            //  Statement.setString(1, id);
            ResultSet rs = Statement.executeQuery();
            if (rs.next()) {
                byte[] bytes = rs.getBytes("documento");
                String nome = rs.getString("nome");

                //converte o array de bytes em file
                f = new File("C:/Ficheiro/Backup/" + nome);
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(bytes);
                fos.close();
            }
            rs.close();
            Statement.close();

            conectarBD.fechar();

            return f;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
// ------------ FIMMMMMMMMM DOS ARQUIVOS --------------------------------------
    //metodo que permite deletar -----------------------------------------------  

    public boolean eliminar(String tabela, String condicao) throws InstantiationException, SQLException, IllegalAccessException {
        String SQL = "delete from " + tabela + " where " + condicao;
        //System.out.println(SQL);
        try {
            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);
            Statement.execute();
        } catch (Exception ex) {
            System.out.println(" Erro quando tentava eliminar ");
            return false;
        }
        
        return true;
    }

    //metodo que permite consultas --------------------------------------------   
    public ResultSet consulta0(String sql) throws InstantiationException, SQLException, IllegalAccessException {
        ResultSet rs = null;
        String SQL = sql;
       // System.out.println(SQL);
        try {
            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);
            rs = Statement.executeQuery();

        } catch (Exception ex) {
            System.out.println(" Erro ao consultar \n " + ex.getMessage());
        }
         
        return rs;

    }

    public ResultSet consulta1(String tabela, String campos) throws InstantiationException, SQLException, IllegalAccessException {
        ResultSet rs = null;
        String SQL = "select " + campos + " from " + tabela;
        System.out.println(SQL);
        try {
            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);
            rs = Statement.executeQuery();

        } catch (Exception ex) {
            System.out.println(" Erro ao consultar \n " + ex.getMessage());
        }
        
        return rs;

    }

    public ResultSet consulta2(String tabela, String campos, String condicao) throws InstantiationException, SQLException, IllegalAccessException {
        ResultSet RS = null;
        String SQL = "select " + campos + " from " + tabela + " where " + condicao;
        System.out.println(SQL);
        try {
            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);
            RS = Statement.executeQuery();
        } catch (Exception ex) {
            System.out.println(" Erro ao consultar \n " + ex.getMessage());
        }
         
        return RS;
    }

    public ResultSet consulta3(String tabela, String campos, String campoOrdem) throws InstantiationException, SQLException, IllegalAccessException {
        ResultSet RS = null;
        String SQL = "select " + campos + " from " + tabela + " ORDER BY " + campoOrdem;
        System.out.println(SQL);
        try {
            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);
            RS = Statement.executeQuery();
        } catch (Exception ex) {
            System.out.println(" Erro ao consultar \n " + ex.getMessage());
        }

        return RS;
    }
    //metodo que permite actualizacao --------------------------------------------   

    public boolean atualizar1(String tabela, String camposEvalores) throws InstantiationException, SQLException, IllegalAccessException {

        String SQL = "update " + tabela + " set " + camposEvalores;

        try {
            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);
            Statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(" Erro ao atualizar ");
            return false;
        }

        return true;
    }

    public boolean atualizar2(String tabela, String camposEvalores, String condicao) throws InstantiationException, SQLException, IllegalAccessException {

        String SQL = "update " + tabela + " set " + camposEvalores + " where " + condicao;
        System.out.println(SQL);
        try {
            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);
            Statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(" Erro ao atualizar 2 ");
            return false;
        }

        return true;
    }

    //Retorna o nome dada o codigo
    public static String RetornaDescricao(String tabela, String condicao) throws InstantiationException, SQLException, IllegalAccessException {
        ResultSet RS = null;
        PreparedStatement Statement;
        conectarBD conecta = new conectarBD();
        String SQL = null;
        if (condicao == null) {
            return "Nemhum";
        } else {
            SQL = "select nome from " + tabela + " where cod = '" + condicao + "'";
        }

        //System.out.println(SQL);
        try {
            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);
            RS = Statement.executeQuery();
            RS.next();
        } catch (Exception ex) {
            System.out.println(" Erro ao Consultar " + ex.toString());
            return "";
        }

        return RS.getString("nome");
    }

    //Retorna o nome dada o codigo
    public static int RetornaNumRegisto(String tabela) throws InstantiationException, SQLException, IllegalAccessException {
        ResultSet RS = null;
        PreparedStatement Statement;
        conectarBD conecta = new conectarBD();
        String SQL = null;
        if (tabela == null || tabela.equals("")) {
            return -1;
        } else {
            SQL = "select count(cod) from " + tabela + "";
        }

        //System.out.println(SQL);
        try {
            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);
            RS = Statement.executeQuery();
            RS.next();
        } catch (Exception ex) {
            System.out.println(" Erro ao Consultar " + ex.toString());
            return -2;
        }

        return RS.getInt("count(cod)");
    }
    
     public void procedimento(String nome) throws InstantiationException, SQLException, IllegalAccessException {
        ResultSet rs = null;
        String SQL = "CALL " + nome;
        //System.out.println(SQL);
        try {
            Statement = (conecta.ConexaoMySql()).prepareStatement(SQL);
            Statement.execute();

        } catch (Exception ex) {
            System.out.println("Erro ao EXECUTAR o procedimento:\n"+ex.getMessage());
        }finally{
            conectarBD.fechar();
        }
        
    }

}
