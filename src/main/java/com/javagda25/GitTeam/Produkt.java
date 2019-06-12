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

    public String wypiszProdukt(){
        return nazwa + ", " + cena+ ", "+ilość;
    }

}
