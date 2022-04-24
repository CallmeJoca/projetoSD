import java.io.*;
import java.net.*;
import java.util.*;
import java.rmi.*;

public class UserThread extends Thread{
	
	Interface cliente;
	
	public UserThread(String nome, Interface cliente) {
		super(nome);
		this.cliente = cliente;
		start();
	}
	
	@Override
	public void run() {
		//Main function of the server
		try {
			Naming.rebind(this.getName(), cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println("Objeto remoto pronto.");
		
	}
}