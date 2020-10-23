package main;

import main.datashape.Porto;

public class Main {

    public static void main(String[] args) {
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
