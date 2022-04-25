import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

// classe de implementacao da interface
public class Implementacao extends UnicastRemoteObject implements Interface {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Implementacao() throws RemoteException {
		// buscar os metodos da superclasse
		super();
	}

    // ----- metodos para o cliente Produtor ----- //
	public ArrayList <String> AdicionarTopico (String topico, ArrayList <String> topicos) throws RemoteException {
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

	public ArrayList <Noticia> InserirNoticia (String topico, String produtor, Calendar publicacao, char [] texto, ArrayList <String> topicos, ArrayList <Noticia> noticias) throws RemoteException {
		// adicionar os dados a noticia
		Noticia noticia = new Noticia();
		noticia.setTopico(topico);
		noticia.setProdutor(produtor);
		noticia.setTexto(texto);
		noticia.setData(publicacao);
		// adicionar a not�cia ao array de noticias
		noticias.add(noticia);
		// retornar o array de noticias - com a nova noticia
		return noticias;
	}

	public ArrayList <Noticia> ConsultarNoticias (String produtor, ArrayList <Noticia> noticias) throws RemoteException {
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
	public ArrayList <String> SubscreverTopico (String topico, ArrayList <String> subscricoes) throws RemoteException {
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

	public ArrayList <Noticia> ConsultarNoticiasTopico (String topico, Calendar inicio, Calendar fim, ArrayList <Noticia> noticias) throws RemoteException {
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
	public Noticia ConsultarUltimaNoticia (String topico, ArrayList <Noticia> noticias) throws RemoteException {
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
