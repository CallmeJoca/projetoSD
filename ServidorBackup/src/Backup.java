import java.util.*;
import java.rmi.*;

public class Backup {
		
	public static void main(String [] args) {
		
		// instalar um gestor de seguranca
    	System.setSecurityManager(new SecurityManager());
    	System.out.println("Servidor Backup a Inicializar\n");
    	
    	try {
			// inicializar a execucao do registo no porto desejado
			java.rmi.registry.LocateRegistry.createRegistry(1099);
    		System.out.println("Registo RMI pronto.");
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
    	
    	// Create the list of threads where each client will be added
    	ArrayList<Interface> userThreads = new ArrayList<Interface>();
    	
    	//ThreadGroup user_threads = new ThreadGroup();
    	
		while(true) {
			try {
				
	    		// instanciar objeto remoto
				Interface cliente = new Implementacao();
	    		
	    		// call the daemon to generate the thread
	    		userThreads.add(cliente);
	    		
	    	} catch (Exception e) {
	    		System.out.println(e.getMessage());
	    	}
		}
    }
}