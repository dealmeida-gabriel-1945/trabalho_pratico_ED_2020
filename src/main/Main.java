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
    /*elementos estáticos que poderão ser acessados em qualquer ponto do código*/
    public static Long MAX_TEMPO_DE_ESPERA = 7000L;

    public static void main(String[] args) {
        //Porto em questão
        Porto porto = new Porto();
        //Objeto responsável por gerar números aleatórios
        Random rand = new Random();
        //Objeto responsável por ler o que o usuário digitar
        Scanner ler = new Scanner(System.in);

        //recoplhe informações necessárias
        System.out.print("Digite o nome do porto: ");
        porto.nome = ler.nextLine();
        System.out.print("Digite o máximo de tempo de espera (dica: 7000): ");
        MAX_TEMPO_DE_ESPERA = ler.nextLong();

        //o porto é populado com dados aleatórios
        porto.popula(rand);
        //é mostrado cada elemento contido no porto
        showAll(porto);
        //dá-se início à contagem do tempo de espera de cada navio
        porto = calculaAll(porto);
        //mostra o worklogs (reports de saída) de cada navio
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            porto.areasAtracamento.get(i).workLog.show(porto.areasAtracamento.get(i), i);
        }
    }

    public static Porto calculaAll(Porto portoOriginal){
        Porto porto = Porto.clona(portoOriginal);
        System.out.println("\n\n\n\n");
        Porto.work(porto);
        return porto;
    }

    public static void showAll(Porto portoOriginal){
        //realiza deep copy do porto
        Porto porto = Porto.clona(portoOriginal);
        //mostra cada elemento do porto
        showPorto(porto);
    }

    public static void showPorto(Porto porto){
        //mopstra o nome do porto
        System.out.println("\n\nPorto: " + porto.nome);
        //mostra os elementos de cada area de atracamento
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            System.out.println("\n" + (i+1) + "ª Área de atracamento:");
            showAreasAtracamento(porto.areasAtracamento.get(i));
        }
    }
    public static void showAreasAtracamento(AreaAtracamento areaAtracamento){
        //mostra cada elemento da filad e navio
        System.out.println("\tFila de navios:");
        showFilaNavio(areaAtracamento.filaNavio);

        //mostra cada elemento das travessas de containers
        System.out.println("\tTravessas de containers:");
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            System.out.println("\t\t" + (i+1) + "ª Travessa de containers: ");
            showTravessas(areaAtracamento.travessas.get(i));
        }
    }
    public static void showFilaNavio(FilaNavio filaNavio){
        //se a fila de navio estiver vazia, mostra-se uma mensagem avisando o fato
        if(filaNavio.vazia()){
            System.out.println("\t\tNão há navios");
        }else{
            //se nao, mostra cada navio da fila
            for (int i = 0; !Objects.isNull(filaNavio.elementoInicio); i++) {
                System.out.println("\t\t" + (i+1) + "° navio: ");
                //retira o navio da fila
                ElementoNavio toShow = filaNavio.desenfileira();
                if(!Objects.isNull(toShow) && !Objects.isNull(toShow.navio)){
                    //mostra o navio
                    showNavio(toShow.navio);
                }
            }
        }
    }
    public static void showNavio(Navio navio){
        //mostra os dados do navio
        System.out.println("\t\t\tNOME: " + navio.nome);
        System.out.println("\t\t\tTEMPO ESPERA: " + navio.tempo);
        System.out.println("\t\t\tQUANTIDADE DE CONTAINERS: " + navio.qtdContainer);
        for (int i = 0; i < Constantes.MAX_QTD_PILHA_DE_CONTAINERS; i++) {
            //mostra cada pilha de continer do navio
            System.out.println("\n\t\t\t\t" + (i+1) + "° Pilha: ");
            showPilhaContainers(navio.pilhasDeContainers.get(i));
        }
    }
    public static void showTravessas(PilhaContainer pilhaContainer){
        //mostra o conteúdo das travessas
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
        //mostra o conteudo das pilhas de continers
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
        //mostra o dado de container
        System.out.println("\t\t\t\t\tID: " + container.id);
    }
}
