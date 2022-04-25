import java.util.*;
import java.rmi.*;

public class Backup {
		
	public static void main(String [] args) {
		
		// instalar um gestor de seguranca
    	System.setSecurityManager(new SecurityManager());
    	System.out.println("Servidor Backup a Inicializar\n");
    	
    	try {
			// inicializar a execucao do registo no porto desejado
			java.rmi.registry.LocateRegistry.createRegistry(1100);
    		System.out.println("Registo RMI pronto.");
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
    	
    	ThreadArquivar ta = new ThreadArquivar();
    	
    	// Create the list of threads where each client will be added
    	ArrayList<ThreadArquivar> userThreads = new ArrayList<ThreadArquivar>();
    	
		try {
			ThreadArquivar clientThread;
    		// instanciar objeto remoto
			Interface cliente = new Implementacao();
			// registar o objeto remoto
    		Naming.rebind("Backup", cliente);
    		
    		while (true) {
    			// adicionar o cliente conectado a uma nova thread
				clientThread = new ThreadArquivar();
				// adicionar a thread a um ArrayList de threads para se saber os clientes ativos
				userThreads.add(clientThread);
    		}
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
}