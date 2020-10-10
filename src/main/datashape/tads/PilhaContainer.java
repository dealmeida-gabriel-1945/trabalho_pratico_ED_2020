package main.datashape.tads;

import main.datashape.AreaAtracamento;
import main.datashape.Container;
import main.datashape.elementos.ElementoContainer;
import main.util.Constantes;

import java.util.ArrayList;
import java.util.Objects;

public class PilhaContainer {
    public ElementoContainer topo = null;

    public PilhaContainer(){}

    public PilhaContainer(PilhaContainer pilhaContainer){
        this.topo = pilhaContainer.topo;
    }

    public static ArrayList<PilhaContainer> clonaTravessas(ArrayList<PilhaContainer> travessasOriginais) {
        ArrayList<PilhaContainer> clones = new ArrayList<>();
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            clones.add(PilhaContainer.clona(travessasOriginais.get(i)));
        }
        return clones;
    }

    public static ArrayList<PilhaContainer> clonaPilhaContainer(ArrayList<PilhaContainer> travessasOriginais) {
        ArrayList<PilhaContainer> clones = new ArrayList<>();
        for (int i = 0; i < Constantes.MAX_QTD_PILHA_DE_CONTAINERS; i++) {
            clones.add(PilhaContainer.clona(travessasOriginais.get(i)));
        }
        return clones;
    }

    public static PilhaContainer clona(PilhaContainer travessaOriginal) {
        PilhaContainer clone = new PilhaContainer();
        clone.topo = travessaOriginal.topo;
        return clone;
    }

    public int empilha(Container dados){
        ElementoContainer elementoContainer = new ElementoContainer(dados);
        elementoContainer.proximo = this.topo;
        this.topo = elementoContainer;
        return 1;
    }

    public ElementoContainer desempilha(){
        if(this.topo == null){
            return null;
        }
        ElementoContainer elm = this.topo;
        this.topo = elm.proximo;
        return elm;
    }

    public Boolean vazia(){
        return Objects.isNull(this.topo);
    }


    public Long quantidade(){
        PilhaContainer pilhaContainer = new PilhaContainer(this);
        if(pilhaContainer.vazia()){
            return 0L;
        }else{
            Long i = 0L;
            for (; !pilhaContainer.vazia(); i++){
                pilhaContainer.desempilha();
            }
            return i;
        }
    }
}
