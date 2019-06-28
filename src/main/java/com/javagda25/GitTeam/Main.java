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
                        try {
                            magazyn.dodajZamowienie();
                        } catch (PodanyNumerZamówieniaIstnieje pnzi) {
                            System.out.println(pnzi.getMessage());
                        }
                        break;
                    case "b":
                        try {
                            dodajDostawe(magazyn);
                        } catch (PodanyNumerFakturyIstnieje pnfi){
                            System.out.println(pnfi.getMessage());
                        }
                        break;
                    case "c":
                        magazyn.listowanie(magazyn.listaZamówieńNieZrealizowanych);
                        break;
                    case "d":
                        magazyn.listowanieDostaw(magazyn.listaZamówieńZrealizowanych);
                        break;
                    case "e":
                        magazyn.listowanieProduktow(magazyn.produktyWMagazynie);
                        break;
                    case "f":
                        magazyn.zapisz();
                        break;
                    case "g":
                        magazyn.wczytaj();
                        System.out.println("Uzupełniono stan magazynowy o następujące produkty:");
                        for (Produkt produkt : magazyn.produktyWMagazynie.values()) {
                            System.out.println(produkt.opiszProdukt());
                        }
                        System.out.println("Uzupełniono listę zamówień o następujące zamówienia:");
                        for (Zamówienie zamówienie : magazyn.listaZamówieńNieZrealizowanych.values()) {
                            System.out.println(zamówienie.wypiszNieZrealizowane());
                        }
                        System.out.println("Uzupełniono listę zamówień zrealizowanych o następujące zamówienia:");
                        for (Zamówienie zamówienie : magazyn.listaZamówieńZrealizowanych.values()) {
                            System.out.println(zamówienie.wypiszZrealizowane());
                        }
                        break;
                    case "h":
                        magazyn.sprzedaz(magazyn.produktyWMagazynie);
                        break;
                }

        } while (!opcja.equals("q"));


    }


    public static void dodajDostawe(Magazyn magazyn) throws PodanyNumerFakturyIstnieje{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer zamówienia");
        String numerZamówienia = scanner.nextLine();

        //sprawdzam czy podany nr zamówienia istnieje
        if (magazyn.listaZamówieńNieZrealizowanych.containsKey(numerZamówienia)) {
            Zamówienie zamówienie = magazyn.listaZamówieńNieZrealizowanych.get(numerZamówienia);

            // usuwanie z listy zamówienia, które zostało zrealizowane
            magazyn.listaZamówieńNieZrealizowanych.remove(numerZamówienia);

            // dodawanie do listy zamówień, te które zostały zrealizowane
            magazyn.listaZamówieńZrealizowanych.put(numerZamówienia, zamówienie);

            System.out.println("Zamówienie zawiera " + zamówienie.getProdukty().size() + " produkty");
            //iteracja po liście produktów celem wypisania ich ilości z zamówienia
            int a = 0; // zmienna potrzebna by uzupełniać ilość dostarczonych produktów
            for (Produkt produkt : zamówienie.getProdukty()) {
                System.out.println("Czy w dostawie znajduje się produkt (tak/nie): " + produkt.opiszProdukt());
                //uzupełnianie magazynu dla dostarczonych produktów
                try {
                    switch (scanner.nextLine().toUpperCase()) {
                        case ("TAK"):
                            produkt.setCzyDostarczony(true);
                            magazyn.dodawanieDoMagazynu(produkt, magazyn.produktyWMagazynie);
                            a++;
                            zamówienie.setProduktyDostarczone(a);
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
            String numerFv = scanner.nextLine();
            zamówienie.setNumerFaktury(numerFv);
            if (magazyn.listaZamówieńZrealizowanych.values().contains(numerFv)){
                throw new PodanyNumerFakturyIstnieje();
            }
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
                        String czas = dateTimeFormatter.format(LocalDateTime.now());
                        zamówienie.setDataDostarczenia(LocalDateTime.parse(czas, dateTimeFormatter));
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
                zamówienie.setCzyOpozniony(true);
                zamówienie.setOIleOpozniony(czasRealizacji);
            }

        } else {
            System.err.println("Brak zamówienia o podanym numerze!");
        }


    }


}

