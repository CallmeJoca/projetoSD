import java.util.*;
import java.rmi.*;

public class Servidor {
		
	@SuppressWarnings({ "removal", "deprecation" })
	public static void main(String [] args) {
		// instalar um gestor de seguranca
    	System.setSecurityManager(new SecurityManager());
    	System.out.println("Servidor Principal a Inicializar\n");
    	int counter = 0;
    	
    	try {
			// inicializar a execucao do registo no porto desejado
			java.rmi.registry.LocateRegistry.createRegistry(1099);
    		System.out.println("Registo RMI pronto.");
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
    	
    	// Create the list of threads where each client will be added
    	ArrayList<UserThread> userThreads = new ArrayList<UserThread>();
		
		try {
			UserThread clientThread;
			// instanciar objeto remoto
			Interface cliente = new Implementacao();
			// registar o objeto remoto
			Naming.rebind("Servidor", cliente);
			while (true) {
				// adicionar o cliente conectado a uma nova thread
				clientThread = new UserThread(cliente);
				// adicionar a thread a um ArrayList de threads para se saber os clientes ativos
				userThreads.add(clientThread);
			}
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
	}
}