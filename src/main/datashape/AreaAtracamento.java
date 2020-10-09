package main.datashape;

import main.datashape.tads.FilaNavio;
import main.datashape.tads.PilhaContainer;
import main.util.Constantes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class AreaAtracamento {
    public FilaNavio filaNavio = new FilaNavio();
    public ArrayList<PilhaContainer> travessas = new ArrayList<PilhaContainer>();

    public AreaAtracamento(){
        /*mesmo sendo um vetor dinamico, ele será tratado como estático*/
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            travessas.add(new PilhaContainer());
        }
    }

    public static ArrayList<AreaAtracamento> clona(ArrayList<AreaAtracamento> areasOriginais) {
        ArrayList<AreaAtracamento> clones = new ArrayList<>();
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            clones.add(AreaAtracamento.clona(areasOriginais.get(i)));
        }
        return clones;
    }

    public static AreaAtracamento clona(AreaAtracamento areaOriginal) {
        AreaAtracamento clone = new AreaAtracamento();
        clone.travessas = PilhaContainer.clonaTravessas(areaOriginal.travessas);
        clone.filaNavio = FilaNavio.clona(areaOriginal.filaNavio);
        return clone;
    }

    public void adicionaNavios(int qtdNavios, Random rand){
        for (int i = 0; i < qtdNavios; i++) {
            this.filaNavio.enfileira(new Navio(rand.nextInt(16)));
        }
        System.out.println("");
    }

    public boolean travessasLotadas(){
        boolean boo = true;
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            if(this.travessas.get(i).quantidade() < Constantes.MAX_QTD_DE_CONTAINERS_POR_TRAVESSA){
                boo = false;
            }
        }
        return boo;
    }

    public Long adicionaContainer(Container container){
        Long tempo = 0L;
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            if(this.travessasLotadas()){
                tempo += Constantes.TEMPO_RETIRAR_TRAVESSA_PARA_O_PATIO * Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS;
            }if(this.travessas.get(i).quantidade() < Constantes.MAX_QTD_DE_CONTAINERS_POR_TRAVESSA){
                this.travessas.get(i).empilha(container);
                tempo+=1L;
                break;
            }
        }
        return tempo;
    }

    public void show(){
        System.out.println("\tFila de navios:");
        this.filaNavio.show();

        System.out.println("\tTravessas de containers:");
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            System.out.println("\t\t" + (i+1) + "ª Travessa de containers: ");
            this.travessas.get(i).show();
        }
    }

    public static void work(AreaAtracamento areaAtracamento){
        if(areaAtracamento.filaNavio.vazia()) return;

        Navio primeiroNavio = areaAtracamento.filaNavio.elementoInicio.navio, toWork = new Navio();
        Long aux = 0L, tempoMais = 0L;
        while (true){
            toWork = areaAtracamento.filaNavio.desenfileira().navio;
            if(toWork.trabalhado) break;
            toWork.tempo = areaAtracamento.desenpilhaNavio(toWork);
            toWork.tempo += tempoMais;
            tempoMais = toWork.tempo;
            toWork.trabalhado = true;
            areaAtracamento.filaNavio.enfileira(toWork);
            if(Objects.equals(primeiroNavio.nome, toWork.nome)){
                aux++;
            }
        }
        System.out.println("");
    }

    public Long desenpilhaNavio(Navio navio){
        Long tempo = 0L;
        for (int i = 0; i < Constantes.MAX_QTD_PILHA_DE_CONTAINERS; i++) {
            if(!navio.pilhasDeContainers.get(i).vazia()){
                while (!navio.pilhasDeContainers.get(i).vazia()){
                    tempo += this.adicionaContainer(navio.pilhasDeContainers.get(i).desempilha().container);
                }
            }
        }
        return tempo;
    }
}
