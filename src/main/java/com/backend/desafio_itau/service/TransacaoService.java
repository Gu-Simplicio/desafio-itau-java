package com.backend.desafio_itau.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.backend.desafio_itau.model.Estatisticas;
import com.backend.desafio_itau.model.Transacao;

@Service
public class TransacaoService {
    private static final Logger logger = LoggerFactory.getLogger(TransacaoService.class);
    // Lista de todas as transações já salvas
    private List<Transacao> transacoes = new ArrayList<Transacao>();

    // POST
    public void criaTransacao(Transacao transacao){
        // checa se o valor é válido
        if(transacao.getValor() == null || transacao.getValor().doubleValue() < 0){
            throw new IllegalArgumentException("valor inválido enviado!");
        }

        // checa se dataHora é válido   
        OffsetDateTime agora = OffsetDateTime.now();
        if(transacao.getDataHora().isAfter(agora) || transacao.getDataHora() == null){
            throw new IllegalArgumentException("dataHora inválida!");            
        }

        // CASO ESTEJA TUDO CERTO!
        transacoes.add(transacao);
        logger.info("Nova transação salva, tamanho atual da lista: {}", transacoes.size());
    }

    // GET - estatisticas
    public Estatisticas recebeEstatisticas(int periodo){
        OffsetDateTime periodoMax = OffsetDateTime.now().minusSeconds(periodo); // período onde os valores devem estar
        List<Double> valoresTransacionados = new ArrayList<>(); // lista que terá os valores no período

        // insere os valores dentro do período de 60 segundos dentro da lista
        for(Transacao t : this.transacoes){
            if(!t.getDataHora().isBefore(periodoMax)){
                Double valor = t.getValor().doubleValue();
                valoresTransacionados.add(valor);
            }
        } 

        Estatisticas estatisticas = new Estatisticas(valoresTransacionados);

        return estatisticas;
    }

    public List<Transacao> recebeTransacoes(){
        return transacoes;
    }

    // DELETE
    public boolean deletaTransacoes() {
        transacoes.clear();
        return true;
    }
}
