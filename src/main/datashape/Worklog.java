package main.datashape;

public class Worklog {
    public String conteudo;

    public void prepare(Navio navio){
        String pt1 = "Navio: " + navio.id + ";";
        String pt2 = "Quantidade de tempo esperado pelo navio: " + navio.tempo + " unidade(s) de tempo;";
        String pt3 = "Quantidade de containers inicial: " + navio.qtdContainer + " containers.";
        this.conteudo = String.join("\n", pt1, pt2, pt3);
    }

    public void prepare(Container container){
        this.conteudo = "Container: " + container.serial + ";";
    }

    public void show(){
        System.out.println(this.conteudo + "\n");
    }
}
