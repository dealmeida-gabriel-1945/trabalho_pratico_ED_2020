package main.datashape;

import main.datashape.tads.PilhaContainer;
import main.util.Constantes;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Navio {
    public String nome;
    public Long tempo;
    public int qtdContainer;

    public ArrayList<PilhaContainer> pilhasDeContainers = new ArrayList<PilhaContainer>();


    public Navio(){
        this.pilhasDeContainers = new ArrayList<PilhaContainer>();
        /*mesmo sendo um vetor dinamico, ele será tratado como estático*/
        for (int i = 0; i < Constantes.MAX_QTD_PILHA_DE_CONTAINERS; i++) {
            pilhasDeContainers.add(new PilhaContainer());
        }
    }

    public Navio(int qtdContainer, int numAux){
        this.pilhasDeContainers = new ArrayList<PilhaContainer>();
        /*mesmo sendo um vetor dinamico, ele será tratado como estático*/
        for (int i = 0; i < Constantes.MAX_QTD_PILHA_DE_CONTAINERS; i++) {
            pilhasDeContainers.add(new PilhaContainer());
        }
        //cria um nome de acordo com o horário atual (devido à velocidade de processamento, pode ocorrer a aparição de nomes repetidos)
        this.nome = "Navio " + LocalDateTime.now() + " " + (numAux + 1);
        //seta quantidade de containers
        this.qtdContainer = qtdContainer;
        //seta o tempo de espera como zero
        this.tempo = 0L;

        //é distribuido, para as pilhas de continers, a quantidade de containers gerada.
        int i = 0, aux = qtdContainer, negativas = 0;
        while((aux < 16) && (aux > 0)){
            this.pilhasDeContainers.get(i).empilha(new Container(String.valueOf(LocalDateTime.now())));
            aux--;
            negativas++;
            if(negativas == Constantes.MAX_QTD_CONTAINERS_POR_PILHA){
                i++;
                negativas = 0;
            }
        }
    }

    //deep copy do navio
    public static Navio clone(Navio navioOriginal) {
        Navio clone = new Navio();
        clone.tempo = navioOriginal.tempo;
        clone.nome = navioOriginal.nome;
        clone.qtdContainer = navioOriginal.qtdContainer;
        clone.pilhasDeContainers = PilhaContainer.clonaPilhaContainer(navioOriginal.pilhasDeContainers);
        return clone;
    }

    //gera o log da saída do anvio
    public String generateLog(){
        String str1 = "Nome:" + this.nome;
        String str2 = "Tempo de espera:" + this.tempo;
        String str3 = "Quantidade de containers:" + this.qtdContainer;
        String str4 = "--------------------------------------------------------------\n";
        return String.join("\n", str1, str2, str3, str4);
    }
}
