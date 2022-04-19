import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.ArrayList;

// classe do consumidor
public class Consumidor {
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
		int opcao;
		ArrayList <String> topicos = new ArrayList <String> ();
		ArrayList <String> noticias = new ArrayList <String> ();
		
		try {
			// ligar o cliente Produtor ao servidor
			InterfaceConsumidor objetoServidor = (InterfaceConsumidor) Naming.lookup("Servidor");
			// menu de operações para o cliente CONSUMIDOR
			do {
				System.out.println("1 - Subscrever tópico\n2 - Consultar notícias de um dado tópico num intervalo de tempo\n3 - Consultar a última notícia de um dado tópico\n0 - Sair");
				opcao = lerInteiro();
				switch (opcao) {
					case 1:
						System.out.println("Introduza o tópico: ");
						String topico1 = lerString();
						objetoServidor.SubscreverTopico(topico1);
						break;
					case 2:
						System.out.println("Introduza o tópico: ");
						String topico2 = lerString();
						System.out.println("Introduza o dia da data inicial: ");
						int diaInicio = lerInteiro();
						System.out.println("Introduza o mês da data inicial: ");
						int mesInicio = lerInteiro();
						System.out.println("Introduza o dia da data final: ");
						int diaFim = lerInteiro();
						System.out.println("Introduza o mês da data final: ");
						int mesFim = lerInteiro();
						objetoServidor.ConsultarNoticiasTopico(topico2, diaInicio, diaFim, mesInicio, mesFim);
						break;
					case 3:
						System.out.println("Introduza o tópico: ");
						String topico3 = lerString();
						objetoServidor.ConsultarUltimaNoticia(topico3);
						break;
					case 0:
						break;
					default:
						System.out.println("Introduza um valor válido.");
				}
			} while (opcao != 0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// caso a ligação falhe, termina o programa
			System.exit(0);
		}
	}
}
