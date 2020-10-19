package main.datashape;

import main.datashape.tads.PilhaContainer;
import main.util.Constantes;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Navio {
    public String nome;
    public Long tempo;
    public boolean trabalhado = false;
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

        this.nome = "Navio " + LocalDateTime.now() + " " + (numAux + 1);
        this.qtdContainer = qtdContainer;
        this.tempo = 0L;

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

    public static void show(Navio navio){
    }

    public static Navio clone(Navio navioOriginal) {
        Navio clone = new Navio();
        clone.tempo = navioOriginal.tempo;
        clone.nome = navioOriginal.nome;
        clone.qtdContainer = navioOriginal.qtdContainer;
        clone.pilhasDeContainers = PilhaContainer.clonaPilhaContainer(navioOriginal.pilhasDeContainers);
        return clone;
    }
}
