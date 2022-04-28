import java.rmi.*;

public interface CallbackCliente extends Remote {
	public void callback (String topico) throws RemoteException;
}
