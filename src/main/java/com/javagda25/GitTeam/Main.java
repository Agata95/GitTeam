package com.javagda25.GitTeam;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Magazyn magazyn = new Magazyn();

        //dane stworzone do testów
//        Produkt p1 = new Produkt("chleb", 2.3, 5, false);
//        Produkt p2 = new Produkt("maka", 2.3, 5, false);
//        Produkt p3 = new Produkt("jaja", 2.3, 5, false);
//        Produkt p4 = new Produkt("mleko", 2.3, 5, false);
//        List <Produkt> produkts = new ArrayList<>();
//        produkts.add(p1);
//        produkts.add(p2);
//        produkts.add(p3);
//        produkts.add(p4);
//
//
//       Zamówienie z1 = new Zamówienie("RR1212", produkts, LocalDateTime.now(), LocalDateTime.now(), "------" );
//       List<Zamówienie> lz = new ArrayList<>();
//
//       magazyn.listaZamówień.put("RR1212", z1);
//
//       dodajDostawe(magazyn);


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
                        } catch (DateTimeParseException dte){
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

            if (czasRealizacji > 60){
                System.out.println("Dosatwa zrealizowana z opóźnieniem!");
            }

        } else {
            System.err.println("Brak zamówienia o podanym numerze!");
        }


    }

}
