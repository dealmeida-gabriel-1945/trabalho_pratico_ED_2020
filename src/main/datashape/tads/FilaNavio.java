package main.datashape.tads;

import main.datashape.Navio;
import main.datashape.elementos.ElementoNavio;

import java.util.Objects;

public class FilaNavio {
    public ElementoNavio elementoInicio;
    public ElementoNavio elementoFim;

    //prepara a fila
    public void prepare() {
        this.elementoInicio = null;
        this.elementoFim = null;
    }

    //verifica se a fila está vazia
    public boolean vazia(){
        //se o início estiver vazio, a fila está vazia
        return Objects.isNull(this.elementoInicio);
    }

    public boolean enfileira(Navio navio){
        //se o navio for nulo, nao o enfilera
        if(Objects.isNull(navio)) return false;

        //elemento auxiliar
        ElementoNavio elNavio = new ElementoNavio();
        //seta o novo navio no elemento auxiliar
        elNavio.navio = navio;
        //seta nulo o proximo elemento no elemento auxiliar
        elNavio.proximo = null;

        if(this.vazia()){//se a fila for vazia, ensira no inicio
            this.elementoInicio = elNavio;
        }else{//se não, insira no final
            this.elementoFim.proximo = elNavio;
        }
        this.elementoFim = elNavio;
        return true;
    }

    //desenfilera 1 navio da fila
    public ElementoNavio desinfileira(){
        //se a fila estiver vazia, não se pode desenfileirar
        if(this.vazia()) return  null;

        //elemento auxiliar recebe o inicio
        ElementoNavio elNavio  = this.elementoInicio;
        //o inicio recebe o segundo elemento
        this.elementoInicio = this.elementoInicio.proximo;
        if(this.vazia()){//se ela for vazia, anula o final
            //fim passa a ser null
            this.elementoFim = null;
        }
        return elNavio;
    }

    //retorna a quantidade de elementos na fila
    public Long quantidade() {
        //se a fila estiver vazia, retorna zero
        if(this.vazia()) return 0L;
        //contador de navios
        Long qtd = 0L;
        //fila auxiliar
        FilaNavio aux = new FilaNavio();
        aux.prepare();
        //passa todos do original para o auxiliar
        while (!this.vazia()){
            aux.enfileira(this.desinfileira().navio);
            qtd+=1L;
        }
        //passa todos do auxiliar para o original
        while (!aux.vazia()){
            this.enfileira(aux.desinfileira().navio);
        }
        return qtd;
    }
}
