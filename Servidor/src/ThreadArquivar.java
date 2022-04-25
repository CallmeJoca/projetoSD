import java.io.*;
import java.util.ArrayList;

public class ThreadArquivar extends Thread {
	
	private static final String CONFIG = "config.txt";
	private int HALFSIZE;
	
	public ThreadArquivar() {
		super();
		start();
	}
	
	public void run() {
		
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();
		
		noticias = Funcoes.abrirFicheiroNoticias(noticias);
		
		topicos = 
		
		try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CONFIG));
            HALFSIZE = (int) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Erro de servidor: " +e.getMessage());
        }
		while(true) {
			
			for(int i = 0; i < noticias.size(); i++) {
				
			}
			
			if(noticias.size() >= HALFSIZE) {
				//Main call of this thread. It's responsible for writing to the remote server
				Funcoes.arquivarNoticias(noticias);
			}
			noticias = Funcoes.abrirFicheiroNoticias(noticias);
		}
	}
}