import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.ArrayList;

// classe do produtor
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
		int opcao1 = -1, opcao2 = -1;
		ArrayList <String> topicos = new ArrayList <String> ();
		ArrayList <String> noticias = new ArrayList <String> ();
		
		try {
			// ligar o cliente Produtor ao servidor
			Interface objetoServidor = (Interface) Naming.lookup("Servidor");
			// verificar se o cliente é PRODUTOR ou CONSUMIDOR
			System.out.println("1 - Produtor\n2 - Consumidor");
			opcao1 = lerInteiro();
			if (opcao1 == 1) {
				// menu de operações para o cliente PRODUTOR
				do {
					System.out.println("1 - Adicionar tópico\n2 - Consultar tópicos existentes\n3 - Inserir notícia\n4 - Consultar todas as notícias publicadas\n0 - Sair");
					opcao2 = lerInteiro();
					switch (opcao2) {
						case 1:
							System.out.println("Introduza o tópico: ");
							String topico1 = lerString();
							objetoServidor.AdicionarTopico(topico1);
							break;
						case 2:
							objetoServidor.ConsultarTopicos(topicos);
							break;
						case 3:
							System.out.println("Introduza o tópico: ");
							String topico2 = lerString();
							objetoServidor.InserirNoticia(topico2);
							break;
						case 4:
							objetoServidor.ConsultarNoticias(noticias);
							break;
						case 0:
							break;
						default:
							System.out.println("Introduza um valor válido.");
					}
				} while (opcao2 != 0);
			}
			if (opcao1 == 2) {
				// menu de operações para o cliente CONSUMIDOR
				do {
					System.out.println("1 - Subscrever tópico\n2 - Consultar notícias de um dado tópico num intervalo de tempo\n3 - Consultar a última notícia de um dado tópico\n0 - Sair");
					opcao2 = lerInteiro();
					switch (opcao2) {
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
				} while (opcao2 != 0);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// caso a ligação falhe, termina o programa
			System.exit(0);
		}
	}
}
