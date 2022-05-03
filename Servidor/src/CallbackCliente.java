import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackCliente extends Remote {
	public void callback (String topico) throws RemoteException;
}
