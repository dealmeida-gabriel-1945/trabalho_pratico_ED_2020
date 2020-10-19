package main;

import main.datashape.AreaAtracamento;
import main.datashape.Container;
import main.datashape.Navio;
import main.datashape.Porto;
import main.datashape.elementos.ElementoNavio;
import main.datashape.tads.FilaNavio;
import main.datashape.tads.PilhaContainer;
import main.util.Constantes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Porto porto = new Porto();
    public static Random rand = new Random();
    public static Long MAX_TEMPO_DE_ESPERA = 7000L;
    public static ArrayList<String> workLogs = new ArrayList<>();

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.print("Digite o nome do porto: ");
        porto.nome = ler.nextLine();
        System.out.print("Digite o máximo de tempo de espera (dica: 7000): ");
        MAX_TEMPO_DE_ESPERA = ler.nextLong();

        porto.popula(rand);
        showAll(porto);
        calculaAll(porto);
        for (int i = 0; i < workLogs.size(); i++) {
            System.out.println(workLogs.get(i));
            System.out.println("\n\n");
        }
    }

    public static Porto calculaAll(Porto portoOriginal){
        Porto porto = Porto.clona(portoOriginal);
        System.out.println("\n\n\n\n");
        Porto.work(porto);
        return porto;
    }

    public static void showAll(Porto portoOriginal){
        Porto porto = Porto.clona(portoOriginal);
        showPorto(porto);
    }

    public static void showPorto(Porto porto){
        System.out.println("\n\nPorto: " + porto.nome);
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            System.out.println("\n" + (i+1) + "ª Área de atracamento:");
            showAreasAtracamento(porto.areasAtracamento.get(i));
        }
    }
    public static void showAreasAtracamento(AreaAtracamento areaAtracamento){
        System.out.println("\tFila de navios:");
        showFilaNavio(areaAtracamento.filaNavio);

        System.out.println("\tTravessas de containers:");
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            System.out.println("\t\t" + (i+1) + "ª Travessa de containers: ");
            showTravessas(areaAtracamento.travessas.get(i));
        }
    }
    public static void showFilaNavio(FilaNavio filaNavio){
        if(filaNavio.vazia()){
            System.out.println("\t\tNão há navios");
        }else{
            for (int i = 0; !Objects.isNull(filaNavio.elementoInicio); i++) {
                System.out.println("\t\t" + (i+1) + "° navio: ");
                ElementoNavio toShow = filaNavio.desenfileira();
                if(!Objects.isNull(toShow) && !Objects.isNull(toShow.navio)){
                    showNavio(toShow.navio);
                }
            }
        }
    }
    public static void showNavio(Navio navio){
        System.out.println("\t\t\tNOME: " + navio.nome);
        System.out.println("\t\t\tTEMPO ESPERA: " + navio.tempo);
        System.out.println("\t\t\tQUANTIDADE DE CONTAINERS: " + navio.qtdContainer);
        for (int i = 0; i < Constantes.MAX_QTD_PILHA_DE_CONTAINERS; i++) {
            System.out.println("\n\t\t\t\t" + (i+1) + "° Pilha: ");
            showPilhaContainers(navio.pilhasDeContainers.get(i));
        }
    }
    public static void showTravessas(PilhaContainer pilhaContainer){
        if(pilhaContainer.vazia()){
            System.out.println("\t\t\t Não há containers");
        }else{
            for (int i = 0; !Objects.isNull(pilhaContainer.topo); i++) {
                System.out.println("\t\t\t" + (i+1) + "° Container: ");
                showContainer(pilhaContainer.desempilha().container);
            }
        }
    }
    public static void showPilhaContainers(PilhaContainer pilhaContainer){
        if(pilhaContainer.vazia()){
            System.out.println("\t\t\t\t\t Não há containers");
        }else{
            for (int i = 0; !Objects.isNull(pilhaContainer.topo); i++) {
                System.out.println("\t\t\t\t\t" + (i+1) + "° Container: ");
                showContainer(pilhaContainer.desempilha().container);
            }
        }
    }
    public static void showContainer(Container container){
        System.out.println("\t\t\t\t\tID: " + container.id);
    }
}
