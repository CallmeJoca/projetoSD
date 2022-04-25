import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class FuncoesBackup {

	private static final String FICHEIRO_DE_ARQUIVO_DE_NOTICIAS = "arquivoNoticias.txt";
	private static final String IP   = "127.0.0.1";
	private static final int    PORT = 1100;

    // abrir os ficheiros com os registos de noticias
    @SuppressWarnings("unchecked")
	public static ArrayList <Noticia> abrirFicheiroNoticias (ArrayList <Noticia> noticias) {
        // abrir o ficheiro com as noticias ja registadas
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_DE_ARQUIVO_DE_NOTICIAS));
            noticias = (ArrayList <Noticia>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return noticias;
    }
  
    //check this later
    public static void receberNoticias(ArrayList <Noticia> noticias){
    	ServerSocket ss;
    	try {
            ss = new ServerSocket (PORT);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    	//ArrayList<Noticia> metade = new ArrayList<Noticia>();
    	Socket servidorBackup;
		try {
			servidorBackup = new Socket(IP, PORT);
			ObjectInputStream lerServidorBackup = new ObjectInputStream(servidorBackup.getInputStream());
			@SuppressWarnings("unchecked")
			ArrayList<Noticia> metade = (ArrayList <Noticia>) lerServidorBackup.readObject();
			
			//write metade to file
			ObjectOutputStream arquivarNoticias = new ObjectOutputStream(new FileOutputStream(FICHEIRO_DE_ARQUIVO_DE_NOTICIAS));
			arquivarNoticias.writeObject(metade);
			arquivarNoticias.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
    
}