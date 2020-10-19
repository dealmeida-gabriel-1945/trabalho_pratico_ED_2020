package main.datashape.tads;

import main.datashape.Navio;
import main.datashape.elementos.ElementoNavio;

import java.util.Objects;

public class FilaNavio {
    public ElementoNavio elementoInicio = null, elementoFim = null;

    public static FilaNavio clona(FilaNavio filaOriginal) {
        FilaNavio clone = new FilaNavio();
        clone.elementoInicio = ElementoNavio.clone(filaOriginal.elementoInicio);
        clone.elementoFim = ElementoNavio.clone(filaOriginal.elementoFim);
        return clone;
    }

    public int enfileira(Navio dados){
        ElementoNavio elementoNavio = new ElementoNavio(dados);
        if(Objects.isNull(this.elementoFim)){
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
}
