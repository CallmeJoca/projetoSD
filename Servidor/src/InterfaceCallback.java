import java.rmi.*;

public interface InterfaceCallback extends Remote {
	public void callback (String topico) throws RemoteException;
}
