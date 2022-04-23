package Servidor;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;

// classe de implementacao da interface 
public class Implementacao extends UnicastRemoteObject implements Interface {	
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
	
	public String InserirNoticia (String topico, String produtor, int dia, int mes, int ano, char [] texto, ArrayList <String> topicos, ArrayList <Noticia> noticias) throws RemoteException {
		int existe = 0;
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
			noticia.setDiaPublicacao(dia);
			noticia.setMesPublicacao(mes);
			noticia.setAnoPublicacao(ano);
			// adicionar a notacia ao array de notacias
			noticias.add(noticia);
			// escrever mensagem de sucesso
			mensagem = "Noticia adicionada com sucesso";
		} else if (existe == 0) {
			// escrever mensagem de erro
			mensagem = "O topico desejado nao existe";
		}
		// retornar a mensagem
		return mensagem;
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
	
    // ----- metodos para o cliente Consumidor ----- //
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
