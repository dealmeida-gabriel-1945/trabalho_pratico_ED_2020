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

    //deep copy de cada pilha de travessa
    public static ArrayList<PilhaContainer> clonaTravessas(ArrayList<PilhaContainer> travessasOriginais) {
        ArrayList<PilhaContainer> clones = new ArrayList<>();
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            clones.add(PilhaContainer.clona(travessasOriginais.get(i)));
        }
        return clones;
    }

    //deep copy de cada pilha de container
    public static ArrayList<PilhaContainer> clonaPilhaContainer(ArrayList<PilhaContainer> travessasOriginais) {
        ArrayList<PilhaContainer> clones = new ArrayList<>();
        for (int i = 0; i < Constantes.MAX_QTD_PILHA_DE_CONTAINERS; i++) {
            clones.add(PilhaContainer.clona(travessasOriginais.get(i)));
        }
        return clones;
    }
    //deep copy de cada pilha de container
    public static PilhaContainer clona(PilhaContainer travessaOriginal) {
        PilhaContainer clone = new PilhaContainer();
        clone.topo = travessaOriginal.topo;
        return clone;
    }

    public int empilha(Container dados){
        //criando novo elemento para inserir
        ElementoContainer elementoContainer = new ElementoContainer(dados);
        //o topo da pilha é linkado ao novo elemento
        elementoContainer.proximo = this.topo;
        //seta o próximo item como o topo da pilha
        this.topo = elementoContainer;
        return 1;
    }

    public ElementoContainer desempilha(){
        //se a pilha for vazia, nada é feito
        if(this.topo == null){
            return null;
        }
        //variavel auxiliar
        ElementoContainer elm = this.topo;
        //o topo agora é o próximo elemento linkado à variável auxiliar
        this.topo = elm.proximo;
        return elm;
    }

    //se o topo da pilha for nulo, ela está vazia
    public Boolean vazia(){
        return Objects.isNull(this.topo);
    }


    public Long quantidade(){
        //clona a si próprio
        PilhaContainer pilhaContainer = new PilhaContainer(this);
        //se ela for vazia, retorna zero
        if(pilhaContainer.vazia()){
            return 0L;
        }else{
            //realiza a contagem de elementos ao desempilhar cada elemento da pilha
            Long i = 0L;
            for (; !pilhaContainer.vazia(); i++){
                pilhaContainer.desempilha();
            }
            return i;
        }
    }
}
