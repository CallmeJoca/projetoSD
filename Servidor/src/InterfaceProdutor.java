import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

// interface do produtor
public interface InterfaceProdutor extends Remote {
	// métodos para o cliente Produtor
	public void AdicionarTopico (String topico) throws RemoteException;
	public void ConsultarTopicos (ArrayList <String> topicos) throws RemoteException;
	public void InserirNoticia (String topico) throws RemoteException;
	public void ConsultarNoticias (ArrayList <String> noticias) throws RemoteException;
}