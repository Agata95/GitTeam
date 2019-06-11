package com.javagda25.GitTeam;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor

public class Magazyn {
    Map<String, Produkt> produktyWMagazynie = new HashMap<>();
    Map<String, Zamówienie> listaZamówień = new HashMap<>();

}
