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
        return "Zamówienie numer = " + numer + "\n" +
//                "zamówione produkty = " + produkty +"\n" +
                "data zamówienia = " + dataZamówienia+"\n$"
                ;}

    public String wypiszZrealizowane() {
        return "Zamówienie numer = " + numer + "\n" +
//                "zamówione produkty = " + produkty +"\n" +
                "data zamówienia = " + dataZamówienia +"\n"+
                "data dostarczenia = " + dataDostarczenia + "\n" +
                "numer faktury = " + numerFaktury+"\n#"
                ;}
}
