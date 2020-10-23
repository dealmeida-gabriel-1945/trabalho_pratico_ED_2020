package main.datashape;

public class Carro {
    public Long viagens;
    public Long tempoDecorrido;

    public void prepare(){
        this.viagens = 0L;
        this.tempoDecorrido = 0L;
    }

    public void show() {
        System.out.println("\t\tQuantidade de viagens: " + this.viagens + ";");
        System.out.println("\t\tQuantidade de tempo decorrido: " + this.tempoDecorrido + ".");
    }
}
