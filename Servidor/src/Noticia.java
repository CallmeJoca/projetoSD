import java.io.Serializable;
import java.util.Calendar;

public class Noticia implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	// para poder ser escrita num ficheiro, a classe tem de implementar a interface Serializable
    private String topico;
    private String produtor;
    private char [] texto;
    private Calendar data;

    public Noticia () {
        topico = "";
        produtor = "";
        texto = new char [180];
        data = Calendar.getInstance();
    }

    public String getTopico() {
        return topico;
    }

    public void setTopico(String topico) {
        this.topico = topico;
    }

    public String getProdutor() {
        return produtor;
    }

    public void setProdutor(String produtor) {
        this.produtor = produtor;
    }

    public char[] getTexto() {
        return texto;
    }

    public void setTexto(char[] texto) {
        this.texto = texto;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    @Override
	public String toString() {
        return "Noticia [Topico: " + topico + ", Produtor: " + produtor + ", Data: " + data.get(data.DAY_OF_MONTH) + "/" + (data.get(data.MONTH) + 1) + "/"  + data.get(data.YEAR) + "]\n";
	}
}
