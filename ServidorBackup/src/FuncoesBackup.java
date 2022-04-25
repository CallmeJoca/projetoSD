import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class FuncoesBackup {
	
	private static final String FICHEIRO_DE_ARQUIVO_DE_NOTICIAS     = "arquivoNoticias.txt";
	private static final String FICHEIRO_DE_ARQUIVO_DE_TOPICOS      = "arquivoTopicos.txt";
	private static final String FICHEIRO_DE_ARQUIVO_DE_UTILIZADORES = "arquivoUtilizadores.txt";
	private static final String IP   = "127.0.0.1";
	private static final int    PORT = 2222;
	
	// ler uma String a partir do teclado
    public static String lerString () {
        String s = "";
        try {
            BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
            s = in.readLine ();
        } catch (IOException e) {
            System.out.println (e.getMessage());
        }
        return s;
    }
    
    // ler um inteiro a partir de uma String
    public static int lerInteiro () {
        while (true) {
            try {
                return Integer.valueOf (lerString().trim()).intValue();
            } catch (Exception e) {
                System.out.println (e.getMessage());
            }
        }
    }

    // abrir os ficheiros com os registos de utilizadores
    @SuppressWarnings("unchecked")
	public static ArrayList <Utilizador> abrirFicheiroUtilizadores (ArrayList <Utilizador> utilizadores) {
        // abrir o ficheiro com os dados dos utilizadores ja registados
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_DE_ARQUIVO_DE_UTILIZADORES));
            utilizadores = (ArrayList <Utilizador>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return utilizadores;
    }
    
    // abrir os ficheiros com os registos de t�picos
    @SuppressWarnings("unchecked")
	public static ArrayList <String> abrirFicheiroTopicos (ArrayList <String> topicos) {
    	// abrir o ficheiro com os t�picos j� registados
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_DE_ARQUIVO_DE_TOPICOS));
            topicos = (ArrayList <String>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return topicos;
    }
    
    // abrir os ficheiros com os registos de not�cias
    @SuppressWarnings("unchecked")
	public static ArrayList <Noticia> abrirFicheiroNoticias (ArrayList <Noticia> noticias) {
        // abrir o ficheiro com as not�cias j� registadas
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_DE_ARQUIVO_DE_NOTICIAS));
            noticias = (ArrayList <Noticia>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return noticias;
    }

    // criar um novo utilizador (aka registo)
    public static boolean criarUtilizador (ArrayList <Utilizador> utilizadores, Utilizador utilizador) {
        String nome, passe, tipo;
        // nome
        System.out.println("Introduza o nome de utilizador: ");
        nome = lerString();
        // verificar se o nome de utilizador j� existe nos registos
        for (int i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getNome().equals(nome)) {
                System.out.println("Ja existe um utilizador com esse nome");
                return false;
            }
        }
        // palavra-passe
        System.out.println("Introduza a palavra-passe: ");
        passe = lerString();
        // tipo de cliente
        System.out.println("Introduza o tipo de cliente (Produtor/Consumidor): ");
        tipo = lerString();

        // adicionar as caracter�sticas ao objeto do tipo Utilizador
        utilizador.setNome(nome);
        utilizador.setPasse(passe);
        utilizador.setTipo(tipo);

        // adicionar o novo utilizador ao ArrayList
        utilizadores.add(utilizador);

        // atualizar o ficheiro que cont�m os registos dos utilizadores e fech�-lo
        try {
            ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(FICHEIRO_DE_ARQUIVO_DE_UTILIZADORES));
            oos.writeObject(utilizadores);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    // verificar o utilizador (aka login)
    public static boolean verificarUtilizador (ArrayList <Utilizador> utilizadores, Utilizador utilizador) {
        String nome, passe;
        // nome
        System.out.println("Introduza o nome de utilizador: ");
        nome = lerString();
        // palavra-passe
        System.out.println("Introduza a palavra-passe: ");
        passe = lerString();
        // verificar se o utilizador existe nos registos
        for (int i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getNome().equals(nome) && utilizadores.get(i).getPasse().equals(passe)) {
            	// atribuir os valores obtidos na posi��o encontrada ao objeto do tipo Utilizador para que estes possam ser usados na classe Cliente
                utilizador.setNome(utilizadores.get(i).getNome());
                utilizador.setPasse(utilizadores.get(i).getPasse());
                utilizador.setTipo(utilizadores.get(i).getTipo());
                utilizador.setSubscricoes(utilizadores.get(i).getSubscricoes());
                System.out.println("Utilizador autenticado");
                return true;
            }
        }
        // passou o ciclo for sem retornar, logo, o utilizador n�o existe
        System.out.println("Utilizador nao encontrado");
        return false;
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