package com.javagda25.GitTeam;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@AllArgsConstructor

public class Magazyn {
    List<Produkt> produktyWMagazynie;
    Set<Zamówienie> listaZamówień;

}
