package Servidor;

import java.io.Serializable;

public class Utilizador implements Serializable {
    // para poder ser escrita num ficheiro, a classe tem de implementar a interface Serializable
    private String nome;
    private String passe;
    private String tipo;

    public Utilizador () {
        nome = "";
        passe = "";
        tipo = "";
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
}