package com.javagda25.GitTeam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Zamówienie {
    private String numer;
    private List<Produkt> produkty;
    private LocalDateTime dataZamówienia;
    private LocalDateTime dataDostarczenia;
    private String numerFaktury;

    // dodatkowe potrzebne zmienne
    private int produktyDostarczone;
    private Boolean czyOpozniony;
    private long oIleOpozniony;

    //   @Override
    public String wypiszNieZrealizowane() {
        StringBuilder builder = new StringBuilder();
        builder.append("zamówienie numer = " + numer + "\n");
        builder.append("zamówione produkty = \n");
        for (Produkt p : produkty) {
            builder.append(p.wypiszProdukt1());
        }
        builder.append("data zamówienia = " + dataZamówienia + "\n$");
        return builder.toString();
    }

    public String wypiszZrealizowane() {
        StringBuilder builder = new StringBuilder();
        builder.append("Zamówienie numer = " + numer + "\n");
        builder.append("Zamówione produkty = \n");
        for (Produkt p : produkty) {
            builder.append(p.wypiszProdukt1());
        }
        builder.append("Data zamówienia = " + dataZamówienia + "\n$");
        builder.append("Data dostarczenia = " + dataDostarczenia + "\n");
        builder.append("Numer faktury = " + numerFaktury + "\n#");
        return builder.toString();

    }
}
