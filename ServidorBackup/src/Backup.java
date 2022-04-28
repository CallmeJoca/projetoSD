import java.util.*;
import java.rmi.*;

public class Backup {
		
	private static final int PORT = 1099;
	
	public static void main(String [] args) {
		
		// instalar um gestor de seguranca
    	System.setSecurityManager(new SecurityManager());
    	System.out.println("Servidor Backup a Inicializar\n");
    	
    	try {
			// inicializar a execucao do registo no porto desejado
			java.rmi.registry.LocateRegistry.createRegistry(PORT);
    		System.out.println("Registo RMI pronto.");
		} catch (RemoteException e) {
			System.out.println("ERROR STARTING BACKUP RMI:"+e.getMessage());
		}
    	
    	ThreadArquivo ta = new ThreadArquivo();
     	
		try {
    		// instanciar objeto remoto
			Interface cliente = new Implementacao();
			// registar o objeto remoto
    		Naming.rebind("Backup", cliente);
    		
    	} catch (Exception e) {
    		System.out.println("ERROR STARTING BACKUP INTERFACE"+e.getMessage());
    	}
    }
}