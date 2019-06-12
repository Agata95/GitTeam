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


    public String dodajZamowienie(Zamówienie zamówienie) {
        Produkt produkt = new Produkt();
        Scanner scanner = new Scanner(System.in);
        List<Produkt> produkty = new ArrayList<>();

        System.out.println("Podaj ilość produktów na zamówieniu:");
        int iloscProduktow = scanner.nextInt();
        for (int i = 1; i < iloscProduktow + 1; i++) {
            System.out.println("Podaj nazwę produktu nr " + i);
            produkt.setNazwa(scanner.next());
            System.out.println("Podaj cenę produktu nr " + i);
            produkt.setCena(scanner.nextDouble());
            System.out.println("Podaj ilość produktu nr " + i);
            produkt.setIlość(scanner.nextDouble());

            produkty.add(produkt);
        }

        System.out.println("Podaj numer zamówienia:");
        String numerZamowienia = scanner.next();

        zamówienie.setProdukty(produkty);
        listaZamówień.put(numerZamowienia, zamówienie);
        return numerZamowienia;
    }

}
