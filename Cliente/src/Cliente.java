import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;

public class Cliente extends UnicastRemoteObject implements CallbackCliente {
    public Cliente() throws RemoteException {
    	// buscar metodos da superclasse
		super();
	}

    // metodo remoto de callback
    public void callback (String topico) throws RemoteException {
		System.out.println("Ha uma nova noticia subordinada ao topico " + topico + ". Pesquise para ver.");
	}

	public static void main(String [] args) {
        int opcao = -1, diaPublicacao, mesPublicacao, anoPublicacao, diaInicio, diaFim, mesInicio, mesFim, anoInicio, anoFim;
        boolean verificacao = false;
        Utilizador user = new Utilizador();
        Noticia ultimaNoticia = new Noticia();
        String topico, texto = "";
        Calendar inicio = Calendar.getInstance(), fim = Calendar.getInstance(), publicacao = Calendar.getInstance();
		ArrayList <Noticia> noticiasTempo = new ArrayList <Noticia> ();
		ArrayList <Utilizador> utilizadores = new ArrayList <Utilizador> ();
		ArrayList <String> topicos = new ArrayList <String> ();
		ArrayList <Noticia> noticias = new ArrayList <Noticia> ();
		ArrayList <Noticia> auxiliar = new ArrayList <Noticia> ();
		ArrayList <String> subscricoes = new ArrayList <String> ();

		try {
			// ligar o cliente ao servidor
			Interface objetoServidor = (Interface) Naming.lookup("Servidor");
			CallbackServidor servidor = (CallbackServidor) Naming.lookup("Callback");
			Cliente c = new Cliente();
			servidor.subscrever("Cliente", (Cliente) c);
            // iniciar cli com o utilizador
            System.out.println("Bem-vindo ao seu servidor de noticias.\n\nDeseja autenticar-se?\n1 - Sim\n2 - Nao");
            while (true) {
                opcao = Funcoes.lerInteiro();
                if (opcao == 1 || opcao == 2) {
                    // autenticar-se - por login ou registo
                    if (opcao == 1) {
                        System.out.println("1 - Login\n2 - Registo");
                        opcao = Funcoes.lerInteiro();
                        if (opcao == 1) {
                            // login
							while (user.getNome().equals("")) {
                                user = objetoServidor.verificarUtilizador(user);
                            }
                        }
                        if (opcao == 2) {
                            // registar
                            while (user.getNome().equals("")) {
                                user = objetoServidor.criarUtilizador(user);
                            }
                        }
                        // verificar o tipo de cliente
                        if (user.getTipo().equals("Produtor")) {
                        	// fazer as operacoes permitidas a um cliente Produtor
                        	// menu de operacoes para o cliente Produtor
                			do {
                				System.out.println("1 - Adicionar topico\n2 - Consultar topicos existentes\n3 - Inserir noticia\n4 - Consultar todas as noticias publicadas\n0 - Sair");
                				opcao = Funcoes.lerInteiro();
                				switch (opcao) {
                					case 1:
                						// adicionar um topico
                						System.out.println("Introduza o topico: ");
                						topico = Funcoes.lerString();
                						topicos = objetoServidor.AdicionarTopico(topico);
                                        Funcoes.escreverFicheiroTopicos(topicos);
                                        break;
                					case 2:
                						// consultar a lista de topicos disponiveis
                						topicos = objetoServidor.VerTopicos();
                						System.out.println("Lista de topicos disponiveis:\n" + topicos);
                						break;
                					case 3:
                						// inserir uma noticia subordinada a um topico
                						// topico
                						System.out.println("Introduza o topico: ");
                						topico = Funcoes.lerString();
										// se o topico nao existir nao ira conseguir adicionar a noticia
										if(objetoServidor.VerificarTopico(topico) == false) {
											System.out.println("Topico inexistente! Adicione-o primeiro.");
											break;
										}
                						// dia da publicacao
                						System.out.println("Introduza o dia de publicacao: ");
                						diaPublicacao = Funcoes.lerInteiro();
                						// mes da publicacao
                						System.out.println("Introduza o mes de publicacao: ");
                						mesPublicacao = Funcoes.lerInteiro() - 1;
                						// ano da publicacao
                						System.out.println("Introduza o ano de publicacao: ");
                						anoPublicacao = Funcoes.lerInteiro();
                						// introduzir a data num objeto do tipo Calendar
                                        publicacao.clear();
                				        publicacao.set(anoPublicacao, mesPublicacao, diaPublicacao);
                						// corpo da noticia
                						System.out.println("Introduza o texto da noticia: ");
                						// ler uma String com o corpo da noticia
                						do {
                							texto = Funcoes.lerString();
                						} while (texto.length() > 180);
                						noticias = objetoServidor.InserirNoticia(topico, user.getNome(), publicacao, texto);
                                        Funcoes.escreverFicheiroNoticias(noticias);
                                        // callback para os utilizadores
                                        c.callback(topico);
                                        break;
                					case 4:
                						// consultar todas as noticias publicadas ate ao momento
                						auxiliar = objetoServidor.ConsultarNoticias(user.getNome());
                						// se o ArrayList auxiliar estiver vazio, nao ha noticias publicadas
                						if (auxiliar == null) {
                							System.out.println("O produtor ainda nao tem noticias publicadas.");
                						// se o ArrayList auxiliar tiver noticias, estas sao apresentadas ao utilizador
                						} else {
                							System.out.println("Noticias publicadas:\n" + auxiliar);
                						}
                						break;
                					case 0:
                						// sair
                                    	System.out.println("Esperamos o seu regresso!");
                                        System.exit(0);
                					default:
                						System.out.println("Introduza um valor valido.");
                				}
                			} while (opcao != 0);
                        } else if (user.getTipo().equals("Consumidor")) {
                        	// fazer as operacoes permitidas a um cliente Consumidor
                        	// menu de operacoes para o cliente Consumidor
                        	do {
                				System.out.println("1 - Subscrever topico\n2 - Consultar noticias de um dado topico num dado intervalo de tempo\n3 - Consultar a ultima noticia de um dado topico\n0 - Sair");
                				opcao = Funcoes.lerInteiro();
                				switch (opcao) {
                					case 1:
                						// subscrever um topico
                						System.out.println("Introduza o topico: ");
                						topico = Funcoes.lerString();
                						subscricoes = objetoServidor.SubscreverTopico(topico, user);
                                        user.setSubscricoes(subscricoes);
                						//utilizadores = Funcoes.setSubscricoesUtlizador(utilizadores, user);
                						Funcoes.escreverFicheiroUtilizadores(utilizadores);
                						break;
                					case 2:
                						// consultar noticias de um dado topico num intervalo de tempo
                                        System.out.println("Introduza o topico: ");
                                        topico = Funcoes.lerString();
										// se o topico nao existir nao ira conseguir visualizar noticias
										if(objetoServidor.VerificarTopico(topico) == false) {
											System.out.println("Topico inexistente.");
											break;
										}
                                        System.out.println("Introduza o dia da data inicial: ");
                                        diaInicio = Funcoes.lerInteiro();
                                        System.out.println("Introduza o mes da data inicial: ");
                                        mesInicio = Funcoes.lerInteiro() - 1;
                                        System.out.println("Introduza o ano da data inicial: ");
                                        anoInicio = Funcoes.lerInteiro();
                                        System.out.println("Introduza o dia da data final: ");
                                        diaFim = Funcoes.lerInteiro();
                                        System.out.println("Introduza o mes da data final: ");
                                        mesFim = Funcoes.lerInteiro() - 1;
                                        System.out.println("Introduza o ano da data final: ");
                                        anoFim = Funcoes.lerInteiro();
                                        // adicionar as datas a objetos do tipo Calendar
                                        inicio.set(anoInicio, mesInicio, diaInicio);
                                        fim.set(anoFim, mesFim, diaFim);
                                        noticiasTempo = objetoServidor.ConsultarNoticiasTopico(topico, inicio, fim);
                                        System.out.println(noticiasTempo);
                                        break;
                					case 3:
                						// consultar ultima noticia de um dado topico
                						System.out.println("Introduza o topico: ");
                						topico = Funcoes.lerString();
                						ultimaNoticia = objetoServidor.ConsultarUltimaNoticia(topico);
                                        // se o topico nao estiver preenchido, entao o objeto esta inicializado a null
                						if (ultimaNoticia.getTopico().equals("")) {
                							System.out.println("Ainda nao ha noticias subordinadas a esse topico.");
                						} else {
                							System.out.println(ultimaNoticia);
                						}
                						break;
                					case 0:
                						// sair
                                    	System.out.println("Esperamos o seu regresso!");
                                        System.exit(0);
                					default:
                						System.out.println("Introduza um valor valido.");
                				}
                			} while (opcao != 0);
                        }
                    }
                    // prosseguir sem autenticar-se
                    if (opcao == 2) {
                        // menu de operacoes para o cliente nao autenticado
                        do {
                            System.out.println("1 - Consultar noticias de um dado topico num dado intervalo de tempo\n2 - Consultar a ultima noticia de um dado topico\n0 - Sair");
                            opcao = Funcoes.lerInteiro();
                            switch (opcao) {
                                case 1:
                                	// consultar noticias de um dado topico num intervalo de tempo
                                    System.out.println("Introduza o topico: ");
                                    topico = Funcoes.lerString();
									// se o topico nao existir nao ira conseguir visualizar noticias
									if(objetoServidor.VerificarTopico(topico) == false) {
										System.out.println("Topico inexistente.");
										break;
									}
                                    System.out.println("Introduza o dia da data inicial: ");
                                    diaInicio = Funcoes.lerInteiro();
                                    System.out.println("Introduza o mes da data inicial: ");
                                    mesInicio = Funcoes.lerInteiro() - 1;
                                    System.out.println("Introduza o ano da data inicial: ");
                                    anoInicio = Funcoes.lerInteiro();
                                    System.out.println("Introduza o dia da data final: ");
                                    diaFim = Funcoes.lerInteiro();
                                    System.out.println("Introduza o mes da data final: ");
                                    mesFim = Funcoes.lerInteiro() - 1;
                                    System.out.println("Introduza o ano da data final: ");
                                    anoFim = Funcoes.lerInteiro();
                                    // adicionar as datas a objetos do tipo Calendar
                                    inicio.set(anoInicio, mesInicio, diaInicio);
                                    fim.set(anoFim, mesFim, diaFim);
                                    noticiasTempo = objetoServidor.ConsultarNoticiasTopico(topico, inicio, fim);
                                    System.out.println(noticiasTempo);
                                    break;
                                case 2:
                                	// consultar ultima noticia de um dado tppico
                                    System.out.println("Introduza o topico: ");
                                    topico = Funcoes.lerString();
                                    ultimaNoticia = objetoServidor.ConsultarUltimaNoticia(topico);
                                    // se o topico nao estiver preenchido, entao o objeto esta inicializado a null
                                    if (ultimaNoticia.getTopico().equals("")) {
            							System.out.println("Ainda nao ha noticias subordinadas a esse topico.");
            						} else {
            							System.out.println(ultimaNoticia);
            						}
            						break;
                                case 0:
                                	// sair
                                	System.out.println("Esperamos o seu regresso!");
                                    System.exit(0);
                                default:
                                    System.out.println("Introduza um valor valido.");
                            }
                        } while (opcao != 0);
                    }
                } else {
                    System.out.println("Introduza uma opcao valida.");
                }
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// caso a ligacao falhe, termina o programa
			System.exit(0);
		}
	}
}
