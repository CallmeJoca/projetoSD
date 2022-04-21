import java.net.MalformedURLException;
import java.io.*;
import java.net.*;

public class Servidor {
		
	public static void main(String [] args) {
		// instalar um gestor de seguran\ufffda
    	System.setSecurityManager(new SecurityManager());
    	
    	try {
			// inicializar a execu��o do registo no porto desejado
			java.rmi.registry.LocateRegistry.createRegistry(1099);
    		System.out.println("Registo RMI pronto.");
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
    	
    	// Create the list of threads where each client will be added
    	ArrayList<Daemon> userThreads = new ArrayList<Daemon>();
    	
		while(true) {
			try {
				
	    		// instanciar objeto remoto
				Interface cliente = new Implementacao();
	    		
	    		// call the daemon to generate the thread
	    		userThreads.add(cliente);
	    		
	    	} catch (MalformedURLException | RemoteException e) {
	    		System.out.println(e.getMessage());
	    	}
		}
    }
}