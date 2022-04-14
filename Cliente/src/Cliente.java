import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;

// classe inicial do cliente
public class Cliente {
	// ler uma String a partir do teclado
    public static String lerString () {
        String s = "";
        try {
            BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
            s = in.readLine ();
        } catch (IOException e) {
            System.out.println (e.getMessage());
        }
        return s;
    }
    
    // ler um inteiro a partir de uma String
    public static int lerInteiro () {
        while (true) {
            try {
                return Integer.valueOf (lerString().trim()).intValue();
            } catch (Exception e) {
                System.out.println (e.getMessage());
            }
        }
    }
    
	public static void main(String [] args) {
		try {
			// ligar o cliente Produtor ao servidor
			InterfaceProdutor objetoServidor = (InterfaceProdutor) Naming.lookup("Servidor");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// caso a ligação falhe, termina o programa
			System.exit(0);
		}
	}
}
