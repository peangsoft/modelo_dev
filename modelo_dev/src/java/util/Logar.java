/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.net.URL;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static util.enviaEmail.email;

/**
 *
 * @author ambilabila
 */
public class Logar {

     boolean autenticacao = false;
     static PasswordAuthentication authentication;
            
    public static boolean autenticar(final String user, final String senha) {

        
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
       
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            
           
            
            try {
                
                Session session = Session.getDefaultInstance(props,
                    
                        new javax.mail.Authenticator() {
                        
                            protected PasswordAuthentication getPasswordAuthentication()
                            {
                                authentication = new PasswordAuthentication(user + "@gmail.com", senha);
                                return  authentication;
                            }
                        }
                
                );
                System.out.println(""+authentication);
                        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
