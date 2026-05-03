package com.backend.desafio_itau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.desafio_itau.model.Transacao;
import com.backend.desafio_itau.service.TransacaoService;

@RestController
@RequestMapping("/unibanco")
public class TransacaoController {
    @Autowired
    TransacaoService transacaoService;

    // ENDPOINT POST
    @PostMapping("/transacao") 
    public ResponseEntity<Void> criaTransacao(@RequestBody Transacao transacao){ 
        // caso a transação enviada seja inválida
        if(transacao.getValor() == null || transacao.getValor().doubleValue() < 0 || transacao.getDataHora() == null){
            System.err.println("Valor inválido enviado na requisição");
            return ResponseEntity.unprocessableContent().build();
        }
        // recebe um valor caso a transação seja bem sucedida
        boolean transacaoCriada = transacaoService.criaTransacao(transacao);

        if(!transacaoCriada) {
            System.err.println("Erro ao salvar transação");
            return ResponseEntity.badRequest().build();
        }
        System.err.println("Transação salva com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // ENDPOINT DELETE
    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deletaTransacoes(){
        transacaoService.deletaTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
