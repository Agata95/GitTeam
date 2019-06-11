package com.javagda25.GitTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Magazyn magazyn = new Magazyn();
        skladanieZamowien(magazyn);


    }

    public static String skladanieZamowien(Magazyn magazyn) {
        Produkt produkt = new Produkt();
        Zamówienie zamówienie = new Zamówienie();
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
        magazyn.listaZamówień.put(numerZamowienia, zamówienie);
        return numerZamowienia;
    }
}
