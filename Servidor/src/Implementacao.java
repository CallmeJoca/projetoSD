import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;

// classe de implementacao da interface
public class Implementacao extends UnicastRemoteObject implements Interface {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String FICHEIRO_DE_UTILIZADORES = "utilizadores.txt";

	ArrayList <Utilizador> utilizadores = new ArrayList <Utilizador> ();
	ArrayList <String> topicos = new ArrayList <String> ();
	ArrayList <Noticia> noticias = new ArrayList <Noticia> ();
	ArrayList <Noticia> auxiliar = new ArrayList <Noticia> ();
	ArrayList <String> subscricoes = new ArrayList <String> ();

	public Implementacao() throws RemoteException {
		// buscar os metodos da superclasse
		super();
	}

	// criar um novo utilizador (aka registo)
	public Utilizador criarUtilizador (Utilizador utilizador) throws RemoteException {
		utilizadores = Funcoes.abrirFicheiroUtilizadores(utilizadores);
		Utilizador aux = new Utilizador();
		String nome, passe, tipo;
		// nome
		System.out.println("Introduza o nome de utilizador: ");
		nome = Funcoes.lerString();
		// verificar se o nome de utilizador ja existe nos registos
		for (int i = 0; i < utilizadores.size(); i++) {
			if (utilizadores.get(i).getNome().equals(nome)) {
				System.out.println("Ja existe um utilizador com esse nome");
				return aux;
			}
		}
		// palavra-passe
		System.out.println("Introduza a palavra-passe: ");
		passe = Funcoes.lerString();
		// tipo de cliente
		System.out.println("Introduza o tipo de cliente (Produtor/Consumidor): ");
		tipo = Funcoes.lerString();

		// adicionar as caracteristicas ao objeto do tipo Utilizador
		utilizador.setNome(nome);
		utilizador.setPasse(passe);
		utilizador.setTipo(tipo);

		// adicionar o novo utilizador ao ArrayList
		utilizadores.add(utilizador);

		// atualizar o ficheiro que contem os registos dos utilizadores e fecha-lo
		try {
			ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(FICHEIRO_DE_UTILIZADORES));
			oos.writeObject(utilizadores);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return utilizador;
	}

	// verificar o utilizador (aka login)
	public Utilizador verificarUtilizador (Utilizador utilizador) throws RemoteException {
		utilizadores = Funcoes.abrirFicheiroUtilizadores(utilizadores);
		Utilizador aux = new Utilizador();
		String nome, passe;
		// nome
		System.out.println("Introduza o nome de utilizador: ");
		nome = Funcoes.lerString();
		// palavra-passe
		System.out.println("Introduza a palavra-passe: ");
		passe = Funcoes.lerString();
		// verificar se o utilizador existe nos registos
		for (int i = 0; i < utilizadores.size(); i++) {
			if (utilizadores.get(i).getNome().equals(nome) && utilizadores.get(i).getPasse().equals(passe)) {
				// atribuir os valores obtidos na posicao encontrada ao objeto do tipo Utilizador para que estes possam ser usados na classe Cliente
				utilizador.setNome(utilizadores.get(i).getNome());
				utilizador.setPasse(utilizadores.get(i).getPasse());
				utilizador.setTipo(utilizadores.get(i).getTipo());
				utilizador.setSubscricoes(utilizadores.get(i).getSubscricoes());
				System.out.println("Utilizador autenticado");
				return utilizador;
			}
		}
		// passou o ciclo for sem retornar, logo, o utilizador nao existe
		System.out.println("Utilizador nao encontrado");
		return aux;
	}

    // ----- metodos para o cliente Produtor ----- //
	public ArrayList <String> AdicionarTopico (String topico) throws RemoteException {
		topicos = Funcoes.abrirFicheiroTopicos(topicos);
		// verificar se o topico ja existe
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).equals(topico)) {
				// se o topico ja existe, retorna esse aviso ao utilizador
				return topicos;
			}
		}
		// adicionar o novo topico a lista
		topicos.add(topico);
		// retornar mensagem de sucesso
		return topicos;
	}
	
	public ArrayList <String> VerTopicos () throws RemoteException {
		topicos = Funcoes.abrirFicheiroTopicos(topicos);
		return topicos;
	}

	public ArrayList <Noticia> InserirNoticia (String topico, String produtor, Calendar publicacao, String texto) throws RemoteException {
		noticias = Funcoes.abrirFicheiroNoticias(noticias);
		// adicionar os dados a notacia
		Noticia noticia = new Noticia();
		noticia.setTopico(topico);
		noticia.setProdutor(produtor);
		noticia.setTexto(texto);
		noticia.setData(publicacao);
		// adicionar a noticia ao array de noticias
		noticias.add(noticia);
		// retornar o array de noticias - com a nova noticia ou nao
		return noticias;
	}

	public ArrayList <Noticia> ConsultarNoticias (String produtor) throws RemoteException {
		noticias = Funcoes.abrirFicheiroNoticias(noticias);
		// criar um ArrayList auxiliar para guardar as noticias publicadas de um produtor
		ArrayList <Noticia> auxiliar = new ArrayList <Noticia> ();
		for (int i = 0; i < noticias.size(); i++) {
			// verificar se a noticia daquela posicao foi escrita por aquele produtor
			if (noticias.get(i).getProdutor().equals(produtor)) {
				// se o produtor coincide, adicionar a noticia ao ArrayList auxiliar
				auxiliar.add(noticias.get(i));
			}
		}
		// retornar o ArrayList auxiliar
		return auxiliar;
	}

    // ----- metodos para o cliente Consumidor ----- //
	public ArrayList <String> SubscreverTopico (String topico, Utilizador user) throws RemoteException {
		subscricoes = user.getSubscricoes();
		utilizadores = Funcoes.abrirFicheiroUtilizadores(utilizadores);
		// verificar se o topico que se quer subscrever ja esta subscrito ou nao
		for (int i = 0; i < subscricoes.size(); i++) {
			if (subscricoes.get(i).equals(topico)) {
				// retornar o ArrayList de topicos subscritos sem modificacoes
				return subscricoes;
			}
		}
		// se saiu do ciclo for sem retornar nenhuma vez, o topico desejado ainda nao esta subscrito
		subscricoes.add(topico);
		// retornar o ArrayList de topicos subscritos, com o novo topico acrescentado
		return subscricoes;
	}

	public ArrayList <Noticia> ConsultarNoticiasTopico (String topico, Calendar inicio, Calendar fim) throws RemoteException {
		noticias = Funcoes.abrirFicheiroNoticias(noticias);
		ArrayList <Noticia> auxiliar = new ArrayList <Noticia> ();
		for (int i = 0; i < noticias.size(); i++) {
			// se a noticia daquela posicao pertence a um determinado topico
			if (noticias.get(i).getTopico().equals(topico)) {
				// comparar as datas
				if (inicio.compareTo(noticias.get(i).getData()) < 0 && fim.compareTo(noticias.get(i).getData()) > 0) {
		            auxiliar.add(noticias.get(i));
		        }
			}
		}
		// retornar o ArrayList com as noticias de um topico de um determinado intervalo de tempo
		return auxiliar;
	}

	// Consulta a Noticia mais recente de um certo topico
	@SuppressWarnings("static-access")
	public Noticia ConsultarUltimaNoticia (String topico) throws RemoteException {
		noticias = Funcoes.abrirFicheiroNoticias(noticias);
		Noticia auxiliar = new Noticia ();
		Calendar dataAux = Calendar.getInstance();
		dataAux.clear();
		dataAux.set(0, 0, 0);

		// verificar, do fim ao principio, qual o topico das noticias
		for (int i = noticias.size()-1; i >= 0; i--) {
			if (noticias.get(i).getTopico().equals(topico)) {
				// se o topico for igual ao passado em parametro, entao verifica se a DATA é mais recente. Se for mais recente, esta fica guardada.
				if(noticias.get(i).getData().compareTo(dataAux) >= 0) {
					// guarda a data para verificar as proximas noticias
					dataAux.set(noticias.get(i).getData().get(noticias.get(i).getData().YEAR), noticias.get(i).getData().get(noticias.get(i).getData().MONTH), noticias.get(i).getData().get(noticias.get(i).getData().DAY_OF_MONTH));
					// guarda os dados da noticia na Noticia auxiliar
					auxiliar.setTopico(noticias.get(i).getTopico());
					auxiliar.setProdutor(noticias.get(i).getProdutor());
					auxiliar.setTexto(noticias.get(i).getTexto());
					auxiliar.setData(noticias.get(i).getData());
				}
			}
		}
		// devolve a Noticia mais recente
		return auxiliar;
	}
}
