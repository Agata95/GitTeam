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
    private String numerFaltury;


}
