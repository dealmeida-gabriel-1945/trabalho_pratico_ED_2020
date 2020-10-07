package main.datashape.tads;

import main.datashape.AreaAtracamento;
import main.datashape.Container;
import main.datashape.elementos.ElementoContainer;

import java.util.Objects;

public class PilhaContainer {
    public ElementoContainer topo = null;

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

    public static void show(PilhaContainer pilhaContainer){
        if(pilhaContainer.vazia()){
            System.out.println("\t\t\t Não há containers");
        }else{
            for (int i = 0; !Objects.isNull(pilhaContainer.topo); i++) {
                System.out.println("\t\t\t" + (i+1) + "° Container: ");
                Container.show(pilhaContainer.desempilha().container);
            }
        }
    }

    public static void show2(PilhaContainer pilhaContainer){
        if(pilhaContainer.vazia()){
            System.out.println("\t\t\t\t\t Não há containers");
        }else{
            for (int i = 0; !Objects.isNull(pilhaContainer.topo); i++) {
                System.out.println("\t\t\t\t\t" + (i+1) + "° Container: ");
                Container.show(pilhaContainer.desempilha().container);
            }
        }
    }
}
