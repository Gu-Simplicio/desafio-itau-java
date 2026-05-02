package com.backend.desafio_itau.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.desafio_itau.model.Transacao;

@Service
public class TransacaoService {
    // Lista de todas as transações já salvas
    private List<Transacao> transacoes = new ArrayList<Transacao>();

    public boolean criaTransacao(Transacao transacao){
        // checa se dataHora é válido   
        OffsetDateTime now = OffsetDateTime.now();
        if(transacao.getDataHora().isAfter(now)){
            System.err.println("dataHora do futuro detectada");
            return false;
        }

        // CASO ESTEJA TUDO CERTO!
        transacoes.add(transacao);
        System.out.println("Transação salva!");
        System.out.println("Tamanho atual da lista: " + transacoes.size());
        return true;
    }
}
