package util;

/**
 *20-09-12
 * @author hp
 */
public class Converte_password {
    
    String pass;

    public Converte_password(String pass) {
        this.pass = pass;
    }
    
    public String passconvertido (){
        String Rpass = "";
        
        for (int i=0; i<this.pass.length();i++ ){
            Rpass = Rpass + "*";
        }
        return Rpass;
    }
       
}
