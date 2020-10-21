package main.datashape;

import main.util.Constantes;

public class WorkLog {
    public double mediaDeEspera = 0L;
    public String textContent = "";
    public Long totalContainers = 0L;
    public Long viagensDoCarro = 0L;

    public void show(AreaAtracamento areaAtracamento, int index){
        System.out.println("\n" + (index+1) + "ª Área de Atracamento:");
        System.out.println("Média do tempo de espera: " + mediaDeEspera + " unidades de tempo.");
        System.out.println("Total de Containers que Passaram Pelas Travessas: " + totalContainers);
        System.out.println("Containers Presentes nas Travessas: ");
        Long cont = 0L;
        for (int i = 0; i < Constantes.MAX_QTD_TRAVESSAS_DE_CONTAINERS; i++) {
            while (!areaAtracamento.travessas.get(i).vazia()) {
                System.out.println((i+1) + "" + areaAtracamento.travessas.get(i).desempilha().container.id);
                cont++;
            }
        }
        System.out.println("------> TotalContainers Presentes nas Travessas: " + cont);
        System.out.println("------> Total de Viagens do Carro: " + viagensDoCarro);
        System.out.println("Logs dos navios:\n" + textContent);
    }
}
