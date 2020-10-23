package main.datashape;

import main.util.Constantes;

import java.util.ArrayList;

public class Porto implements Cloneable{
    public String nome;
    public ArrayList<AreaAtracamento> areasAtracamento;

    //Prepara o porto
    public void prepare() {
        //inicia a list de areas de atracamento
        this.areasAtracamento = new ArrayList<>();
        //for para inciar as areas de atracamnto
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            AreaAtracamento area = new AreaAtracamento();
            //prepara a area de atracamento
            area.prepare();
            areasAtracamento.add(area);
        }
        //seta o nome
        this.nome = Constantes.NOME_PORTO;
    }

    //popula o porto
    public void popula() {
        //for para popular as areas de atracamento
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            areasAtracamento.get(i).popula();
        }
    }

    //trabalha o porto
    public void work() {
        //for para trabalhar as areas de atracamento
        for (int i = 0; i < Constantes.MAX_QTD_AREA_ATRACAMENTO; i++) {
            areasAtracamento.get(i).work();
        }
    }
}
