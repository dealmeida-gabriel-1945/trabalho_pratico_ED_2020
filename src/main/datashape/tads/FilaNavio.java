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
        //elemento a ser adicionado
        ElementoNavio elementoNavio = new ElementoNavio(dados);
        //verifica se p elemento do fim é nulo
        if(Objects.isNull(this.elementoFim)){
            //se sim, o inicio será o novo elemento
            this.elementoInicio = elementoNavio;
        }else{
            //se nao, o novo elemento será colocado após op último
            this.elementoFim.proximo = elementoNavio;
        }
        //o fim da fila será igual ao novo elemento
        this.elementoFim = elementoNavio;
        return 1;
    }

    public ElementoNavio desenfileira(){
        //verifica se a fila está vazia
        if(this.elementoInicio == null){
            return null;
        }
        //elemento auxiliar
        ElementoNavio elementoNavio = this.elementoInicio;
        //o inicio herda os valores do elemento seguinte
        this.elementoInicio = this.elementoInicio.proximo;
        //se, agora, a file estiver vazia é setado como null o ultimo item
        if(this.elementoInicio == null){
            this.elementoFim = null;
        }
        //retorna o elemento retiurado da TAD
        return elementoNavio;
    }

    //se o elemento inicial for nulo, a fila está vazia
    public Boolean vazia(){
        return Objects.isNull(this.elementoInicio);
    }
}
