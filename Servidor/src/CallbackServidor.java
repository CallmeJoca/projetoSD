import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackServidor extends Remote {
	public void subscrever (String s, CallbackCliente c) throws RemoteException;
}
