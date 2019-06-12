package com.javagda25.GitTeam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;


@AllArgsConstructor
@NoArgsConstructor

public class Magazyn {
    Map<String, Produkt> produktyWMagazynie = new HashMap<>();
    Map<String, Zamówienie> listaZamówień = new HashMap<>();


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

        listaZamówień.put(zamówienie.getNumer(), zamówienie);
//        System.out.println(listaZamówień);

        return zamówienie.getNumer();
    }

    public void listujZamowienia(Map<String, Zamówienie> listaZamówień) {
        Set<Map.Entry<String, Zamówienie>> pary = listaZamówień.entrySet();

        for (Map.Entry<String, Zamówienie> s : pary) {
            System.out.println(s.getKey() + " " + s.getValue());
        }

    }

}
