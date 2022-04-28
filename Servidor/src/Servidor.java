import java.util.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class Servidor extends UnicastRemoteObject implements CallbackServidor {
	private static CallbackCliente c;
	
	public Servidor () throws RemoteException {
		// buscar metodos da superclasse
		super();
	}
	
	public void subscrever (String s, CallbackCliente c) {
		this.c = c;
	}
		
	@SuppressWarnings({ "removal", "deprecation" })
	public static void main(String [] args) {
		// instalar um gestor de seguranca
    	System.setSecurityManager(new SecurityManager());
    	System.out.println("Servidor Principal a Inicializar\n");
    	
    	try {
			// inicializar a execucao do registo no porto desejado
			java.rmi.registry.LocateRegistry.createRegistry(1099);
    		System.out.println("Registo RMI pronto.");
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
    	
		try {
			// inicializar o objeto de callback
			Servidor servidor = new Servidor();
			// instanciar objeto remoto
			Interface cliente = new Implementacao();
			// registar o objeto remoto
			Naming.rebind("Servidor", cliente);
			Naming.rebind("Callback", servidor);
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
	}
}