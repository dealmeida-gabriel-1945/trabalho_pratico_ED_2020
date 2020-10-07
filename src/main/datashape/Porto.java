package main.datashape;

import main.util.Constantes;

import java.util.ArrayList;
import java.util.Random;

public class Porto {
    public String nome;
    public ArrayList<AreaAtracamento> areasAtracamento = new ArrayList<AreaAtracamento>();

    public Porto(){
        /*mesmo sendo um vetor dinamico, ele será tratado como estático*/
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            areasAtracamento.add(new AreaAtracamento());
        }
    }
    public Porto(Porto porto){
        this.areasAtracamento = porto.areasAtracamento;
        this.nome = porto.nome;
    }

    public void popula(Random rand){
        int qtd = 0;
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            qtd = rand.nextInt(6);
            this.areasAtracamento.get(i).adicionaNavios(qtd, rand);
        }
    }

    public static void show(Porto porto){
        System.out.println("\n\nPorto: " + porto.nome);
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            System.out.println("\n" + (i+1) + "ª Área de atracamento:");
            AreaAtracamento.show(porto.areasAtracamento.get(i));
        }
    }
}
