public class produtor {
    
    String nome;
    int id;

    produtor(){
        this.nome = "teste";
        this.id = "0";
    }

    produtor(String nome, int id){
        this.nome = nome;
        this.id = id;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ("O nome do produtor é" + this.nome + "e o seu ID é " + this.id + "\n");
    }

}
