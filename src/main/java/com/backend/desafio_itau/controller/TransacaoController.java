package com.backend.desafio_itau.controller;

import java.util.List;

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

import com.backend.desafio_itau.dto.ApiResponseDto;
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
    public ResponseEntity<ApiResponseDto<Void>> criaTransacao(@RequestBody Transacao transacao){ 
        try{ // tenta criar uma nova transação
            transacaoService.criaTransacao(transacao);

            ApiResponseDto<Void> response = new ApiResponseDto<>(true, "Transação criada com sucesso!");

            System.err.println("Tudo certo!");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch(IllegalArgumentException e){  //Dados inválidos enviados
            ApiResponseDto<Void> response = new ApiResponseDto<>(false, "Dados inválidos enviados: " + e.getMessage());

            System.err.println("Dados inválidos enviados");
            return ResponseEntity.unprocessableContent().body(response);
        } catch(Exception e){ // caso ocorra algum erro desconhecido
            ApiResponseDto<Void> response = new ApiResponseDto<>(false, "Dados inválidos enviados: " + e.getMessage());

            System.err.println("Erro desconhecido!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ENDPOINT GET - estatísticas
    @GetMapping("/estatistica")
    public ResponseEntity<ApiResponseDto<Estatisticas>> recebeEstatisticas(@RequestParam(value = "periodo", defaultValue = "60") int periodo){
        try{
            Estatisticas estatisticas = transacaoService.recebeEstatisticas(periodo);

            ApiResponseDto<Estatisticas> response = new ApiResponseDto<>(true, "Estatísticas calculadas com sucesso", estatisticas);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception e){ // caso ocorra algum erro inesperado
            ApiResponseDto<Estatisticas> response = new ApiResponseDto<>(false, "Erro ao calcular estatísticas: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // ENDPOINT GET - geral
    @GetMapping("/transacao")
    public ResponseEntity<ApiResponseDto<List<Transacao>>> recebeTransacoes() {
        try {
            List<Transacao> transacoes = transacaoService.recebeTransacoes();

            ApiResponseDto<List<Transacao>> response = new ApiResponseDto<>(true, "Transações efetuadas recebidas com sucesso", transacoes);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception e){
            ApiResponseDto<List<Transacao>> response = new ApiResponseDto<>(false, "Erro ao receber transações: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // ENDPOINT DELETE
    @DeleteMapping("/transacao")
    public ResponseEntity<ApiResponseDto<Void>> deletaTransacoes(){
        try {
            transacaoService.deletaTransacoes();

            ApiResponseDto<Void> response = new ApiResponseDto<>(true, "Transações deletadas com sucesso!");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception e){ // caso ocorra algum erro inesperado
            ApiResponseDto<Void> response = new ApiResponseDto<>(false, "Erro ao deletar transaç]oes: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
