import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;

// classe de implementa��o da interface do produtor
public class Implementacao extends UnicastRemoteObject implements Interface {
	public Implementacao() throws RemoteException {
		// buscar os m�todos da superclasse
		super();
	}
	
	// ----- METODOS PRODUTOR -----//
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
	
	// ----- METODOS CONSUMIDOR ----- //
	public void SubscreverTopico (String topico) throws RemoteException {
		System.out.println("Por implementar.");
	}
	
	public void ConsultarNoticiasTopico (String topico, int diaInicio, int diaFim, int mesInicio, int mesFim) throws RemoteException {
		System.out.println("Por implementar.");
	}
	
	public void ConsultarUltimaNoticia (String topico) throws RemoteException {
		System.out.println("Por implementar.");
	}
}
