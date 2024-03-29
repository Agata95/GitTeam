package com.javagda25.GitTeam;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Magazyn magazyn = new Magazyn();

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
                    magazyn.dodajZamowienie();
                    break;
                case "b":
                    dodajDostawe(magazyn);
                    break;
                case "c":
                    magazyn.listujZamowienia(magazyn.listaZamówień);
                    break;
            }


        } while (!opcja.equals("q"));

        System.out.println(magazyn.produktyWMagazynie);

    }


    public static void dodajDostawe(Magazyn magazyn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer zamówienia");
        String numerZamówienia = scanner.nextLine();

        //sprawdzam czy podany nr zamówienia istnieje
        if (magazyn.listaZamówień.containsKey(numerZamówienia)) {
            Zamówienie zamówienie = magazyn.listaZamówień.get(numerZamówienia);
            System.out.println("Zamówienie zawiera " + zamówienie.getProdukty().size() + " produkty");
            //iteracja po liście produktów celem wypisania ich ilości z zamówienia
            for (Produkt produkt : zamówienie.getProdukty()) {
                System.out.println("Czy w dostawie znajduje się produkt (tak/nie): " + produkt.wypiszProdukt());
                //uzupełnianie magazynu dla dostarczonych produktów
                try {
                    switch (scanner.nextLine().toUpperCase()) {
                        case ("TAK"):
                            produkt.setCzyDostarczony(true);
                            magazyn.produktyWMagazynie.put(numerZamówienia, produkt);
                            break;
                        case ("NIE"):
                            produkt.setCzyDostarczony(false);
                            System.out.println("Oznaczam produkt jako niedostarczony. Nie zwiększam stanu magazynowego.");
                            break;
                        default:
                            break;
                    }
                } catch (IllegalArgumentException iae) {
                    System.err.println("Błędna komenda!");
                    continue;
                }
            }
            //utworzenie nr faktury
            System.out.println("Zamówienie zrealizowane. Podaj numer faktury");
            zamówienie.setNumerFaltury(scanner.nextLine());
            System.out.println("Faktura dopisana do zamówienia");
            //utworzenie daty dostarczenia
            System.out.println("Czy chcesz wprowadzić datę dostawy ręcznie? tak/nie");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            try {
                switch (scanner.nextLine().toUpperCase()) {
                    case ("TAK"):
                        System.out.println("Wprowadż datę w formacie yyyy-MM-dd HH:mm");
                        try {
                            zamówienie.setDataDostarczenia(LocalDateTime.parse(scanner.nextLine(), dateTimeFormatter));
                        } catch (DateTimeParseException dte) {
                            System.out.println("Podano zły format daty!");
                        }
                        break;
                    case ("NIE"):
                        zamówienie.setDataDostarczenia(LocalDateTime.now());
                        break;
                }


            } catch (IllegalArgumentException iae) {
                System.err.println("Błędna komenda!");
            }
            //obliczanie czasu realizacji zamówienia
            Timestamp tZamówienie = Timestamp.valueOf(zamówienie.getDataZamówienia());
            Timestamp tDostarczenie = Timestamp.valueOf(zamówienie.getDataDostarczenia());

            Long czasRealizacji = tDostarczenie.getTime() - tZamówienie.getTime();

            if (czasRealizacji > 60000) {
                System.out.println("Dostawa zrealizowana z opóźnieniem!");
            }

        } else {
            System.err.println("Brak zamówienia o podanym numerze!");
        }


    }


}

