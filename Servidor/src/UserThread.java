import java.rmi.*;

public class UserThread extends Thread{
	
	Interface cliente;
	
	public UserThread(Interface cliente) {
		super();
		this.cliente = cliente;
		start();
	}
	
	@Override
	public void run() {
		//System.out.println("Objeto remoto pronto.");
	}
}