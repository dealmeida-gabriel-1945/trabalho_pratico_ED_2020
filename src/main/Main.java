package main;

import main.datashape.Porto;
import main.util.Constantes;

public class Main {

    public static void main(String[] args) {
        Constantes.TEMPO_MAXIMO = 25L;

        //inicia o porto
        Porto porto = new Porto();

        //prepara o porto
        porto.prepare();

        //popula o porto
        porto.popula();

        //trabalha porto
        porto.work();

        //mostra todos os worklogs
        porto.showDados();
    }
}
