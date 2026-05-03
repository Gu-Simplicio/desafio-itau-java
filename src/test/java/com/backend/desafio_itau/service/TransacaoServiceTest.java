package com.backend.desafio_itau.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import com.backend.desafio_itau.model.Transacao;

public class TransacaoServiceTest {
    private TransacaoService transacaoService = new TransacaoService();

    // TESTES POST
    @Test // caso perfeito
    public void criaTransacao_RetornaTrue(){
        // cria uma transação para testar a criação
        Transacao transacao = new Transacao();
        transacao.setValor(0.15);
        transacao.setDataHora(OffsetDateTime.now());

        // executa e testa
        boolean resultado = transacaoService.criaTransacao(transacao);
        assertEquals(true, resultado);
    }

    @Test // dataHora no futuro
    public void criaTransacao_DataHoraRetornaFalse() {
        // CHECAGEM DE dataHora FUTURA
        OffsetDateTime dataFutura = OffsetDateTime.now().plus(1, ChronoUnit.DAYS); // cria data 1 dia no futuro

        // cria a transação no futuro
        Transacao transacao = new Transacao();
        transacao.setValor(0);
        transacao.setDataHora(dataFutura);

        // executa e testa
        boolean resultado = transacaoService.criaTransacao(transacao);
        assertEquals(false, resultado);
    }

    // TESTES DELETE
    @Test 
    public void deletaTransacoes_RetornaTrue(){
        // cria uma transação
        Transacao transacao = new Transacao();
        transacao.setValor(0);
        transacao.setDataHora(OffsetDateTime.now());

        transacaoService.criaTransacao(transacao);

        boolean resultado = transacaoService.deletaTransacoes();
        assertEquals(true, resultado);
    }
}
