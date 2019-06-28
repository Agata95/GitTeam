package com.javagda25.GitTeam;

public class PodanyNumerZamówieniaIstnieje extends Exception {
    public PodanyNumerZamówieniaIstnieje() {
        super("Podany numer zamówienia już istnieje. Spróbuj jeszcze raz.\n");
    }
}
