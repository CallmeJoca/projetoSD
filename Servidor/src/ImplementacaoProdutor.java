import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;

// classe de implementação da interface do produtor
public class ImplementacaoProdutor extends UnicastRemoteObject implements InterfaceProdutor {	
	public ImplementacaoProdutor() throws RemoteException {
		// buscar os métodos da superclasse
		super();
	}
	
	public void AdicionarTopico (String topico) throws RemoteException {
		System.out.println("Por implementar.");
	}
	
	public void ConsultarTopicos (ArrayList <String> topicos) throws RemoteException {
		System.out.println("Por implementar.");
	}
	
	public void InserirNoticia (String topico) throws java.rmi.RemoteException {
		System.out.println("Por implementar.");
	}
	
	public void ConsultarNoticias (ArrayList <String> noticias) throws RemoteException {
		System.out.println("Por implementar.");
	}
}
