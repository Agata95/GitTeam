package com.javagda25.GitTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Magazyn magazyn = new Magazyn();
        Zamówienie zamówienie = new Zamówienie();
        Scanner scanner = new Scanner(System.in);

        String polecenia = "Wybierz dostępną opcję: \n\"a\" (składanie zamówień) \n\"b\" (realizowanie zamówień)" +
                "\n\"c\" (listowane zamówień) \n\"d\" (listowanie dostaw) \n\"e\" (listowanie produktów)" +
                "\n\"f\" (zapisywanie do pliku) \n\"g\" (wczytywanie z pliku) \n\"h\" (sprzedaż) \n\"q\" (wyjście)";

        String opcja;
        do {
            System.out.println(polecenia);
            opcja = scanner.nextLine();
            switch (opcja) {
                case "a":
                    magazyn.dodajZamowienie(zamówienie);
                    break;
                case "c":
                    magazyn.listujZamowienia(magazyn.listaZamówień);
                    break;
            }

        } while (!opcja.equals("q"));


    }
}
