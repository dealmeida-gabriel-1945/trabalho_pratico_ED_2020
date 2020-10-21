package main.datashape;

import main.util.Constantes;

public class WorkLog {
    public double mediaDeEspera = 0L;
    public String textContent = "";
    public Long totalContainers = 0L;
    public String idsContainers = "";
    public Long viagensDoCarro = 0L;

    public void show(AreaAtracamento areaAtracamento, int index){
        System.out.println("\n" + (index+1) + "ª Área de Atracamento:");
        System.out.println("Média do tempo de espera: " + mediaDeEspera + " unidades de tempo.");
        System.out.println("Total de Containers que Passaram Pelas Travessas: " + totalContainers);
        System.out.println("Containers Presentes nas Travessas: ");
        System.out.println("------> Total de Viagens do Carro: " + viagensDoCarro);
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            System.out.println("-----------> Travessa n° " + i + ": " + areaAtracamento.travessas.get(i).quantidade() + " containers.");
        }
        Long cont = 0L;
        int j = 0;
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            j = 0;
            while (!areaAtracamento.travessas.get(i).vazia()) {
                idsContainers += (j+1) + "° Container: " + areaAtracamento.travessas.get(i).desempilha().container.id + "\n";
                cont++;
                j++;
            }
        }
        System.out.println("------> Total de Containers Presentes nas Travessas: " + cont);
        System.out.println(idsContainers);
        System.out.println("Logs dos navios:\n" + textContent);
    }
}
