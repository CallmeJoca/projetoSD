import java.io.Serializable;
import java.util.ArrayList;

public class Utilizador implements Serializable {
    // para poder ser escrita num ficheiro, a classe tem de implementar a interface Serializable
    private String nome;
    private String passe;
    private String tipo;
    private ArrayList <String> subscricoes;

    public Utilizador () {
        nome = "";
        passe = "";
        tipo = "";
        subscricoes = new ArrayList <String> ();
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getNome () {
        return nome;
    }

    public void setPasse (String passe) {
        this.passe = passe;
    }

    public String getPasse () {
        return passe;
    }

    public void setTipo (String tipo) {
        this.tipo = tipo;
    }

    public String getTipo () {
        return tipo;
    }

    public void setSubscricoes (ArrayList <String> subscricoes) {
    	this.subscricoes = subscricoes;
    }

    public ArrayList <String> getSubscricoes () {
    	return subscricoes;
    }

    @Override
	public String toString() {
		return "Utilizador [Nome: " + nome + ", Pass: " + passe + ", Tipo: " + tipo + ", Subscricoes: " + subscricoes + "]\n";
	}
}
