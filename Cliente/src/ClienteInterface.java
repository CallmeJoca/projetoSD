import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

// interface do produtor
public interface ClienteInterface extends Remote {
	// métodos para o cliente Produtor
	public void AdicionarTopico (String topico) throws RemoteException;
	public void ConsultarTopicos (ArrayList <String> topicos) throws RemoteException;
	public void InserirNoticia (String topico) throws RemoteException;
	public void ConsultarNoticias (ArrayList <String> noticias) throws RemoteException;
	
	// métodos para o cliente Consumidor
	public void SubscreverTopico (String topico) throws RemoteException;
	public void ConsultarNoticiasTopico (String topico, int diaInicio, int diaFim, int mesInicio, int mesFim) throws RemoteException;
	public void ConsultarUltimaNoticia (String topico) throws RemoteException;
}