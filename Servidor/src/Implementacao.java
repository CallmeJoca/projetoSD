import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;

// classe de implementa��o da interface 
public class Implementacao extends UnicastRemoteObject implements Interface {	
	public Implementacao() throws RemoteException {
		// buscar os m�todos da superclasse
		super();
	}
	
    // ----- m�todos para o cliente Produtor ----- //
	public ArrayList <String> AdicionarTopico (String topico, ArrayList <String> topicos) throws RemoteException {
		// verificar se o t�pico j� existe
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).equals(topico)) {
				// se o t�pico j� existe, retorna esse aviso ao utilizador
				return topicos;
			}
		}
		// adicionar o novo t�pico � lista
		topicos.add(topico);
		// retornar mensagem de sucesso
		return topicos;
	}
	
	public String InserirNoticia (String topico, String produtor, int dia, int mes, int ano, char [] texto, ArrayList <String> topicos, ArrayList <Noticia> noticias) throws RemoteException {
		int existe = 0;
		String mensagem = "";
		// verificar se o t�pico existe
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).equals(topico)) {
				// se o t�pico j� existe, ativa-se a flag e sai-se do ciclo for
				existe = 1;
				break;
			}
		}
		if (existe == 1) {
			// adicionar os dados � not�cia
			Noticia noticia = new Noticia();
			noticia.setTopico(topico);
			noticia.setProdutor(produtor);
			noticia.setTexto(texto);
			noticia.setDiaPublicacao(dia);
			noticia.setMesPublicacao(mes);
			noticia.setAnoPublicacao(ano);
			// adicionar a not�cia ao array de not�cias
			noticias.add(noticia);
			// escrever mensagem de sucesso
			mensagem = "Not�cia adicionada com sucesso";
		} else if (existe == 0) {
			// escrever mensagem de erro
			mensagem = "O t�pico desejado n�o existe";
		}
		// retornar a mensagem
		return mensagem;
	}
	
	public ArrayList <Noticia> ConsultarNoticias (String produtor, ArrayList <Noticia> noticias) throws RemoteException {
		// criar um ArrayList auxiliar para guardar as not�cias publicadas de um produtor
		ArrayList <Noticia> auxiliar = new ArrayList <Noticia> ();
		for (int i = 0; i < noticias.size(); i++) {
			// verificar se a not�cia daquela posi��o foi escrita por aquele produtor
			if (noticias.get(i).getProdutor().equals(produtor)) {
				// se o produtor coincide, adicionar a not�cia ao ArrayList auxiliar
				auxiliar.add(noticias.get(i));
			}
		}
		// retornar o ArrayList auxiliar
		return auxiliar;
	}
	
    // ----- m�todos para o cliente Consumidor ----- //
	public void SubscreverTopico (String topico) throws RemoteException {
		System.out.println("Por implementar.");
	}
	
	public void ConsultarNoticiasTopico (String topico, int diaInicio, int diaFim, int mesInicio, int mesFim, int anoInicio, int anoFim) throws RemoteException {
		// 
		System.out.println("Por implementar.");
	}
	
	public void ConsultarUltimaNoticia (String topico, ArrayList <Noticia> noticias) throws RemoteException {
		System.out.println("Por implementar.");
	}
}
