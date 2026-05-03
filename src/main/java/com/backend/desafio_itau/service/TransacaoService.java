package com.backend.desafio_itau.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.desafio_itau.model.Estatisticas;
import com.backend.desafio_itau.model.Transacao;

@Service
public class TransacaoService {
    // Lista de todas as transações já salvas
    private List<Transacao> transacoes = new ArrayList<Transacao>();

    // POST
    public boolean criaTransacao(Transacao transacao){
        // checa se dataHora é válido   
        OffsetDateTime agora = OffsetDateTime.now();
        if(transacao.getDataHora().isAfter(agora)){
            System.err.println("dataHora do futuro detectada");
            return false;
        }

        // CASO ESTEJA TUDO CERTO!
        transacoes.add(transacao);
        System.out.println("Transação salva!");
        System.out.println("Tamanho atual da lista: " + transacoes.size());
        return true;
    }

    // GET
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

    // DELETE
    public boolean deletaTransacoes() {
        transacoes.clear();
        System.out.println("Todas as transações deletadas com sucesso!");
        return true;
    }
}
