package com.javagda25.GitTeam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Produkt {
    private String nazwa;
    private double cena;
    private double ilość;
    private boolean czyDostarczony;

    public String opiszProdukt() {
        return nazwa + ", cena: " + cena + "zł, ilość: " + ilość + " sztuk";
    }


    public String wypiszProdukt() {
        return "Produkt = " + nazwa + "\n" +
                "cena = " + cena + "\n" +
                "ilość = " + ilość +"\n*";
    }


}
