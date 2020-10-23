package main.datashape;

import main.datashape.tads.PilhaContainer;
import main.util.Constantes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class Navio {
    public String id;
    public Long tempo;
    public int qtdContainer;

    public ArrayList<PilhaContainer> pilhasDeContainers = new ArrayList<>();

    //prepara o navio
    public void prepare(){
        Random rand = new Random();
        this.id = LocalDateTime.now().toString() + Constantes.NOME_PORTO + rand.nextInt();
        this.tempo = 0L;
        this.pilhasDeContainers = new ArrayList<>();
        for (int i = 0; i < Constantes.MAX_QTD_PILHA_DE_CONTAINERS; i++) {
            PilhaContainer pilha = new PilhaContainer();
            pilha.prepare();
            this.pilhasDeContainers.add(pilha);
        }
    }

    public void popula() {//popula o navio
        Random rand = new Random();
        //gera de 4 a 16 containers no vaio
        this.qtdContainer = rand.nextInt(12) + 4;
        //controlador de qual pilha será feita a inserção
        int pilha = 0;
        for (int i = 0; i < qtdContainer; i++) {
            //instancia o novo container
            Container newContainer = new Container();
            //prepara o container
            newContainer.prepare();

            //empilha o novo container
            this.pilhasDeContainers.get(pilha).empilha(newContainer);

            //se o controlador da pilha estiver no ultimo index, retorna-o para 0
            //caso não, adiciona 1 ao valor
            pilha += (pilha == Constantes.MAX_QTD_PILHA_DE_CONTAINERS-1) ? (-pilha) : 1;
        }
    }
}
