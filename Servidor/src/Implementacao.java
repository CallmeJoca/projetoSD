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
	public String AdicionarTopico (String topico, ArrayList <String> topicos) throws RemoteException {
		// verificar se o t�pico j� existe
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).equals(topico)) {
				// se o t�pico j� existe, retorna esse aviso ao utilizador
				return "T�pico j� existente";
			}
		}
		// adicionar o novo t�pico � lista
		topicos.add(topico);
		// retornar mensagem de sucesso
		return "T�pico adicionado com sucesso";
	}
	
	public String InserirNoticia (String topico, ArrayList <String> topicos, ArrayList <Noticia> noticias) throws RemoteException {
		String mensagem = "";
		boolean existe = false;
		// verificar se o t�pico existe
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).equals(topico)) {
				// se o t�pico j� existe, ativa-se a flag e sai-se do ciclo for
				existe = true;
				break;
			}
		}
		if (existe == true) {
			// 
			// adiciona a not�cia
			Noticia noticia = new Noticia();
			noticia.setTopico(topico);
			// escrever mensagem de sucesso
			mensagem = "Not�cia adicionada com sucesso";
		} else if (existe == false) {
			// escrever mensagem de erro
			mensagem = "O t�pico desejado n�o existe";
		}
		// retornar a mensagem
		return mensagem;
	}
	
	public void ConsultarNoticias (ArrayList <Noticia> noticias) throws RemoteException {
		System.out.println("Por implementar.");
	}
	
    // ----- m�todos para o cliente Consumidor ----- //
	public void SubscreverTopico (String topico) throws RemoteException {
		System.out.println("Por implementar.");
	}
	
	public void ConsultarNoticiasTopico (String topico, int diaInicio, int diaFim, int mesInicio, int mesFim, int anoInicio, int anoFim) throws RemoteException {
		System.out.println("Por implementar.");
	}
	
	public void ConsultarUltimaNoticia (String topico, ArrayList <Noticia> noticias) throws RemoteException {
		System.out.println("Por implementar.");
	}
}
