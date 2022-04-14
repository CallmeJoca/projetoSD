import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Servidor {
	public static void main(String [] args) {
        // instalar um gestor de segurança
    	System.setSecurityManager(new SecurityManager());
		try {
			// inicializar a execução do registo no porto desejado
			java.rmi.registry.LocateRegistry.createRegistry(1099);
    		System.out.println("Registo RMI pronto.");
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
    	try {
    		// instanciar objeto remoto do Produtor
    		ClienteInterface objetoRemoto = new ClienteImplementacao();
    		// registar o objeto remoto no Registry
    		Naming.rebind("Servidor", objetoRemoto);
    		System.out.println("Objeto remoto pronto.");
    	} catch (MalformedURLException | RemoteException e) {
    		System.out.println(e.getMessage());
    	}
    }
}
