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
        if(this.elementoFim == null){
            this.elementoInicio = elementoNavio;
        }else{
            this.moveParaUltimo(this.elementoInicio, elementoNavio);
        }
        this.elementoFim = elementoNavio;
        return 1;
    }

    private void moveParaUltimo(ElementoNavio elementoInicio, ElementoNavio elementoNovo) {
        if(Objects.isNull(elementoInicio.proximo)){
            elementoInicio.proximo = elementoNovo;
        }else{
            moveParaUltimo(elementoInicio.proximo, elementoNovo);
        }
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
