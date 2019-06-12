package com.javagda25.GitTeam;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Produkt {
    private String nazwa;
    private double cena;
    private double ilość;
    private boolean czyDostarczony;

    public String wypiszProdukt(){
        return nazwa + ", " + cena+ ", "+ilość;
    }

}
