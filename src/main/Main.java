package main;

import main.datashape.Porto;
import main.util.Constantes;

import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Random rand = new Random();
        Scanner ler = new Scanner(System.in);
        Porto porto = new Porto();
        System.out.print("Digite o nome do porto: ");
//        porto.nome = ler.nextLine();
        porto.nome = "Nosso Porto";
//        showAll(porto);

        porto.popula(rand);
        showAll(porto);
        calculaAll(porto);
        showAll(porto);
    }

    public static void calculaAll(Porto portoOriginal){
        Porto porto = Porto.clona(portoOriginal);
        System.out.println("\n\n\n\n");
        Porto.work(porto);
        porto.show();
    }

    public static void showAll(Porto portoOriginal){
        Porto porto = Porto.clona(portoOriginal);
        porto.show();
    }
}
