import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

// interface do produtor
public interface InterfaceConsumidor extends Remote {
	// métodos para o cliente Consumidor
	public void SubscreverTopico (String topico) throws RemoteException;
	public void ConsultarNoticiasTopico (String topico, int diaInicio, int diaFim, int mesInicio, int mesFim) throws RemoteException;
	public void ConsultarUltimaNoticia (String topico) throws RemoteException;
}