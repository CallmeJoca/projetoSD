public class Daemon extends Thread{
	
	Interface cliente;
	
	public Daemon(Interface cliente) {
		super();
		this.cliente = cliente;
		start();
	}
	
	@Override
	public void run() {
		//Main function of the server
		
		Naming.rebind("Cliente", cliente);
		System.out.println("Objeto remoto pronto.");
	}
	
}