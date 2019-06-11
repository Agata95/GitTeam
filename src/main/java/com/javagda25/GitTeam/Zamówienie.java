package com.javagda25.GitTeam;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Data
@AllArgsConstructor

public class Zamówienie {
    private int numer;
    private List<Produkt> produkty;
    private LocalDateTime dataZamówienia;
    private LocalDateTime dataDostarczenia;
    private String numerFaltury;
}
