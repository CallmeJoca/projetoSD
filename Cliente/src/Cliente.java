import java.util.*;
import java.rmi.Naming;

// classe inicial do cliente
public class Cliente {
    public static void main(String [] args) {
        int opcao = -1, diaPublicacao, mesPublicacao, anoPublicacao, diaInicio, diaFim, mesInicio, mesFim, anoInicio, anoFim;
        boolean verificacao = false;
        char [] textoAuxiliar;
        char [] noticia = new char [180];
        Utilizador user = new Utilizador();
        Noticia ultimaNoticia = new Noticia();
        String topico, texto;
        Calendar inicio = Calendar.getInstance(), fim = Calendar.getInstance(), publicacao = Calendar.getInstance();
        ArrayList <Utilizador> utilizadores = new ArrayList <Utilizador> ();
		ArrayList <String> topicos = new ArrayList <String> ();
		ArrayList <Noticia> noticias = new ArrayList <Noticia> ();
		ArrayList <Noticia> auxiliar = new ArrayList <Noticia> ();
		ArrayList <String> subscricoes = new ArrayList <String> ();
		ArrayList <Noticia> noticiasTempo = new ArrayList <Noticia> ();

		try {
			// ligar o cliente ao servidor
			Interface objetoServidor = (Interface) Naming.lookup("Servidor");
            // abrir os ficheiros de texto
            utilizadores = Funcoes.abrirFicheiroUtilizadores(utilizadores);
            topicos = Funcoes.abrirFicheiroTopicos(topicos);
            noticias = Funcoes.abrirFicheiroNoticias(noticias);
            // iniciar cli com o utilizador
            System.out.println("Bem-vindo ao seu servidor de not�cias.\n\nDeseja autenticar-se?\n1 - Sim\n2 - N�o");
            while (true) {
                opcao = Funcoes.lerInteiro();
                if (opcao == 1 || opcao == 2) {
                    // autenticar-se - por login ou registo
                    if (opcao == 1) {
                        System.out.println("1 - Login\n2 - Registo");
                        opcao = Funcoes.lerInteiro();
                        if (opcao == 1) {
                            // login
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
                        	// fazer as opera��es permitidas a um cliente Produtor
                        	// menu de opera��es para o cliente Produtor
                			do {
                				System.out.println("1 - Adicionar t�pico\n2 - Consultar t�picos existentes\n3 - Inserir not�cia\n4 - Consultar todas as not�cias publicadas\n0 - Sair");
                				opcao = Funcoes.lerInteiro();
                				switch (opcao) {
                					case 1:
                						// adicionar um t�pico
                						System.out.println("Introduza o t�pico: ");
                						topico = Funcoes.lerString();
                						topicos = objetoServidor.AdicionarTopico(topico, topicos);
                                        Funcoes.escreverFicheiroTopicos(topicos);
                                        break;
                					case 2:
                						// consultar a lista de t�picos dispon�veis
                						System.out.println("Lista de t�picos dispon�veis:\n" + topicos);
                						break;
                					case 3:
                						// inserir uma not�cia subordinada a um t�pico
                						// t�pico
                						System.out.println("Introduza o t�pico: ");
                						topico = Funcoes.lerString();
                						// dia da publica��o
                						System.out.println("Introduza o dia de publica��o: ");
                						diaPublicacao = Funcoes.lerInteiro();
                						// m�s da publica��o
                						System.out.println("Introduza o m�s de publica��o: ");
                						mesPublicacao = Funcoes.lerInteiro();
                						// ano da publica��o
                						System.out.println("Introduza o ano de publica��o: ");
                						anoPublicacao = Funcoes.lerInteiro();
                						// introduzir a data num objeto do tipo Calendar
                				        publicacao.set(anoPublicacao, mesPublicacao, diaPublicacao);
                						// corpo da not�cia
                						System.out.println("Introduza o texto da not�cia: ");
                						// ler uma String com o corpo da not�cia
                						texto = Funcoes.lerString();
                						// transformar a String para um array de carateres auxiliar
                				        textoAuxiliar = texto.toCharArray();
                				        // passar os carateres para o array de carateres final, com limite de 180 posi��es (carateres)
                				        for (int i = 0; i < 180; i++) {
                				            noticia[i] = textoAuxiliar[i];
                				        }
                						noticias = objetoServidor.InserirNoticia(topico, user.getNome(), publicacao, noticia, topicos, noticias);
                                        Funcoes.escreverFicheiroNoticias(noticias);
                                        break;
                					case 4:
                						// consultar todas as not�cias publicadas at� ao momento
                						auxiliar = objetoServidor.ConsultarNoticias(user.getNome(), noticias);
                						// se o ArrayList auxiliar estiver vazio, n�o h� not�cias publicadas
                						if (auxiliar == null) {
                							System.out.println("O produtor ainda n�o tem not�cias publicadas.");
                						// se o ArrayList auxiliar tiver not�cias, estas s�o apresentadas ao utilizador
                						} else {
                							System.out.println("Not�cias publicadas:\n" + auxiliar);
                						}
                						break;
                					case 0:
                						// sair
                                    	System.out.println("Esperamos o seu regresso!");
                                        System.exit(0);
                					default:
                						System.out.println("Introduza um valor v�lido.");
                				}
                			} while (opcao != 0);
                        } else if (user.getTipo().equals("Consumidor")) {
                        	// fazer as opera��es permitidas a um cliente Consumidor
                        	// menu de opera��es para o cliente Consumidor
                        	do {
                				System.out.println("1 - Subscrever t�pico\n2 - Consultar not�cias de um dado t�pico num dado intervalo de tempo\n3 - Consultar a �ltima not�cia de um dado t�pico\n0 - Sair");
                				opcao = Funcoes.lerInteiro();
                				switch (opcao) {
                					case 1:
                						// subscrever um t�pico
                						// SER� NECESS�RIO COLOCAR UMA OP��O PARA UM CONSUMIDOR PODER CONSULTAR OS T�PICOS DISPON�VEIS PARA SUBSCREVER? DEFENDO QUE SIM
                						System.out.println("Introduza o t�pico: ");
                						topico = Funcoes.lerString();
                						subscricoes = objetoServidor.SubscreverTopico(topico, subscricoes);
                						break;
                					case 2:
                						// consultar not�cias de um dado t�pico num intervalo de tempo
                                        System.out.println("Introduza o t�pico: ");
                                        topico = Funcoes.lerString();
                                        System.out.println("Introduza o dia da data inicial: ");
                                        diaInicio = Funcoes.lerInteiro();
                                        System.out.println("Introduza o m�s da data inicial: ");
                                        mesInicio = Funcoes.lerInteiro();
                                        System.out.println("Introduza o ano da data inicial: ");
                                        anoInicio = Funcoes.lerInteiro();
                                        System.out.println("Introduza o dia da data final: ");
                                        diaFim = Funcoes.lerInteiro();
                                        System.out.println("Introduza o m�s da data final: ");
                                        mesFim = Funcoes.lerInteiro();
                                        System.out.println("Introduza o ano da data final: ");
                                        anoFim = Funcoes.lerInteiro();
                                        // adicionar as datas a objetos do tipo Calendar
                                        inicio.set(anoInicio, mesInicio, diaInicio);
                                        fim.set(anoFim, mesFim, diaFim);
                                        noticiasTempo = objetoServidor.ConsultarNoticiasTopico(topico, inicio, fim, noticias);
                                        System.out.println(noticiasTempo);
                                        break;
                					case 3:
                						// consultar �ltima not�cia de um dado t�pico
                						System.out.println("Introduza o t�pico: ");
                						topico = Funcoes.lerString();
                						ultimaNoticia = objetoServidor.ConsultarUltimaNoticia(topico, noticias);
                                        // se o t�pico n�o estiver preenchido, ent�o o objeto est� inicializado a null
                						if (ultimaNoticia.getTopico().equals("")) {
                							System.out.println("Ainda n�o h� not�cias subordinadas a esse t�pico.");
                						} else {
                							System.out.println(ultimaNoticia);
                						}
                						break;
                					case 0:
                						// sair
                                    	System.out.println("Esperamos o seu regresso!");
                                        System.exit(0);
                					default:
                						System.out.println("Introduza um valor v�lido.");
                				}
                			} while (opcao != 0);
                        }
                    }
                    // prosseguir sem autenticar-se
                    if (opcao == 2) {
                        // menu de opera��es para o cliente n�o autenticado
                        do {
                            System.out.println("1 - Consultar not�cias de um dado t�pico num dado intervalo de tempo\n2 - Consultar a �ltima not�cia de um dado t�pico\n0 - Sair");
                            opcao = Funcoes.lerInteiro();
                            switch (opcao) {
                                case 1:
                                	// consultar not�cias de um dado t�pico num intervalo de tempo
                                    System.out.println("Introduza o t�pico: ");
                                    topico = Funcoes.lerString();
                                    System.out.println("Introduza o dia da data inicial: ");
                                    diaInicio = Funcoes.lerInteiro();
                                    System.out.println("Introduza o m�s da data inicial: ");
                                    mesInicio = Funcoes.lerInteiro();
                                    System.out.println("Introduza o ano da data inicial: ");
                                    anoInicio = Funcoes.lerInteiro();
                                    System.out.println("Introduza o dia da data final: ");
                                    diaFim = Funcoes.lerInteiro();
                                    System.out.println("Introduza o m�s da data final: ");
                                    mesFim = Funcoes.lerInteiro();
                                    System.out.println("Introduza o ano da data final: ");
                                    anoFim = Funcoes.lerInteiro();
                                    // adicionar as datas a objetos do tipo Calendar
                                    inicio.set(anoInicio, mesInicio, diaInicio);
                                    fim.set(anoFim, mesFim, diaFim);
                                    noticiasTempo = objetoServidor.ConsultarNoticiasTopico(topico, inicio, fim, noticias);
                                    System.out.println(noticiasTempo);
                                    break;
                                case 2:
                                	// consultar �ltima not�cia de um dado t�pico
                                    System.out.println("Introduza o t�pico: ");
                                    topico = Funcoes.lerString();
                                    ultimaNoticia = objetoServidor.ConsultarUltimaNoticia(topico, noticias);
                                    // se o t�pico n�o estiver preenchido, ent�o o objeto est� inicializado a null
                                    if (ultimaNoticia.getTopico().equals("")) {
            							System.out.println("Ainda n�o h� not�cias subordinadas a esse t�pico.");
            						} else {
            							System.out.println(ultimaNoticia);
            						}
            						break;
                                case 0:
                                	// sair
                                	System.out.println("Esperamos o seu regresso!");
                                    System.exit(0);
                                default:
                                    System.out.println("Introduza um valor v�lido.");
                            }
                        } while (opcao != 0);
                    }
                } else {
                    System.out.println("Introduza uma op��o v�lida.");
                }
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// caso a liga��o falhe, termina o programa
			System.exit(0);
		}
	}
}
