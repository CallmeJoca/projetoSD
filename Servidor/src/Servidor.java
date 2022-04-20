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
				
				
	    		// instanciar objeto remoto do Produtor
	    		InterfaceProdutor produtor = new ImplementacaoProdutor();
	    		InterfaceConsumidor consumidor = new ImplementacaoConsumidor();
	    		
	    		// call the daemon to generate the thread
	    		
	    		userThreads.add();
	    		
	    		
	    		
	    		// (move the lower code into the daemon)
	    		
	    		// registar o objeto remoto do produtor no Registry
	    		Naming.rebind("Produtor", produtor);
	    		System.out.println("Objeto remoto do Produtor pronto.");
	    		
	    		// registar o objeto remoto do consumidor no Registry
	    		Naming.rebind("Consumidor", consumidor);
	    		System.out.println("Objeto remoto do Consumidor pronto.");
	    		
	    		// 
	    		
	    		
	    	} catch (MalformedURLException | RemoteException e) {
	    		System.out.println(e.getMessage());
	    	}
		}
    }
}
