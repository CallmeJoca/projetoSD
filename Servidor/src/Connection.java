import java.io.*;
import java.net.*;

public class Connection extends Thread{
	
	//placeholder code to be replaced with remote objects
	
	private Socket connectionSocket;
	
	public Connection(Socket connectionSocket) {
		super();
		this.connectionSocket = connectionSocket;
		start();
	}
	
	public void run() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream (connectionSocket.getOutputStream());
			oos.writeObject("stuff");
			oos.flush();
		}
		catch (Exception E) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	
}