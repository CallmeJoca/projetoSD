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
		int existe = 0;
		@SuppressWarnings("unused")
		String mensagem = "";
		// verificar se o topico existe
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).equals(topico)) {
				// se o topico ja existe, ativa-se a flag e sai-se do ciclo for
				existe = 1;
				break;
			}
		}
		if (existe == 1) {
			// adicionar os dados a notacia
			Noticia noticia = new Noticia();
			noticia.setTopico(topico);
			noticia.setProdutor(produtor);
			noticia.setTexto(texto);
			noticia.setData(publicacao);
			// adicionar a noticia ao array de noticias
			noticias.add(noticia);
		}
		// retornar o array de noticias - com a nova noticia ou nao
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
	
	public Noticia ConsultarUltimaNoticia (String topico, ArrayList <Noticia> noticias) throws RemoteException {
		Noticia auxiliar = new Noticia ();
		// verificar, do fim para o principio, qual o topico das noticias
		for (int i = noticias.size(); i > 0; i--) {
			// se o topico for igual ao passado em parametro, entao retorna a noticia dessa posicao do ArrayList
			if (noticias.get(i).getTopico().equals(topico)) {
				return noticias.get(i);
			}
		}
		// se saiu do ciclo for, nao ha noticias sobre aquele topico, logo retorna uma noticia vazia
		return auxiliar;
	}
}
