package com.backend.desafio_itau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.desafio_itau.model.Estatisticas;
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
        try{ // tenta criar uma nova transação
            transacaoService.criaTransacao(transacao);
            System.err.println("Tudo certo!");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(IllegalArgumentException e){  //Dados inválidos enviados
            System.err.println("Dados inválidos enviados");
            return ResponseEntity.unprocessableContent().build();
        } catch(Exception e){ // caso ocorra algum erro desconhecido
            System.err.println("Erro desconhecido!");
            return ResponseEntity.badRequest().build();
        }
    }

    // ENDPOINT GET
    @GetMapping("/estatistica")
    public ResponseEntity<Estatisticas> recebeEstatisticas(@RequestParam(value = "periodo", defaultValue = "60") int periodo){
        Estatisticas estatisticas = transacaoService.recebeEstatisticas(periodo);

        return ResponseEntity.status(HttpStatus.OK).body(estatisticas);
    }

    // ENDPOINT DELETE
    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deletaTransacoes(){
        transacaoService.deletaTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
