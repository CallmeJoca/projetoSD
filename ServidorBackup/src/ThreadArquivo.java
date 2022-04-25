import java.util.ArrayList;


public class ThreadArquivo extends Thread{
	
	public ThreadArquivo() {
		super();
		start();
	}
	
	public void run() {
		
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();
		
		while(true) {
			FuncoesBackup.receberNoticias(noticias);
		}
		
		
		
	}
	
}