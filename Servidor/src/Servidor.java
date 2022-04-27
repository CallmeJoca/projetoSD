import java.util.*;
import java.rmi.*;

public class Servidor {
		
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
			UserThread clientThread;
			// instanciar objeto remoto
			Interface cliente = new Implementacao();
			// registar o objeto remoto
			Naming.rebind("Servidor", cliente);
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
	}
}