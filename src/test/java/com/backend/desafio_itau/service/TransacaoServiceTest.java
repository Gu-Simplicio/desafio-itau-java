package com.backend.desafio_itau.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.OffsetDateTime;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.desafio_itau.model.Estatisticas;
import com.backend.desafio_itau.model.Transacao;

public class TransacaoServiceTest {
    private TransacaoService transacaoService = new TransacaoService();

    @BeforeEach
    void setUpTransacao() {
        transacaoService = new TransacaoService();
    }

    // TESTES POST
    
    @Test // valor negativo
    public void criaTransacao_ValorNegativo(){
        Transacao transacao = new Transacao();
        transacao.setValor(-5.10);
        transacao.setDataHora(OffsetDateTime.now());

        // espera um erro de argumento
        assertThrows(
            IllegalArgumentException.class, 
            () -> transacaoService.criaTransacao(transacao));
    }

    @Test // caso dataHora no futuro
    public void criaTransacao_DataHoraFutura(){
        OffsetDateTime dataFutura = OffsetDateTime.now().plusDays(5);
        // cria uma transação para testar a criação
        Transacao transacao = new Transacao();
        transacao.setValor(0.15);
        transacao.setDataHora(dataFutura);

        // expera receber um erro do tipo IllegalArgumentException
        assertThrows(
            IllegalArgumentException.class, 
            () -> transacaoService.criaTransacao(transacao));
    }

    @Test // caso dataHora seja nulo
    public void criaTransacao_DataHoraNula() {
        OffsetDateTime dataFutura = null;
        // cria uma transação para testar a criação
        Transacao transacao = new Transacao();
        transacao.setValor(0.15);
        transacao.setDataHora(dataFutura);

        // expera receber um erro do tipo IllegalArgumentException
        assertThrows(
            NullPointerException.class, 
            () -> transacaoService.criaTransacao(transacao));
    }

    @Test // tudo certo
    public void criaTransacao_tudoCerto() {
        Transacao novaTransacao = new Transacao();
        novaTransacao.setValor(10.50);
        novaTransacao.setDataHora(OffsetDateTime.now());
        
        // recebe o tamanho atual da lista de transações
        int tamanhoAtual = transacaoService.recebeTransacoes().size();
        transacaoService.criaTransacao(novaTransacao);
        // recebe o tamanho após a inserção
        int tamanhoNovo = transacaoService.recebeTransacoes().size();

        assertEquals(tamanhoAtual, tamanhoNovo - 1);
    }

    // TESTES GET
    @Test
    public void recebeEstatisticas_TresValidas(){
        // transação dentro do período 
        Transacao transacaoNoPeriodo = new Transacao();
        transacaoNoPeriodo.setValor(10.0);
        transacaoNoPeriodo.setDataHora(OffsetDateTime.now());
        // transação fora do período
        Transacao transacaoFora = new Transacao();
        transacaoFora.setValor(10.0);
        transacaoFora.setDataHora(OffsetDateTime.now().minusMinutes(3));

        for(int i = 0; i < 3; i++){
            transacaoService.criaTransacao(transacaoNoPeriodo);
            transacaoService.criaTransacao(transacaoFora);
        }

        Estatisticas estatisticas = transacaoService.recebeEstatisticas(60); // recebe estatísticas dentro de 60 segs

        assertEquals(3.0, estatisticas.getCount(), 0.001);
        assertEquals(10.0, estatisticas.getAvg(), 0.001);
        assertEquals(10.0, estatisticas.getMax(), 0.001);
        assertEquals(10.0, estatisticas.getMin(), 0.001);
        assertEquals(30.0, estatisticas.getSum(), 0.001);
    }

    @Test 
    public void recebeEstatisticas_TudoZero(){ // deve retornar todos os valores como 0.0
        Estatisticas estatisticas = transacaoService.recebeEstatisticas(1);

        assertEquals(0.0, estatisticas.getCount(), 0.001);
        assertEquals(0.0, estatisticas.getAvg(), 0.001);
        assertEquals(0.0, estatisticas.getMax(), 0.001);
        assertEquals(0.0, estatisticas.getMin(), 0.001);
        assertEquals(0.0, estatisticas.getSum(), 0.001);
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
