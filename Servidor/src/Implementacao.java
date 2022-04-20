import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;

// classe de implementação da interface 
public class Implementacao extends UnicastRemoteObject implements Interface {	
	public Implementacao() throws RemoteException {
		// buscar os métodos da superclasse
		super();
	}
	
    // ----- métodos para o cliente Produtor ----- //
	public String AdicionarTopico (String topico, ArrayList <String> topicos) throws RemoteException {
		// verificar se o tópico já existe
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).equals(topico)) {
				// se o tópico já existe, retorna esse aviso ao utilizador
				return "Tópico já existente";
			}
		}
		// adicionar o novo tópico à lista
		topicos.add(topico);
		// retornar mensagem de sucesso
		return "Tópico adicionado com sucesso";
	}
	
	public String InserirNoticia (String topico, ArrayList <String> topicos, ArrayList <Noticia> noticias) throws RemoteException {
		String mensagem = "";
		boolean existe = false;
		// verificar se o tópico existe
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).equals(topico)) {
				// se o tópico já existe, ativa-se a flag e sai-se do ciclo for
				existe = true;
				break;
			}
		}
		if (existe == true) {
			// 
			// adiciona a notícia
			Noticia noticia = new Noticia();
			noticia.setTopico(topico);
			// escrever mensagem de sucesso
			mensagem = "Notícia adicionada com sucesso";
		} else if (existe == false) {
			// escrever mensagem de erro
			mensagem = "O tópico desejado não existe";
		}
		// retornar a mensagem
		return mensagem;
	}
	
	public void ConsultarNoticias (ArrayList <Noticia> noticias) throws RemoteException {
		System.out.println("Por implementar.");
	}
	
    // ----- métodos para o cliente Consumidor ----- //
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
