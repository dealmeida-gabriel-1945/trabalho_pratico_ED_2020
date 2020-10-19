package main.datashape;

import main.util.Constantes;

import java.util.ArrayList;
import java.util.Random;

public class Porto implements Cloneable{
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
    }

    public static Porto clona(Porto portoOriginal) {
        Porto clone = new Porto();
        clone.areasAtracamento = AreaAtracamento.clona(portoOriginal.areasAtracamento);
        clone.nome = portoOriginal.nome;
        return clone;
    }

    public void popula(Random rand){
        int qtd = 0;
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            qtd = rand.nextInt(6);
            this.areasAtracamento.get(i).adicionaNavios(qtd, rand);
        }
    }

    public static void work(Porto porto){
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            AreaAtracamento.work(porto.areasAtracamento.get(i));
        }
    }
}
