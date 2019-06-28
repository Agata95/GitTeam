package com.javagda25.GitTeam;

public class PodanyNumerFakturyIstnieje extends Exception {
    public PodanyNumerFakturyIstnieje() {
        super("Podany numer faktury już istnieje. Spróbuj jeszcze raz.\n");
    }
}
