import java.rmi.*;

public interface CallbackServidor extends Remote {
	public void subscrever (String s, CallbackCliente c) throws RemoteException;
}
