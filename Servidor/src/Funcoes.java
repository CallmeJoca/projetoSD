import java.io.*;
import java.util.*;

public class Funcoes {
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
    
    // ler um caratere a partir de uma String
    public static char lerCaratere () {
        while (true) {
            try {
                return lerString().charAt(0);
            }
            catch (Exception e) {
                System.out.println ("Não é um caratere válido.");
            }
        }
    }

    // abrir os ficheiros com os registos de utilizadores
    public static ArrayList <Utilizador> abrirFicheiroUtilizadores (ArrayList <Utilizador> utilizadores) {
        // abrir o ficheiro com os dados dos utilizadores já registados
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("utilizadores.txt"));
            utilizadores = (ArrayList <Utilizador>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return utilizadores;
    }
    
    // abrir os ficheiros com os registos de tópicos
    public static ArrayList <String> abrirFicheiroTopicos (ArrayList <String> topicos) {
    	// abrir o ficheiro com os tópicos já registados
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("topicos.txt"));
            topicos = (ArrayList <String>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return topicos;
    }
    
    // abrir os ficheiros com os registos de notícias
    public static ArrayList <Noticia> abrirFicheiroNoticias (ArrayList <Noticia> noticias) {
        // abrir o ficheiro com as notícias já registadas
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("noticias.txt"));
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
        // verificar se o nome de utilizador já existe nos registos
        for (int i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getNome().equals(nome)) {
                System.out.println("Já existe um utilizador com esse nome");
                return false;
            }
        }
        // palavra-passe
        System.out.println("Introduza a palavra-passe: ");
        passe = lerString();
        // tipo de cliente
        System.out.println("Introduza o tipo de cliente (Produtor/Consumidor): ");
        tipo = lerString();

        // adicionar as características ao objeto do tipo Utilizador
        utilizador.setNome(nome);
        utilizador.setPasse(passe);
        utilizador.setTipo(tipo);

        // adicionar o novo utilizador ao ArrayList
        utilizadores.add(utilizador);

        // atualizar o ficheiro que contém os registos dos utilizadores e fechá-lo
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
            	// atribuir os valores obtidos na posição encontrada ao objeto do tipo Utilizador para que estes possam ser usados na classe Cliente
                utilizador.setNome(utilizadores.get(i).getNome());
                utilizador.setPasse(utilizadores.get(i).getPasse());
                utilizador.setTipo(utilizadores.get(i).getTipo());
                System.out.println("Utilizador autenticado");
                return true;
            }
        }
        // passou o ciclo for sem retornar, logo, o utilizador não existe
        System.out.println("Utilizador não encontrado");
        return false;
    }
}