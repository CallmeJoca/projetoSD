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
	
	public String InserirNoticia (String topico, String produtor, ArrayList <String> topicos, ArrayList <Noticia> noticias) throws RemoteException {
		String mensagem = "";
		char [] texto = new char [180];
		boolean existe = false;
		int dia, mes, ano;
		// verificar se o tópico existe
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).equals(topico)) {
				// se o tópico já existe, ativa-se a flag e sai-se do ciclo for
				existe = true;
				break;
			}
		}
		if (existe == true) {
			// ler os dados necessários para criar a notícia
			// dia da publicação
			System.out.println("Introduza o dia de publicação: ");
			dia = Funcoes.lerInteiro();
			// mês da publicação
			System.out.println("Introduza o mês de publicação: ");
			mes = Funcoes.lerInteiro();
			// ano da publicação
			System.out.println("Introduza o ano de publicação: ");
			ano = Funcoes.lerInteiro();
			// corpo da notícia
			System.out.println("Introduza o texto da notícia: ");
			for (int i = 0; i < 180; i++) {
				texto[i] = Funcoes.lerCaratere();
			}
			// adicionar os dados à notícia
			Noticia noticia = new Noticia();
			noticia.setTopico(topico);
			noticia.setProdutor(produtor);
			noticia.setTexto(texto);
			noticia.setDiaPublicacao(dia);
			noticia.setMesPublicacao(mes);
			noticia.setAnoPublicacao(ano);
			// adicionar a notícia ao array de notícias
			noticias.add(noticia);
			// escrever mensagem de sucesso
			mensagem = "Notícia adicionada com sucesso";
		} else if (existe == false) {
			// escrever mensagem de erro
			mensagem = "O tópico desejado não existe";
		}
		// retornar a mensagem
		return mensagem;
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
