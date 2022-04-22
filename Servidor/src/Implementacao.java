import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

// classe de implementação da interface 
public class Implementacao extends UnicastRemoteObject implements Interface {	
	public Implementacao() throws RemoteException {
		// buscar os métodos da superclasse
		super();
	}
	
    // ----- métodos para o cliente Produtor ----- //
	public ArrayList <String> AdicionarTopico (String topico, ArrayList <String> topicos) throws RemoteException {
		// verificar se o tópico já existe
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).equals(topico)) {
				// se o tópico já existe, retorna esse aviso ao utilizador
				return topicos;
			}
		}
		// adicionar o novo tópico à lista
		topicos.add(topico);
		// retornar mensagem de sucesso
		return topicos;
	}
	
	public ArrayList <Noticia> InserirNoticia (String topico, String produtor, Calendar publicacao, char [] texto, ArrayList <String> topicos, ArrayList <Noticia> noticias) throws RemoteException {
		int existe = 0;
		// verificar se o tópico existe
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).equals(topico)) {
				// se o tópico já existe, ativa-se a flag e sai-se do ciclo for
				existe = 1;
				break;
			}
		}
		if (existe == 1) {
			// adicionar os dados à notícia
			Noticia noticia = new Noticia();
			noticia.setTopico(topico);
			noticia.setProdutor(produtor);
			noticia.setTexto(texto);
			noticia.setDiaPublicacao(publicacao.get(Calendar.DAY_OF_MONTH));
			noticia.setMesPublicacao(publicacao.get(Calendar.MONTH));
			noticia.setAnoPublicacao(publicacao.get(Calendar.YEAR));
			// adicionar a notícia ao array de notícias
			noticias.add(noticia);
		}
		// retornar o array de notícias - com a nova notícia ou não
		return noticias;
	}
	
	public ArrayList <Noticia> ConsultarNoticias (String produtor, ArrayList <Noticia> noticias) throws RemoteException {
		// criar um ArrayList auxiliar para guardar as notícias publicadas de um produtor
		ArrayList <Noticia> auxiliar = new ArrayList <Noticia> ();
		for (int i = 0; i < noticias.size(); i++) {
			// verificar se a notícia daquela posição foi escrita por aquele produtor
			if (noticias.get(i).getProdutor().equals(produtor)) {
				// se o produtor coincide, adicionar a notícia ao ArrayList auxiliar
				auxiliar.add(noticias.get(i));
			}
		}
		// retornar o ArrayList auxiliar
		return auxiliar;
	}
	
    // ----- métodos para o cliente Consumidor ----- //
	public ArrayList <String> SubscreverTopico (String topico, ArrayList <String> subscricoes) throws RemoteException {
		// verificar se o tópico que se quer subscrever já está subscrito ou não
		for (int i = 0; i < subscricoes.size(); i++) {
			if (subscricoes.get(i).equals(topico)) {
				// retornar o ArrayList de tópicos subscritos sem modificações
				return subscricoes;
			}
		}
		// se saiu do ciclo for sem retornar nenhuma vez, o tópico desejado ainda não está subscrito
		subscricoes.add(topico);
		// retornar o ArrayList de tópicos subscritos, com o novo tópico acrescentado
		return subscricoes;
	}
	
	public ArrayList <Noticia> ConsultarNoticiasTopico (String topico, Calendar inicio, Calendar fim, ArrayList <Noticia> noticias) throws RemoteException {
		ArrayList <Noticia> auxiliar = new ArrayList <Noticia> ();
		Calendar data = Calendar.getInstance();
		for (int i = 0; i < noticias.size(); i++) {
			// se a notícia daquela posição pertence a um determinado tópico
			if (noticias.get(i).getTopico().equals(topico)) {
				data.set(noticias.get(i).getAnoPublicacao(), noticias.get(i).getMesPublicacao(), noticias.get(i).getDiaPublicacao());
				// comparar as datas
				if (inicio.compareTo(data) < 0 && fim.compareTo(data) > 0) {
		            auxiliar.add(noticias.get(i));
		        }
			}
		}
		// retornar o ArrayList com as notícias de um tópico de um determinado intervalo de tempo
		return auxiliar;
	}
	
	public Noticia ConsultarUltimaNoticia (String topico, ArrayList <Noticia> noticias) throws RemoteException {
		Noticia auxiliar = new Noticia ();
		// verificar, do fim para o princípio, qual o tópico das notícias
		for (int i = noticias.size(); i > 0; i--) {
			// se o tópico for igual ao passado em parâmetro, então retorna a notícia dessa posição do ArrayList
			if (noticias.get(i).getTopico().equals(topico)) {
				return noticias.get(i);
			}
		}
		// se saiu do ciclo for, não há notícias sobre aquele tópico, logo retorna uma notícia vazia
		return auxiliar;
	}
}
