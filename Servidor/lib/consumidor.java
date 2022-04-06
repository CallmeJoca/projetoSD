public class consumidor {
    
    String nome;
    int id;

    consumidor(){
        this.nome = "Test Consumer";
        this.id = 0;
    }

    consumidor(String nome, int id){
        this.nome = nome;
        this.id = id;
    }

    @Override
    public String toString(){
        return ("O nome do consumidor é" + this.nome + "e o seu ID é " + this.id + "\n");
    }

}
