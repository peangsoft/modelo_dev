
package SQL;
/**
 *
 * @author hp
 */
import java.io.Serializable;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;

public class conectarBD implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static String DRIVER = "com.mysql.jdbc.Driver";
   
    //Para Local
  /*  private static String USERNAME = "root";
    
    private static String PASSWORD = "Mds@1.mds@mdS";
    private static String BD = "jdbc:mysql://localhost:3306/almoxarifadobd";  */
    
      //Para Local
    private static String USERNAME = "appuser";
    
    private static String PASSWORD = "mds.123";
    private static String BD = "jdbc:mysql://10.197.83.140:3306/almoxarifadobd";
    
    private static Driver MM_driver; static Connection conn;
    
    @Produces
    public static Connection ConexaoMySql() throws InstantiationException, SQLException, IllegalAccessException{
        try {
            MM_driver = (Driver)Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(BD,USERNAME,PASSWORD);
            System.out.println(" Conexao aberta ");
            
            
        } catch (ClassNotFoundException ex) {
            System.out.println(" Erro na conexao ");
           
        }    
          return conn;
    }
   
    @Produces   
    public static void fechar() throws InstantiationException, SQLException, IllegalAccessException{
        try {
            
            conn.close();
            System.out.println(" Conexao fechada ");
            
            
        } catch (SQLException e) {
            System.out.println(" Erro na conexao, ao tentar a base de dados ");
           
        }    
    }

    public static void main(String[] args) {
        conectarBD c = new conectarBD();
        try {
            c.ConexaoMySql();
        } catch (InstantiationException ex) {
            Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conectarBD.fechar();
            } catch (InstantiationException ex) {
                Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(conectarBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}