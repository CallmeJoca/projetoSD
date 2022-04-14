import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;

// classe de implementa��o da interface do produtor
public class ImplementacaoConsumidor extends UnicastRemoteObject implements InterfaceConsumidor {
	public ImplementacaoConsumidor() throws RemoteException {
		// buscar os m�todos da superclasse
		super();
	}
	
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
