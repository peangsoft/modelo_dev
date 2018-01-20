
import SQL.Operacao;
import SQL.conectarBD;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ambilabila
 */
public class AbrirImagem extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Operacao op = new Operacao();
        Blob mapBlob = null;

        try {

            ResultSet res = op.consulta2("tbproduto", "imagem", "id = '" + request.getParameter("id") + "'");

            if (res.next()) {
                mapBlob = res.getBlob("imagem");
            }
            if (mapBlob == null) {
                res = op.consulta2("tbproduto_aux", "imagem", "id = '1'");

                if (res.next()) {
                    mapBlob = res.getBlob("imagem");
                }
            }

            res.getStatement().close();
            byte[] arquivo = mapBlob.getBytes(1, (new Long(mapBlob.length())).intValue());

            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/png");

            ServletOutputStream out = response.getOutputStream();
            out.write(arquivo);
            out.flush();
            out.close();

        } catch (java.lang.NullPointerException ex) {
           
        } catch (InstantiationException ex) {
            Logger.getLogger(AbrirImagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AbrirImagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbrirImagem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
