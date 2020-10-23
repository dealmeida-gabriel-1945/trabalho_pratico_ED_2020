package main.datashape;

import main.datashape.tads.FilaNavio;
import main.datashape.tads.PilhaContainer;
import main.util.Constantes;

import java.util.ArrayList;
import java.util.Random;

public class AreaAtracamento {
    public Long tempoDecorrido;
    public Carro carro;
    public FilaNavio filaNavio;
    public ArrayList<PilhaContainer> travessas;

    //prepara a area de atracamento
    public void prepare() {
        this.tempoDecorrido = 0L;
        //inicia a fila de navios
        this.filaNavio = new FilaNavio();
        //prepara a fila
        this.filaNavio.prepare();

        //inicia a lista de travessas
        this.travessas = new ArrayList<>();
        //for para iniciar cada travessa
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            PilhaContainer pilha = new PilhaContainer();
            //prepara a travessa
            pilha.prepare();
            this.travessas.add(pilha);
        }

        this.carro = new Carro();
        this.carro.prepare();
    }

    //popula a area de atracamento
    public void popula() {
        Random rand = new Random();
        int qtdNavios = rand.nextInt(Constantes.MAX_NAVIOS_INICIAIS);
        for (int i = 0; i < qtdNavios ; i++) {
            Navio newNavio = new Navio();
            //prepara o navio
            newNavio.prepare();
            //popula o navio
            newNavio.popula();
            this.filaNavio.enfileira(newNavio);
        }
    }

    //trabalha a area de atracamento
    public void work() {
        //enquanto a fila nao for vazia e o tempo máximo nao for batido
        //a area ser átrabalhada
        while(!this.filaNavio.vazia() && (tempoDecorrido <= Constantes.TEMPO_MAXIMO)){
            //desempilha o navio
            Navio toWork = this.filaNavio.desinfileira().navio;
            //trabalha o navio
            this.trabalhaNavioPt1(toWork);
            //todo: toString do navio
        }
    }

    //trabalha o navio quanto a qual travessa receberá o container
    private void trabalhaNavioPt1(Navio navio){
        //cópia da quantidade de containers
        int qtdContainers = navio.qtdContainer;
        //controlador das pilhas
        int pilha = 0;
        //enquanto a quantidade de containers não for zerada,
        //onavio será trabalhado
        while(qtdContainers > 0){
            //se encontrar uma travessa com menos de 5 containers
            //essa será escolhida para receber o novo container
            if (this.travessas.get(pilha).quantidade() < 5){
                this.trabalhaNavioPt2(navio, this.travessas.get(pilha));
            }
            //se o controlador da pilha estiver no ultimo index, retorna-o para 0
            //caso não, adiciona 1 ao valor
            pilha += (pilha == Constantes.MAX_QTD_PILHA_DE_CONTAINERS-1) ? (-pilha) : 1;
            //desconta uma unidade da quantidade de containers
            qtdContainers--;
        }
    }

    //seleciona de qual pilha do navio será tirado o container
    private void trabalhaNavioPt2(Navio navio, PilhaContainer travessa){
        //percorre todas as pilhas de containers
        for (int i = 0; i < Constantes.MAX_QTD_PILHA_DE_CONTAINERS; i++) {
            //se a pilha não estiver vazia, retida dela
            if (!navio.pilhasDeContainers.get(i).vazia()){
                //a travessa recebe o novo container
                travessa.empilha(navio.pilhasDeContainers.get(i).desempilha().container);
                //é adicionado uma unidade de tempo em todos da fila
                this.adicionarTempoEmTodaFila(Constantes.TEMPO_DESEMPILHAR_CONTAINER_GRUA);
                //o navio recebe uma unidade de tempo
                navio.tempo+=1L;
                //o tempo decorrido da área de atracamento
                this.tempoDecorrido+= 1L;
                //rompe o laço
                break;
            }
        }
        //se a travessa estiver cheia, ela será desempilhada pelo carro
        if(travessa.quantidade() == Constantes.MAX_QTD_DE_CONTAINERS_POR_TRAVESSA){
            //esvazia a travessa
            travessa.esvazia();
            //adiciona uma viagem do carro
            this.carro.viagens++;
            //adiciona o dempo decorrido do carro
            this.carro.tempoDecorrido += Constantes.TEMPO_MEDIO_CARRO;
        }
    }

    private void adicionarTempoEmTodaFila(Long intervalo) {
        //fila auxiliar
        FilaNavio aux = new FilaNavio();

        //passa todos os navios para a auxiliar adicionando o intervalo aos seus tempos
        while(!this.filaNavio.vazia()){
            //desenfileira um navio
            Navio navio = this.filaNavio.desinfileira().navio;
            //adiciona o intervalo de tempo
            navio.tempo += intervalo;
            //o coloca na auxiliar
            aux.enfileira(navio);
        }

        //agora é passado para a original os navios
        while(!aux.vazia()){
            this.filaNavio.enfileira(aux.desinfileira().navio);
        }
    }
}
