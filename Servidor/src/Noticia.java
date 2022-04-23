import java.io.Serializable;
import java.util.Calendar;

public class Noticia implements Serializable {
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
    
    public String toString() {
    	String text_holder, day_holder, month_holder, year_holder;
    	text_holder = (String) this.texto;
    	day_holder = (String) this.diaPublicacao;
    	month_holder = (String) this.mesPublicacao;
    	year_holder = (String) this.anoPublicacao;
    	return(""+this.topico+"\n"+this.produtor+"\n"+this.text_holder+"\n"+this.day_holder+"\n"+this.month_holder+"\n"+this.year_holder+"\n");
    }
}