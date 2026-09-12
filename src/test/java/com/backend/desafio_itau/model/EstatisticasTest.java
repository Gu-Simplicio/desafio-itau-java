package com.backend.desafio_itau.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EstatisticasTest {
    // CHECA ERROS
    @Test 
    public void estatisticas_TransacoesNulas() {
        Estatisticas estatisticasNulas = new Estatisticas(null);

        // checa se os valores estão realmente zerados 
        assertEquals(estatisticasNulas.getAvg(), 0.0, 0.001);
        assertEquals(estatisticasNulas.getCount(), 0.0, 0.001);
        assertEquals(estatisticasNulas.getMax(), 0.0, 0.001);
        assertEquals(estatisticasNulas.getMin(), 0.0, 0.001);
        assertEquals(estatisticasNulas.getSum(), 0.0, 0.001);
    }

    @Test
    public void estatisticas_TransacoesZeradas() {
        ArrayList<Double> transacoesZeradas = new ArrayList<>();
        Estatisticas estatisticasZeradas = new Estatisticas(transacoesZeradas);

        // checa se os valores estão realmente zerados 
        assertEquals(estatisticasZeradas.getAvg(), 0.0, 0.001);
        assertEquals(estatisticasZeradas.getCount(), 0.0, 0.001);
        assertEquals(estatisticasZeradas.getMax(), 0.0, 0.001);
        assertEquals(estatisticasZeradas.getMin(), 0.0, 0.001);
        assertEquals(estatisticasZeradas.getSum(), 0.0, 0.001);
    }

    // CHECA ACERTOS
    @Test 
    public void estatisticas_calculoCorreto() {
        ArrayList<Double> transacoesCorretas = new ArrayList<>();  
        // popula o ArrayList
        for(int i = 0; i < 5; i++) transacoesCorretas.add(10.0);

        Estatisticas estatisticas = new Estatisticas(transacoesCorretas);

        // checa se os valores estão calculados corretamente
        assertEquals(estatisticas.getAvg(), 10.0, 0.001);
        assertEquals(estatisticas.getCount(), 5, 0.001);
        assertEquals(estatisticas.getMax(), 10.0, 0.001);
        assertEquals(estatisticas.getMin(), 10.0, 0.001);
        assertEquals(estatisticas.getSum(), 50.0, 0.001);
    }
}