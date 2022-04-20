import java.util.*;
import java.rmi.Naming;

// classe inicial do cliente
public class Cliente {
    public static void main(String [] args) {
        Utilizador user = new Utilizador();
        String topico;
        int opcao = -1, diaInicio, diaFim, mesInicio, mesFim, anoInicio, anoFim;
        boolean verificacao = false;
        ArrayList <Utilizador> utilizadores = new ArrayList <Utilizador> ();
		ArrayList <String> topicos = new ArrayList <String> ();
		ArrayList <Noticia> noticias = new ArrayList <Noticia> ();

		try {
			// ligar o cliente ao servidor
			Interface objetoServidor = (Interface) Naming.lookup("Servidor");
            // abrir os ficheiros de texto
            Funcoes.abrirFicheiros(utilizadores, topicos, noticias);
            // iniciar cli com o utilizador
            System.out.println("Bem-vindo ao seu servidor de notícias.\n\nDeseja autenticar-se?\n1 - Sim\n2 - Não");
            while (true) {
                opcao = Funcoes.lerInteiro();
                if (opcao == 1 || opcao == 2) {
                    // autenticar-se - por login ou registo
                    if (opcao == 1) {
                        System.out.println("1 - Login\n2 - Registo");
                        opcao = Funcoes.lerInteiro();
                        if (opcao == 1) {
                            // login
                        	// ESTA VERIFICAÇÃO NÃO ESTÁ A FUNCIONAR COMO DEVE SER
                        	while (verificacao == false) {
                                verificacao = Funcoes.verificarUtilizador(utilizadores, user);
                            }
                        }
                        if (opcao == 2) {
                            // registar
                            while (verificacao == false) {
                                verificacao = Funcoes.criarUtilizador(utilizadores, user);
                            }
                        }
                        // verificar o tipo de cliente
                        if (user.getTipo().equals("Produtor")) {
                        	// fazer as operações permitidas a um cliente Produtor
                        	// menu de operações para o cliente Produtor
                			do {
                				System.out.println("1 - Adicionar tópico\n2 - Consultar tópicos existentes\n3 - Inserir notícia\n4 - Consultar todas as notícias publicadas\n0 - Sair");
                				opcao = Funcoes.lerInteiro();
                				switch (opcao) {
                					case 1:
                						// adicionar um tópico
                						System.out.println("Introduza o tópico: ");
                						topico = Funcoes.lerString();
                						System.out.println(objetoServidor.AdicionarTopico(topico, topicos));
                						break;
                					case 2:
                						// consultar a lista de tópicos disponíveis
                						System.out.println("Lista de tópicos disponíveis:\n" + topicos);
                						break;
                					case 3:
                						// inserir uma notícia subordinada a um tópico
                						System.out.println("Introduza o tópico: ");
                						topico = Funcoes.lerString();
                						System.out.println(objetoServidor.InserirNoticia(topico, topicos, noticias));
                						break;
                					case 4:
                						// consultar todas as notícias publicadas até ao momento
                						objetoServidor.ConsultarNoticias(noticias);
                						break;
                					case 0:
                						// sair
                                    	System.out.println("Esperamos o seu regresso!");
                                        System.exit(0);
                					default:
                						System.out.println("Introduza um valor válido.");
                				}
                			} while (opcao != 0);
                        } else if (user.getTipo().equals("Consumidor")) {
                        	// fazer as operações permitidas a um cliente Consumidor
                        	// menu de operações para o cliente Consumidor
                        	do {
                				System.out.println("1 - Subscrever tópico\n2 - Consultar notícias de um dado tópico num intervalo de tempo\n3 - Consultar a última notícia de um dado tópico\n0 - Sair");
                				opcao = Funcoes.lerInteiro();
                				switch (opcao) {
                					case 1:
                						// subscrever um tópico
                						// SERÁ NECESSÁRIO COLOCAR UMA OPÇÃO PARA UM CONSUMIDOR PODER CONSULTAR OS TÓPICOS DISPONÍVEIS PARA SUBSCREVER? DEFENDO QUE SIM
                						System.out.println("Introduza o tópico: ");
                						topico = Funcoes.lerString();
                						objetoServidor.SubscreverTopico(topico);
                						break;
                					case 2:
                						// consultar notícias de um dado tópico num intervalo de tempo
                                        System.out.println("Introduza o tópico: ");
                                        topico = Funcoes.lerString();
                                        System.out.println("Introduza o dia da data inicial: ");
                                        diaInicio = Funcoes.lerInteiro();
                                        System.out.println("Introduza o mês da data inicial: ");
                                        mesInicio = Funcoes.lerInteiro();
                                        System.out.println("Introduza o ano da data inicial: ");
                                        anoInicio = Funcoes.lerInteiro();
                                        System.out.println("Introduza o dia da data final: ");
                                        diaFim = Funcoes.lerInteiro();
                                        System.out.println("Introduza o mês da data final: ");
                                        mesFim = Funcoes.lerInteiro();
                                        System.out.println("Introduza o ano da data final: ");
                                        anoFim = Funcoes.lerInteiro();
                                        objetoServidor.ConsultarNoticiasTopico(topico, diaInicio, diaFim, mesInicio, mesFim, anoInicio, anoFim);
                                        break;
                					case 3:
                						// consultar última notícia de um dado tópico
                						System.out.println("Introduza o tópico: ");
                						topico = Funcoes.lerString();
                						objetoServidor.ConsultarUltimaNoticia(topico, noticias);
                						break;
                					case 0:
                						// sair
                                    	System.out.println("Esperamos o seu regresso!");
                                        System.exit(0);
                					default:
                						System.out.println("Introduza um valor válido.");
                				}
                			} while (opcao != 0);
                        }
                    }
                    // prosseguir sem autenticar-se
                    if (opcao == 2) {
                        // menu de operações para o cliente não autenticado
                        do {
                            System.out.println("1 - Consultar notícias de um dado tópico num intervalo de tempo\n2 - Consultar a última notícia de um dado tópico\n0 - Sair");
                            opcao = Funcoes.lerInteiro();
                            switch (opcao) {
                                case 1:
                                	// consultar notícias de um dado tópico num intervalo de tempo
                                    System.out.println("Introduza o tópico: ");
                                    topico = Funcoes.lerString();
                                    System.out.println("Introduza o dia da data inicial: ");
                                    diaInicio = Funcoes.lerInteiro();
                                    System.out.println("Introduza o mês da data inicial: ");
                                    mesInicio = Funcoes.lerInteiro();
                                    System.out.println("Introduza o ano da data inicial: ");
                                    anoInicio = Funcoes.lerInteiro();
                                    System.out.println("Introduza o dia da data final: ");
                                    diaFim = Funcoes.lerInteiro();
                                    System.out.println("Introduza o mês da data final: ");
                                    mesFim = Funcoes.lerInteiro();
                                    System.out.println("Introduza o ano da data final: ");
                                    anoFim = Funcoes.lerInteiro();
                                    objetoServidor.ConsultarNoticiasTopico(topico, diaInicio, diaFim, mesInicio, mesFim, anoInicio, anoFim);
                                    break;
                                case 2:
                                	// consultar última notícia de um dado tópico
                                    System.out.println("Introduza o tópico: ");
                                    topico = Funcoes.lerString();
                                    objetoServidor.ConsultarUltimaNoticia(topico, noticias);
                                    break;
                                case 0:
                                	// sair
                                	System.out.println("Esperamos o seu regresso!");
                                    System.exit(0);
                                default:
                                    System.out.println("Introduza um valor válido.");
                            }
                        } while (opcao != 0);
                    }
                } else {
                    System.out.println("Introduza uma opção válida.");
                }
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// caso a ligação falhe, termina o programa
			System.exit(0);
		}
	}
}