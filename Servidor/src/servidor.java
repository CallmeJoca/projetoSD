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
    		InterfaceProdutor produtor = new ImplementacaoProdutor();
    		InterfaceConsumidor consumidor = new ImplementacaoConsumidor();
    		// registar o objeto remoto do produtor no Registry
    		Naming.rebind("Produtor", produtor);
    		System.out.println("Objeto remoto do Produtor pronto.");
    		// registar o objeto remoto do cliente no Registry
    		Naming.rebind("Consumidor", consumidor);
    		System.out.println("Objeto remoto do Consumidor pronto.");
    	} catch (MalformedURLException | RemoteException e) {
    		System.out.println(e.getMessage());
    	}
    }
}
