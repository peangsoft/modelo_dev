/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlo;

import SQL.Operacao;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Logar;

/**
 *
 * @author ambilabila
 */
public class LoginDAO {

    private static ResultSet rs = null;

    public static boolean validate(String user, String email) throws InstantiationException, SQLException, IllegalAccessException {

        try {
            //if (Logar.autenticar(user, email)) {
            if (true) {
                
                return true;
            }else
                 return false;
        } catch (Exception e) {
            System.out.println("Login error -->" + e.getMessage());
            return false;
        }
        
    }

}
