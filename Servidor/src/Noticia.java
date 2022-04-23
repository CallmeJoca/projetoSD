package Servidor;

import java.io.Serializable;

public class Noticia implements Serializable {
    // para poder ser escrita num ficheiro, a classe tem de implementar a interface Serializable
    private String topico;
    private String produtor;
    private char [] texto;
    private int diaPublicacao;
    private int mesPublicacao;
    private int anoPublicacao;

    public Noticia () {
        topico = "";
        produtor = "";
        texto = new char [180];
        diaPublicacao = 0;
        mesPublicacao = 0;
        anoPublicacao = 0;
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

    public int getDiaPublicacao() {
        return diaPublicacao;
    }

    public void setDiaPublicacao(int diaPublicacao) {
        this.diaPublicacao = diaPublicacao;
    }

    public int getMesPublicacao() {
        return mesPublicacao;
    }

    public void setMesPublicacao(int mesPublicacao) {
        this.mesPublicacao = mesPublicacao;
    }
    
    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
}
