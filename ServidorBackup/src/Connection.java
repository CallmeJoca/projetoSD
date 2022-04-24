import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Connection extends Thread {
	private Socket S;
	private ArrayList<Noticia> n;
	public Connection(Socket s) {
		super();
		S=s;
		start();
	}
	public void run() {
		ArrayList<Noticia> n=new ArrayList<Noticia>();
		try {
			ObjectOutputStream os=new ObjectOutputStream(S.getOutputStream());
			os.writeObject("Noticias em arquivo:"+n.toString());
			os.flush();			
			
		}
		catch(IOException e){}
	}
}
