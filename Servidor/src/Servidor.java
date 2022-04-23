package Servidor;

import java.io.*;
import java.net.*;
import java.util.*;
import java.rmi.*;

public class Servidor {
		
	public static void main(String [] args) {
		// instalar um gestor de seguranca
    	System.setSecurityManager(new SecurityManager());
    	
    	try {
			// inicializar a execucao do registo no porto desejado
			java.rmi.registry.LocateRegistry.createRegistry(1099);
    		System.out.println("Registo RMI pronto.");
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
    	
    	// Create the list of threads where each client will be added
    	ArrayList<Interface> userThreads = new ArrayList<Interface>();
    	
		while(true) {
			try {
				
	    		// instanciar objeto remoto
				Interface cliente = new Implementacao();
	    		
	    		// call the daemon to generate the thread
	    		userThreads.add(cliente);
	    		
	    	} catch (Exception e) {
	    		System.out.println(e.getMessage());
	    	}
		}
    }
}