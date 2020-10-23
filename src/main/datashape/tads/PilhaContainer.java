package main.datashape.tads;

import main.datashape.Container;
import main.datashape.elementos.ElementoContainer;

import java.util.Objects;

public class PilhaContainer {
    public ElementoContainer topo;

    public void prepare() {//prepara a pilha de containers
        this.topo = null;
    }

    public boolean vazia(){//verifica se a pílha está vazia
        //se o topo for nulo, a pilha está vazia
        return Objects.isNull(this.topo);
    }

    public boolean empilha(Container container){//empilha um elemento container à pilha
        //se os dados forem nulos, não se pode inserir
        if (Objects.isNull(container)) return false;

        //cria um nó auxiliar
        ElementoContainer elContainer = new ElementoContainer();
        //seta o novo container no elemento
        elContainer.container = container;
        //coloca o topo atual como o proximo
        elContainer.proximo = this.topo;
        //agora, o topo é o novo elemento
        this.topo = elContainer;
        return true;
    }

    public ElementoContainer desempilha(){
        //se a pilha for vazia, nao se pode desempilhar
        if(this.vazia()) return null;

        //Elemento auxiliar recebe o topo
        ElementoContainer dados = this.topo;
        //o topo é o próximo do antigo topo
        this.topo = dados.proximo;
        //retorna o elemento desempilhado
        return dados;
    }

    //retorna a quantidade de
    public int quantidade(){
        //se a pilha for vazia, retorna zero
        if(this.vazia()) return 0;
        //variavel para contar
        int qtd = 0;
        //inicia a pilha auxiliar e a prepara
        PilhaContainer aux = new PilhaContainer();
        aux.prepare();

        //desempilha toda a pilha original e conta quantos elementos
        while (!this.vazia()){
            aux.empilha(this.desempilha().container);
            qtd++;
        }

        //retorna os itens para a original
        while (!aux.vazia()){
            this.empilha(aux.desempilha().container);
        }

        return qtd;
    }

    //esvazia a pilha
    public void esvazia() {
        //seta o topo como nulo
        this.topo = null;
    }
}
