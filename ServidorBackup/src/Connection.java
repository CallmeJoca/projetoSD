import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Thread {
	private Socket S;
	private Noticia n;
	public Connection(Socket s) {
		super();
		S=s;
		start();
	}
	public void run() {
		try {
			ObjectOutputStream os=new ObjectOutputStream(S.getOutputStream());
			os.writeObject("Noticias em arquivo:"+n.toString());
			os.flush();
			
		}
		catch(IOException e){}
	}
}
