package main.datashape.tads;

import main.datashape.Navio;
import main.datashape.elementos.ElementoNavio;

import java.util.Objects;

public class FilaNavio {
    public ElementoNavio elementoInicio = null, elementoFim = null;

    public int enfileira(Navio dados){
        ElementoNavio elementoNavio = new ElementoNavio(dados);
        if(this.elementoFim == null){
            this.elementoInicio = elementoNavio;
        }else{
            this.elementoFim.proximo = elementoNavio;
        }
        this.elementoFim = elementoNavio;
        return 1;
    }

    public ElementoNavio desenfileira(){
        if(this.elementoInicio == null){
            return null;
        }
        ElementoNavio elementoNavio = this.elementoInicio;
        this.elementoInicio = this.elementoInicio.proximo;
        if(this.elementoInicio == null){
            this.elementoFim = null;
        }
        return elementoNavio;
    }

    public Boolean vazia(){
        return Objects.isNull(this.elementoInicio);
    }

    public static void show(FilaNavio filaNavio){

        if(filaNavio.vazia()){
            System.out.println("\t\tNão há navios");
        }else{
            for (int i = 0; !Objects.isNull(filaNavio.elementoInicio); i++) {
                System.out.println("\t\t" + (i+1) + "° navio: ");
                ElementoNavio toShow = filaNavio.desenfileira();
                if(!Objects.isNull(toShow) && !Objects.isNull(toShow.navio)){
                    Navio.show(toShow.navio);
                }
            }
        }
    }
}
