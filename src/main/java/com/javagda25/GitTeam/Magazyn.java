package com.javagda25.GitTeam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor

public class Magazyn {
    Map<String, Produkt> produktyWMagazynie = new HashMap<>();
    Map<String, Zamówienie> listaZamówieńNieZrealizowanych = new HashMap<>();
    Map<String, Zamówienie> listaZamówieńZrealizowanych = new HashMap<>();
    Map<String, Zamówienie> listaSprzedanychProduktow = new HashMap<>();





    public void listowanie(Map<String, Zamówienie> listaZamówieńNieZrealizowanych) {
            Set<Map.Entry<String, Zamówienie>> p = listaZamówieńNieZrealizowanych.entrySet();

            for (Map.Entry<String, Zamówienie> s : p) {
                System.out.println("Numer zamówienia: " + s.getKey() + " " + s.getValue().getProdukty());
            }
    }

    public void listowanieDostaw(Map<String, Zamówienie> listaZamówieńZrealizowanych) {
            Set<Map.Entry<String, Zamówienie>> p = listaZamówieńZrealizowanych.entrySet();

            for (Map.Entry<String, Zamówienie> s : p) {
                System.out.println("Numer zamówienia: " + s.getKey() + " Numer faktury: " + s.getValue().getNumerFaktury() +
                        " Data zamówienia: " + s.getValue().getDataZamówienia() + " Data dostarczenia: " + s.getValue().getDataDostarczenia()
                        + " Ilość produktów dostarczonych: " + s.getValue().getProduktyDostarczone() + " Czy opóźnione: " +
                        s.getValue().getCzyOpozniony() + " Opóźniony o: " + s.getValue().getOIleOpozniony());
            }
    }

    public void listowanieProduktow(Map<String, Produkt> produktyWMagazynie) {
            Map<String, Produkt> p = produktyWMagazynie;
            Map<String, Double> r = new HashMap<>();
            double licznik = 0;

            for (Map.Entry<String, Produkt> s : p.entrySet()) {
                licznik = s.getValue().getIlość();
                if (s.getValue().getNazwa().equals(p.entrySet())) {
                    licznik += s.getValue().getIlość();
                }
                r.put(s.getValue().getNazwa(), licznik);
            }
            System.out.println(r);
    }

    public void dodawanieDoMagazynu(Produkt produkt, Map<String, Produkt> produktyWMagazynie) {
        double licznik = 0;

        licznik = produkt.getIlość();
        for (Map.Entry<String, Produkt> s : produktyWMagazynie.entrySet()) {
            if (s.getKey().equals(produkt.getNazwa())) {
                licznik += s.getValue().getIlość();
            }
        }
        produkt.setIlość(licznik);
        produktyWMagazynie.put(produkt.getNazwa(), produkt);


    }




    public void zapisz() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("Stany_magazynowe.txt", false))) {
            printWriter.println("PRODUKTY W MAGAZYNIE ->");
            for (Produkt produkt : produktyWMagazynie.values()) {
                printWriter.println(produkt.wypiszProdukt());
            }
            printWriter.println("ZAMÓWIENIA NIEZREALIZOWANE ->");
            for (Zamówienie zamówienie : listaZamówieńNieZrealizowanych.values()) {
                printWriter.println(zamówienie.wypiszNieZrealizowane());
            }
            printWriter.println("ZAMÓWIENIA ZREALIZOWANE ->");
            for (Zamówienie zamówienie : listaZamówieńZrealizowanych.values()) {
                printWriter.println(zamówienie.wypiszZrealizowane());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void wczytaj() {

        try (BufferedReader reader = new BufferedReader(new FileReader("Stany_magazynowe.txt"))) {
            String linia;
            Produkt produkt = null;
            Zamówienie zamówienie = null;

            while ((linia = reader.readLine()) != null) {
                if (linia.equals("PRODUKTY W MAGAZYNIE ->") || linia.equals("*")) {
                    if (produkt != null) {
                        dodawanieDoMagazynu(produkt, produktyWMagazynie);
                    }
                    produkt = new Produkt();

                } else {
                    parsujLinieProdukt(linia, produkt);
                }
                if (linia.equals("ZAMÓWIENIA NIEZREALIZOWANE ->") || linia.equals("$")) {
                    if (zamówienie != null && zamówienie.getNumer() != null) {
                        listaZamówieńNieZrealizowanych.put(zamówienie.getNumer(), zamówienie);
                    }
                    zamówienie = new Zamówienie();

                } else {
                    parsujLinieZamówienie(linia, zamówienie);
                }
                if (linia.equals("ZAMÓWIENIA ZREALIZOWANE ->") || linia.equals("#")) {
                    if (zamówienie != null && zamówienie.getNumer() != null) {
                        listaZamówieńZrealizowanych.put(zamówienie.getNumer(), zamówienie);
                    }
                    zamówienie = new Zamówienie();

                } else {
                    parsujLinieZamówienie(linia, zamówienie);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    private void parsujLinieProdukt(String linia, Produkt produkt) {
        String[] informacje = linia.split(" = ");
        switch (informacje[0]) {
            case ("Produkt"):
                produkt.setNazwa(informacje[1]);
                break;
            case "cena":
                produkt.setCena(Double.parseDouble(informacje[1]));
                break;
            case "ilość":
                produkt.setIlość(Double.parseDouble(informacje[1]));
                break;
        }
    }


    private void parsujLinieZamówienie(String linia, Zamówienie zamówienie) {
        String[] informacje = linia.split(" = ");
        switch (informacje[0]) {
            case "Zamówienie numer":
                zamówienie.setNumer(informacje[1]);
                break;
//            case "zamówione produkty : \n ":
//                zamówienie.setProdukty(null);
//                break;
            case "data zamówienia":
                zamówienie.setDataZamówienia(LocalDateTime.parse(informacje[1]));
                break;
            case "data dostarczenia":
                zamówienie.setDataDostarczenia(LocalDateTime.parse(informacje[1]));
                break;
            case "numer faktury":
                zamówienie.setNumerFaktury(informacje[1]);
                break;
        }
    }
}

