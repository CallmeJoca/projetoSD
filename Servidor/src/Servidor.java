import java.util.*;
import java.rmi.*;

public class Servidor {
	
	private static int counter = 0;
		
	public static void main(String [] args) {
		// instalar um gestor de seguranca
    	System.setSecurityManager(new SecurityManager());
    	
    	try {
			// inicializar a execucao do registo no porto desejado
			java.rmi.registry.LocateRegistry.createRegistry(1099);
    		System.out.println("Registo RMI pronto.");
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
    	
    	ThreadArquivar ta = new ThreadArquivar();
    	
    	// Create the list of threads where each client will be added
    	ArrayList<UserThread> userThreads = new ArrayList<UserThread>();
		
		try {
			UserThread clientThread;
			Interface cliente = new Implementacao();
			Naming.rebind("Servidor", cliente);
			while (true) {
				clientThread = new UserThread(cliente);
				userThreads.add(clientThread);
			}
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
	}
}