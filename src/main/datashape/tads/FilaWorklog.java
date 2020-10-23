package main.datashape.tads;

import main.datashape.Worklog;
import main.datashape.elementos.ElementoWorklog;

import java.util.Objects;

public class FilaWorklog {
    public ElementoWorklog elementoInicio;
    public ElementoWorklog elementoFim;

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

    public boolean enfileira(Worklog worklog){
        //se o worklog for nulo, nao o enfilera
        if(Objects.isNull(worklog)) return false;

        //elemento auxiliar
        ElementoWorklog elWorklog = new ElementoWorklog();
        //seta o novo worklog no elemento auxiliar
        elWorklog.worklog = worklog;
        //seta nulo o proximo elemento no elemento auxiliar
        elWorklog.proximo = null;

        if(this.vazia()){//se a fila for vazia, ensira no inicio
            this.elementoInicio = elWorklog;
        }else{//se não, insira no final
            this.elementoFim.proximo = elWorklog;
        }
        this.elementoFim = elWorklog;
        return true;
    }

    //desenfilera 1 navio da fila
    public ElementoWorklog desinfileira(){
        //se a fila estiver vazia, não se pode desenfileirar
        if(this.vazia()) return  null;

        //elemento auxiliar recebe o inicio
        ElementoWorklog elWorklog  = this.elementoInicio;
        //o inicio recebe o segundo elemento
        this.elementoInicio = this.elementoInicio.proximo;
        if(this.vazia()){//se ela for vazia, anula o final
            //fim passa a ser null
            this.elementoFim = null;
        }
        return elWorklog;
    }

    public void show() {
        while (!this.vazia()){
            this.desinfileira().worklog.show();
        }
    }
}
