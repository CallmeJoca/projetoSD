import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

// interface do servidor
public interface Interface extends Remote {
	// metodos de login / registo
	public Utilizador criarUtilizador (Utilizador utilizador) throws RemoteException;
	public Utilizador verificarUtilizador (Utilizador utilizador) throws RemoteException;

	// metodos para o cliente Produtor
	public ArrayList <String> AdicionarTopico (String topico) throws RemoteException;
	public ArrayList <String> VerTopicos () throws RemoteException;
	public boolean VerificarTopico (String topico) throws RemoteException;
	public ArrayList <Noticia> InserirNoticia (String topico, String produtor, Calendar publicacao, String texto) throws RemoteException;
	public ArrayList <Noticia> ConsultarNoticias (String produtor) throws RemoteException;
    
	// metodos para o cliente Consumidor
	public ArrayList <String> SubscreverTopico (String topico, Utilizador user) throws RemoteException;
	public ArrayList <Noticia> ConsultarNoticiasTopico (String topico, Calendar inicio, Calendar fim) throws RemoteException;
	public Noticia ConsultarUltimaNoticia (String topico) throws RemoteException;
}