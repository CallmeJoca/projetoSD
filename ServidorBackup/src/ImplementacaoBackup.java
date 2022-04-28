import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

// classe de implementacao da interface 
public class ImplementacaoBackup extends UnicastRemoteObject implements Interface {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImplementacaoBackup() throws RemoteException {
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
	
	public ArrayList <Noticia> InserirNoticia (String topico, String produtor, Calendar publicacao, String texto, ArrayList <String> topicos, ArrayList <Noticia> noticias) throws RemoteException {
		int existe = 0;
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
			// adicionar a not�cia ao array de not�cias
			noticias.add(noticia);
		}
		// retornar o array de not�cias - com a nova not�cia ou n�o
		return noticias;
	}
	
	public ArrayList <Noticia> ConsultarNoticias (String produtor, ArrayList <Noticia> noticias) throws RemoteException {
		// criar um ArrayList auxiliar para guardar as notocias publicadas de um produtor
		ArrayList <Noticia> auxiliar = new ArrayList <Noticia> ();
		for (int i = 0; i < noticias.size(); i++) {
			// verificar se a notocia daquela posicao foi escrita por aquele produtor
			if (noticias.get(i).getProdutor().equals(produtor)) {
				// se o produtor coincide, adicionar a notacia ao ArrayList auxiliar
				auxiliar.add(noticias.get(i));
			}
		}
		// retornar o ArrayList auxiliar
		return auxiliar;
	}
	
    // ----- m�todos para o cliente Consumidor ----- //
	public ArrayList <String> SubscreverTopico (String topico, ArrayList <String> subscricoes) throws RemoteException {
		// verificar se o t�pico que se quer subscrever j� est� subscrito ou n�o
		for (int i = 0; i < subscricoes.size(); i++) {
			if (subscricoes.get(i).equals(topico)) {
				// retornar o ArrayList de t�picos subscritos sem modifica��es
				return subscricoes;
			}
		}
		// se saiu do ciclo for sem retornar nenhuma vez, o t�pico desejado ainda n�o est� subscrito
		subscricoes.add(topico);
		// retornar o ArrayList de t�picos subscritos, com o novo t�pico acrescentado
		return subscricoes;
	}
	
	public ArrayList <Noticia> ConsultarNoticiasTopico (String topico, Calendar inicio, Calendar fim, ArrayList <Noticia> noticias) throws RemoteException {
		ArrayList <Noticia> auxiliar = new ArrayList <Noticia> ();
		for (int i = 0; i < noticias.size(); i++) {
			// se a not�cia daquela posi��o pertence a um determinado t�pico
			if (noticias.get(i).getTopico().equals(topico)) {
				// comparar as datas
				if (inicio.compareTo(noticias.get(i).getData()) < 0 && fim.compareTo(noticias.get(i).getData()) > 0) {
		            auxiliar.add(noticias.get(i));
		        }
			}
		}
		// retornar o ArrayList com as not�cias de um t�pico de um determinado intervalo de tempo
		return auxiliar;
	}
	
	public Noticia ConsultarUltimaNoticia (String topico, ArrayList <Noticia> noticias) throws RemoteException {
		Noticia auxiliar = new Noticia ();
		// verificar, do fim para o princ�pio, qual o t�pico das not�cias
		for (int i = noticias.size(); i > 0; i--) {
			// se o t�pico for igual ao passado em par�metro, ent�o retorna a not�cia dessa posi��o do ArrayList
			if (noticias.get(i).getTopico().equals(topico)) {
				return noticias.get(i);
			}
		}
		// se saiu do ciclo for, n�o h� not�cias sobre aquele t�pico, logo retorna uma not�cia vazia
		return auxiliar;
	}
}
