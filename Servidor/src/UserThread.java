import java.rmi.*;

public class UserThread extends Thread{
	
	Interface cliente;
	
	public UserThread(String nome) {
		super(nome);
		start();
	}
	
	@Override
	public void run() {
		//Main function of the server
		try {
			Interface cliente = new Implementacao();
			
			
			Naming.rebind(this.getName(), cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println("Objeto remoto pronto.");
		
	}
}