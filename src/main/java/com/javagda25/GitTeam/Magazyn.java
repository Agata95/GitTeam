package com.javagda25.GitTeam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor

public class Magazyn {
    Map<String, Produkt> produktyWMagazynie = new HashMap<>();
    Map<String, Zamówienie> listaZamówień = new HashMap<>();
    Map<String, Zamówienie> listaZamówieńNieZrealizowanych = listaZamówień;
    Map<String, Zamówienie> listaZamówieńZrealizowanych = new HashMap<>();
    Map<String, Zamówienie> listaSprzedanychProduktow = new HashMap<>();


    public String dodajZamowienie() {
        Scanner scanner = new Scanner(System.in);
        List<Produkt> produkty = new ArrayList<>();

        Zamówienie zamówienie = new Zamówienie();
        System.out.println("Podaj ilość produktów na zamówieniu:");
        int iloscProduktow = scanner.nextInt();
        int i = 1;

        while (i <= iloscProduktow) {
            Produkt produkt = new Produkt();
            System.out.println("Podaj nazwę produktu nr " + i);
            produkt.setNazwa(scanner.next());
            System.out.println("Podaj cenę produktu nr " + i);
            produkt.setCena(scanner.nextDouble());
            System.out.println("Podaj ilość produktu nr " + i);
            produkt.setIlość(scanner.nextDouble());

            produkty.add(produkt);
            i++;
        }

        System.out.println("Podaj numer zamówienia:");
        String numerZamowienia = scanner.next();
        zamówienie.setNumer(numerZamowienia);
        System.out.println("Zamówienie złożone. Nr zamówienia: " + zamówienie.getNumer());

        zamówienie.setProdukty(produkty);
        zamówienie.setDataZamówienia(LocalDateTime.now());

        listaZamówień.put(zamówienie.getNumer(), zamówienie);
//        System.out.println(listaZamówień);

        return zamówienie.getNumer();
    }

    public void listowanie(Map<String, Zamówienie> listaZamówieńNieZrealizowanych) {
        Set<Map.Entry<String, Zamówienie>> p = listaZamówieńNieZrealizowanych.entrySet();

        for (Map.Entry<String, Zamówienie> s : p) {
            System.out.println("Numer zamówienia: " + s.getKey() + " " + s.getValue().getProdukty());
        }
    }

    public void listowanieDostaw(Map<String, Zamówienie> listaZamówieńZrealizowanych) {
        Set<Map.Entry<String, Zamówienie>> p = listaZamówieńZrealizowanych.entrySet();

        for (Map.Entry<String, Zamówienie> s : p) {
            System.out.println("Numer zamówienia: " + s.getKey() + " Numer faktury: " + s.getValue().getNumerFaltury() +
                    " Data zamówienia: " + s.getValue().getDataZamówienia() + " Data dostarczenia: " + s.getValue().getDataDostarczenia()
                    + " Ilość produktów dostarczonych: " + s.getValue().getProduktyDostarczone() + " Czy opóźnione: " +
                    s.getValue().getCzyOpozniony() + " Opóźniony o: " + s.getValue().getOIleOpozniony());
        }
    }

    public void listowanieProduktow(Map<String, Produkt> produktyWMagazynie) {
        Map<String, Produkt> p = produktyWMagazynie;
        Map<String, Double> r = new HashMap<>();
//        Map<String, Double> produktyIlosc = new HashMap<>();
        double licznik = 0;


        for (Map.Entry<String, Produkt> s : p.entrySet()) {
            licznik = s.getValue().getIlość();
            if (s.getValue().getNazwa().equals(p.entrySet())) {
                licznik += s.getValue().getIlość();
            }
            r.put(s.getValue().getNazwa(), licznik);
        }

//        for (Map.Entry<String, Double> s : produktyIlosc.entrySet()) {
//
//            if(s.getValue().equals(produktyWMagazynie.get(s.getKey()))){
//                licznik++;
//            }
//        }

//        if(produktyWMagazynie.containsValue(produktyIlosc)){
//            licznik++;
//        }

        System.out.println(r);
        System.out.println(licznik);

//        for (Map.Entry<String, Double> s : produktyIlosc.entrySet()) {
//            System.out.println(s);
//
//        }
    }

    public void dodawanieDoMagazynu(Produkt produkt, Map<String, Produkt> produktyWMagazynie) {
        double licznik = 0;


//        produktyWMagazynie.put(produkt.getNazwa(), produkt);

        licznik = produkt.getIlość();
        for (Map.Entry<String, Produkt> s : produktyWMagazynie.entrySet()) {
            if (s.getKey().equals(produkt.getNazwa())) {
                licznik += s.getValue().getIlość();
            }
        }
        produkt.setIlość(licznik);
        produktyWMagazynie.put(produkt.getNazwa(), produkt);


    }

    public void sprzedaz(Map<String, Produkt> produktyWMagazynie) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer zamówienia");
        String numerZamówienia = scanner.nextLine();

        // sprawdzenie, czy wpisany numer zamowienia istnieje
        if (listaZamówieńZrealizowanych.containsKey(numerZamówienia)) {
            Zamówienie zamówienie = listaZamówieńZrealizowanych.get(numerZamówienia);


            System.out.println("Zamówienie zawiera " + zamówienie.getProdukty().size() + " produkty/ów.");
            // ile produktów dostarczono na magazyn z produktów zamówionych?
            System.out.println("Dostarczono : " + zamówienie.getProduktyDostarczone() + " produkty/ów.");

            // dodaje do listy sprzedanych produktow
            listaSprzedanychProduktow.put(zamówienie.getNumer(), zamówienie);
            System.out.println(listaSprzedanychProduktow);

            // usuwanie z magazynu sprzedanych produktów
            for (Produkt produkt : zamówienie.getProdukty()) {
                double iloscSprzedanegoZamowienia = produkt.getIlość();
                for (Map.Entry<String, Produkt> s : produktyWMagazynie.entrySet()) {
                    if (produkt.getNazwa().equalsIgnoreCase(s.getKey())) {
                        double iloscWMagazynie = s.getValue().getIlość();
                        s.getValue().setIlość(iloscWMagazynie-iloscSprzedanegoZamowienia);
                    }
//                    if(s.getValue().getIlość()==0){
//                        produktyWMagazynie.remove(s);
//                    }
                }
            }
        } else {
            System.err.println("Brak zamówienia o podanym numerze!");
        }
    }

}
