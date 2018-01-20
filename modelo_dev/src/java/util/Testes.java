
package util;

import Controlo.LoginDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ambilabila
 */
public class Testes {
    public static void main(String[] args) throws InstantiationException, SQLException, IllegalAccessException {
        List<Integer> inteiros = new ArrayList<Integer>();
		inteiros.add(10);
		inteiros.add(1);
		inteiros.add(10);
		inteiros.add(2);
		inteiros.add(10);
		inteiros.add(7);
		inteiros.add(10);
		inteiros.add(5);
		int frequency = Collections.frequency(inteiros, 10);
		System.out.println(frequency);
        
    }
}
