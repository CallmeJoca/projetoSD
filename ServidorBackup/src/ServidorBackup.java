import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorBackup {
	private ServerSocket ss;
	private Socket s;
	private Connection c;
	public ServidorBackup() {
		try {
			ss=new ServerSocket(5432);
			while(true) {
				s=ss.accept();//aceita uma ligaçao pedido cliente
				c=new Connection(s);
			}
		}
		catch(IOException e) {}
		
	}
	public static void main(String args[]) {
		ServidorBackup noticioasArquivo=new ServidorBackup();
	}

}
