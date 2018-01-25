package util;
/**
 *  03.09.12 alterada em: 19/4/2016
 * @author Bilabila
 */
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class enviaEmail {
    
    static String email = "noreply";
    static String senha = "viwrorbi";
    
    
    public static void EnviarEmail(String assunto, String mensagem, String destinatario){
        
        Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication()
                             {
                                   return new PasswordAuthentication(email+"@mdsinsure.com", senha);
                             }
                        });

            /** Ativa Debug para sessão */
            session.setDebug(true);

            try {

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress(email+"@mdsinsure.com")); //Remetente

                  Address[] toUser = InternetAddress //Destinatário(s)
                             .parse(destinatario);  

                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject(assunto);//Assunto
                  message.setText(mensagem);
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);

                  System.out.println("Msn enviado!!!");

             } catch (MessagingException e) {
                 // throw new RuntimeException(e);
                  System.out.println("Erro de envio!!!\n"+e);
            }
    }
    
   // public static void main(String[] args) {
     //   enviaEmail.EnviarEmail("Teste de mensagem", "Ola tudo bem bila 2", "augusto.bilabila@mdsinsure.com,bilabila120@hotmail.com");
    //}
}
