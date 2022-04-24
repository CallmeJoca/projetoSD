package ServidorBackup;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

// interface do servidor
public interface Interface extends Remote {
	// metodos para o cliente Produtor
	public ArrayList <String> AdicionarTopico (String topico, ArrayList <String> topicos) throws RemoteException;
	public ArrayList <Noticia> InserirNoticia (String topico, String produtor, Calendar publicacao, char [] texto, ArrayList <String> topicos, ArrayList <Noticia> noticias) throws RemoteException;
	public ArrayList <Noticia> ConsultarNoticias (String produtor, ArrayList <Noticia> noticias) throws RemoteException;
    
	// m�todos para o cliente Consumidor
	public ArrayList <String> SubscreverTopico (String topico, ArrayList <String> subscricoes) throws RemoteException;
	public ArrayList <Noticia> ConsultarNoticiasTopico (String topico, Calendar inicio, Calendar fim, ArrayList <Noticia> noticias) throws RemoteException;
	public Noticia ConsultarUltimaNoticia (String topico, ArrayList <Noticia> noticias) throws RemoteException;
}