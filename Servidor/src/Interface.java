import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

// interface do servidor
public interface Interface extends Remote {
	// m�todos para o cliente Produtor
	public String AdicionarTopico (String topico, ArrayList <String> topicos) throws RemoteException;
	public String InserirNoticia (String topico, ArrayList <String> topicos, ArrayList <Noticia> noticias) throws RemoteException;
	public void ConsultarNoticias (ArrayList <Noticia> noticias) throws RemoteException;
    
	// m�todos para o cliente Consumidor
	public void SubscreverTopico (String topico) throws RemoteException;
	public void ConsultarNoticiasTopico (String topico, int diaInicio, int diaFim, int mesInicio, int mesFim, int anoInicio, int anoFim) throws RemoteException;
	public void ConsultarUltimaNoticia (String topico, ArrayList <Noticia> noticias) throws RemoteException;
}