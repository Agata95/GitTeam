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

    @Override
    public String toString() {
        return "Zamówienie numer: " + numer +
                ", zamówione produkty: " + produkty +"\n" +
                ", data zamówienia: " + dataZamówienia +
                ", data dostarczenia: " + dataDostarczenia +
                ", numer faktury: " + numerFaktury + '\'' +
                ", produkty dostarczone: " + produktyDostarczone + "\n"+
                ", czy opoznione: " + czyOpozniony +
                ", o ile opóźnione: " + oIleOpozniony ;
    }
}
