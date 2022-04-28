import java.io.*;
import java.util.ArrayList;

public class ThreadArquivar extends Thread {

	private static final String CONFIG = "C:\\Users\\sarak\\Documents\\GitHub\\projetoSD\\Servidor\\src\\config.txt";
	private int HALFSIZE;

	public ThreadArquivar() {
		super();
		start();
	}

	public void run() {
		
		System.out.println("A começar thread de arquivo de noticias");

		ArrayList<Noticia> noticias = new ArrayList<Noticia>();
		ArrayList<String>  topicos  = new ArrayList<String>();
		ArrayList<Noticia> arquivar = new ArrayList<Noticia>();

		noticias = Funcoes.abrirFicheiroNoticias(noticias);
		topicos = Funcoes.abrirFicheiroTopicos(topicos);
		int topicCounter[] = new int[topicos.size()];
		int totalNews = noticias.size();
		int half = 0;

		try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CONFIG));
            HALFSIZE = (int) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Erro de servidor: " +e.getMessage());
        }
		while(true) {

			//While cicle to hold on until there are new news
			while(true) {
				noticias = Funcoes.abrirFicheiroNoticias(noticias);
				if(totalNews == noticias.size()) {
					System.out.println("Nova noticia inserida");
					break;
				}
			}
			//update total number of news
			totalNews = noticias.size();
			System.out.println("A começar a contar noticias no ficheiro");
			// count number of topics existing
			for(int i = 0; i < topicos.size();i++) {
				for(int j = 0; j < noticias.size(); j++) {
					if (noticias.get(j).getTopico().equals(topicos.get(i))){
						topicCounter[i]++;
					}
				}

				//save half the news of a given topic to a list to be sent to the backup
				System.out.println("A verificar se numero de noticias de um certo topico é suficiente para arquivar");
				if (topicCounter[i] >= HALFSIZE) {
						for(int k = 0; k < noticias.size(); k++) {
							if (noticias.get(k).getTopico().equals(topicos.get(i))){
								arquivar.add(noticias.get(k));
								half++;
							}
							if(half >= (topicCounter[i] / 2))
								half = 0;
								break;
						}

						for(int l = 0; l < arquivar.size(); l++) {
							noticias.remove(arquivar.get(l));
						}
						//send news to be archived and clear them from the file
						System.out.println("A rescrever ficheiro de noticias com as noticias arquivadas removidas");
						Funcoes.escreverFicheiroNoticias(noticias);
						System.out.println("A enviar noticias para o servidor");
						Funcoes.arquivarNoticias(arquivar);
						arquivar.removeAll(arquivar);
					}
			}
		}
	}
}
