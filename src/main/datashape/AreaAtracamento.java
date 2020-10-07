package main.datashape;

import main.datashape.tads.FilaNavio;
import main.datashape.tads.PilhaContainer;
import main.util.Constantes;

import java.util.ArrayList;
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

    public void adicionaNavios(int qtdNavios, Random rand){
        for (int i = 0; i < qtdNavios; i++) {
            this.filaNavio.enfileira(new Navio(rand.nextInt(16)));
        }
        System.out.println("");
    }

    public static void show(AreaAtracamento areaAtracamento){
        System.out.println("\tFila de navios:");
        FilaNavio.show(areaAtracamento.filaNavio);

        System.out.println("\tTravessas de containers::");
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            System.out.println("\t\t" + (i+1) + "ª Travessa de containers: ");
            PilhaContainer.show(areaAtracamento.travessas.get(i));
        }
    }
}
