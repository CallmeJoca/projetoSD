import java.io.*;
import java.util.*;
import java.net.*;

public class Funcoes {
	private static final String FICHEIRO_DE_NOTICIAS = "noticias.txt";
	private static final String FICHEIRO_DE_TOPICOS  = "topicos.txt";
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
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("utilizadores.txt"));
            utilizadores = (ArrayList <Utilizador>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return utilizadores;
    }

	// escrever/guardar os utilizadores para o ficheiro
    public static void escreverFicheiroUtilizadores(ArrayList <Utilizador> utilizadores) {
    	try {
			ObjectOutputStream tOUT = new ObjectOutputStream(new FileOutputStream("utilizadores.txt"));
			tOUT.writeObject(utilizadores);
			tOUT.close();
		} catch(IOException e) {e.printStackTrace(); }
    }

    // manter a lista de Utilizadores atualizada com o User, depois de ser alterado as suas subscricoes
    public static ArrayList <Utilizador> setSubscricoesUtlizador(ArrayList <Utilizador> utilizadores, Utilizador utilizador) {
    	for (int i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getNome().equals(utilizador.getNome()) && utilizadores.get(i).getPasse().equals(utilizador.getPasse())) {
            	utilizadores.get(i).setSubscricoes(utilizador.getSubscricoes());
				break;
            }
        }
    	return utilizadores;
    }

    // abrir os ficheiros com os registos de topicos
    @SuppressWarnings("unchecked")
	public static ArrayList <String> abrirFicheiroTopicos (ArrayList <String> topicos) {
    	// abrir o ficheiro com os topicos ja registados
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_DE_TOPICOS));
            topicos = (ArrayList <String>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return topicos;
    }

    // escrever/guardar para o ficheiro
    public static void escreverFicheiroTopicos (ArrayList <String> topicos) {
    	try {
			ObjectOutputStream tOUT = new ObjectOutputStream(new FileOutputStream(FICHEIRO_DE_TOPICOS));
			tOUT.writeObject(topicos);
			tOUT.close();
		} catch(IOException e) {e.printStackTrace(); }
    }

    // abrir os ficheiros com os registos de noticias
    @SuppressWarnings("unchecked")
	public static ArrayList <Noticia> abrirFicheiroNoticias (ArrayList <Noticia> noticias) {
        // abrir o ficheiro com as noticias ja registadas
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHEIRO_DE_NOTICIAS));
            noticias = (ArrayList <Noticia>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return noticias;
    }

    // escrever/guardar as noticias para o ficheiro
    public static void escreverFicheiroNoticias (ArrayList <Noticia> noticias) {
    	try {
			ObjectOutputStream tOUT = new ObjectOutputStream(new FileOutputStream(FICHEIRO_DE_NOTICIAS));
			tOUT.writeObject(noticias);
			tOUT.close();
		} catch(IOException e) {e.printStackTrace(); }
    }

    // criar um novo utilizador (aka registo)
    public static boolean criarUtilizador (ArrayList <Utilizador> utilizadores, Utilizador utilizador) {
        String nome, passe, tipo;
        // nome
        System.out.println("Introduza o nome de utilizador: ");
        nome = lerString();
        // verificar se o nome de utilizador ja existe nos registos
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

        // adicionar as caracteristicas ao objeto do tipo Utilizador
        utilizador.setNome(nome);
        utilizador.setPasse(passe);
        utilizador.setTipo(tipo);

        // adicionar o novo utilizador ao ArrayList
        utilizadores.add(utilizador);

        // atualizar o ficheiro que contem os registos dos utilizadores e fecha-lo
        try {
            ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream("utilizadores.txt"));
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
            	// atribuir os valores obtidos na posicao encontrada ao objeto do tipo Utilizador para que estes possam ser usados na classe Cliente
                utilizador.setNome(utilizadores.get(i).getNome());
                utilizador.setPasse(utilizadores.get(i).getPasse());
                utilizador.setTipo(utilizadores.get(i).getTipo());
                utilizador.setSubscricoes(utilizadores.get(i).getSubscricoes());
                System.out.println("Utilizador autenticado");
                return true;
            }
        }
        // passou o ciclo for sem retornar, logo, o utilizador nao existe
        System.out.println("Utilizador nao encontrado");
        return false;
    }

    public static void arquivarNoticias(ArrayList <Noticia> noticias){

    	ArrayList<Noticia> metade = (ArrayList<Noticia>) noticias.subList(0, noticias.size()/2);
    	Socket servidorBackup;
		try {
			servidorBackup = new Socket(IP, PORT);
			ObjectOutputStream escreverServidorBackup = new ObjectOutputStream(servidorBackup.getOutputStream());
			escreverServidorBackup.writeObject(metade);
			System.out.println("Arquivado com sucesso");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

    }

}
