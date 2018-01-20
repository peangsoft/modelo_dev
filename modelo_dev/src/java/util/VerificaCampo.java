/*
 * Classe que verifica a existencia de um campo na BD
 */
package util;
import SQL.Operacao;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 24-09-2012
 * @author hp
 */
public class VerificaCampo {
  
  private String valor = null;
  private String campo = null;
  private String tabela = null;
  Operacao opr = null;
  ResultSet st = null;

    public VerificaCampo(String tabela, String campo, String valor){
        this.campo = campo;
        this.tabela = tabela;
        this.valor = valor;
        
        opr = new Operacao();
    }

public boolean ReportarOcorrencia () throws InstantiationException, SQLException, IllegalAccessException
{
    try {
            st = opr.consulta2(tabela, campo, campo+" = "+"'" + valor + "'");
            return st.next(); // se retornar false entao nao existe na bd
       }
        catch (Exception e) {
            System.out.print("Erro de consulta (class: VerificaCampo.java)");
       }
    return false;
}
    
}
