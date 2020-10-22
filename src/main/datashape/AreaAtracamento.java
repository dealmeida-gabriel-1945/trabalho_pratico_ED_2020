package main.datashape;

import main.Main;
import main.datashape.tads.FilaNavio;
import main.datashape.tads.PilhaContainer;
import main.util.Constantes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class AreaAtracamento {
    public FilaNavio filaNavio = new FilaNavio();
    public WorkLog workLog = new WorkLog();
    public ArrayList<PilhaContainer> travessas = new ArrayList<PilhaContainer>();

    public AreaAtracamento(){
        /*mesmo sendo um vetor dinamico, ele será tratado como estático*/
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            travessas.add(new PilhaContainer());
        }
    }
    //deep copy das áreas de atracamento
    public static ArrayList<AreaAtracamento> clona(ArrayList<AreaAtracamento> areasOriginais) {
        ArrayList<AreaAtracamento> clones = new ArrayList<>();
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            clones.add(AreaAtracamento.clona(areasOriginais.get(i)));
        }
        return clones;
    }
    //deep copy da área de atracamento
    public static AreaAtracamento clona(AreaAtracamento areaOriginal) {
        AreaAtracamento clone = new AreaAtracamento();
        clone.travessas = PilhaContainer.clonaTravessas(areaOriginal.travessas);
        clone.filaNavio = FilaNavio.clona(areaOriginal.filaNavio);
        return clone;
    }

    public void adicionaNavios(int qtdNavios, Random rand){
        for (int i = 0; i < qtdNavios; i++) {
            //enfileira um novo navio com dados aleatorios
            this.filaNavio.enfileira(new Navio(rand.nextInt(16), i));
        }
    }

    //verifica se as travessas estão lotadas
    public boolean travessasLotadas(){
        boolean boo = true;
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            //verifica uma por uma se elas possuem vagas
            if(this.travessas.get(i).quantidade() < Constantes.MAX_QTD_DE_CONTAINERS_POR_TRAVESSA){
                boo = false;
            }
        }
        return boo;
    }

    //adiciona container ás travessas retornando o tempo gasto
    public Long adicionaContainer(Container container){
        Long tempo = 0L;
        this.workLog.totalContainers++;
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            //se todas as travessas estiverem lotadas, mande o conteúdo para o pátio
            if(this.travessasLotadas()){
                tempo += Constantes.TEMPO_RETIRAR_TRAVESSA_PARA_O_PATIO * Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS;
                this.workLog.viagensDoCarro += Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS;
                this.esvaziaTravessas();
                //chega uma nova leva de vaios ou nao
                this.chegaNovosNavios(Constantes.TEMPO_RETIRAR_TRAVESSA_PARA_O_PATIO * Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS);
            }
            if(this.travessas.get(i).quantidade() < Constantes.MAX_QTD_DE_CONTAINERS_POR_TRAVESSA){
                this.travessas.get(i).empilha(container);
                tempo+=1L;
                //chega uma nova leva de vaios ou nao
                this.chegaNovosNavios(1L);
                break;
            }
        }
        return tempo;
    }

    private void esvaziaTravessas() {
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            this.travessas.set(i, new PilhaContainer());
        }
    }

    //adiciona de 0 a 3 navios a cada tic
    private void chegaNovosNavios(Long tics) {
        for (Long i = tics; i > 0 ; i--) {
            this.adicionaNavios(new Random().nextInt(4), new Random());
        }
    }

    //faz a area de atracamento trabalhar
    public static void work(AreaAtracamento areaAtracamento, int posicaoArea){
        //se a fila e navios está vazia, nada é executado
        if(areaAtracamento.filaNavio.vazia()) return;
        Navio toWork = new Navio();
        String workLog = "";
        Long tempoMais = 0L, contadorNavios = 0L, somaTempoNavios = 0L;
        //enquanto a fila não for vazia, é contado o tempo de espera de cada um
        while (!areaAtracamento.filaNavio.vazia()){
            //desenfilera o navio para tirar seus containers
            toWork = areaAtracamento.filaNavio.desenfileira().navio;
            //desempilha os containers do navio e seta o tempo de espera
            toWork.tempo = areaAtracamento.desenpilhaNavio(toWork);
            //seta o "tempo a mais" como sendo o tempo de espera total do atual
            tempoMais += toWork.tempo;
            //adiciona um workLog
            workLog += String.join("\n", generateSaidaNavio(toWork, posicaoArea));
            contadorNavios++;
            somaTempoNavios += toWork.tempo;
            //se o tempo de espera sobrepor o máximo, quebra-se o laço
            if(tempoMais > Main.MAX_TEMPO_DE_ESPERA) break;
        }
        //adiciona um workLog na variável do main
        areaAtracamento.workLog.textContent = workLog;
        areaAtracamento.workLog.mediaDeEspera = (contadorNavios != 0) ? somaTempoNavios/contadorNavios : 0L;
        areaAtracamento.workLog.mediaDeEspera = somaTempoNavios/contadorNavios;
    }

    //gerar uma saída de navío para o workLog
    private static String generateSaidaNavio(Navio toWork, int posicaoArea) {
        return String.join("\n", (posicaoArea + 1) + "ª Área de atracamento:", toWork.generateLog());
    }

    //desempilha cada container de um navio
    public Long desenpilhaNavio(Navio navio){
        Long tempo = 0L;
        for (int i = 0; i < Constantes.MAX_QTD_PILHA_DE_CONTAINERS; i++) {
            while (!navio.pilhasDeContainers.get(i).vazia()){
                tempo += this.adicionaContainer(navio.pilhasDeContainers.get(i).desempilha().container);
            }
        }
        return tempo;
    }
}
