package com.backend.desafio_itau.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(TransacaoController.class);

    @Autowired
    TransacaoService transacaoService;

    // ENDPOINT POST
    @PostMapping("/transacao") 
    public ResponseEntity<ApiResponseDto<Void>> criaTransacao(@RequestBody Transacao transacao){ 
        logger.info("Iniciando criação de nova transação");
        try{ // tenta criar uma nova transação
            transacaoService.criaTransacao(transacao);

            ApiResponseDto<Void> response = new ApiResponseDto<>(true, "Transação criada com sucesso!");

            logger.info("Transação criada com sucesso");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch(IllegalArgumentException e){  //Dados inválidos enviados
            ApiResponseDto<Void> response = new ApiResponseDto<>(false, "Dados inválidos enviados: " + e.getMessage());

            logger.error("Dados inválidos enviados: ", e);
            return ResponseEntity.unprocessableContent().body(response);
        } catch(Exception e){ // caso ocorra algum erro desconhecido
            ApiResponseDto<Void> response = new ApiResponseDto<>(false, "Dados inválidos enviados: " + e.getMessage());

            logger.error("Erro inesperado ao criar transação: ", e);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ENDPOINT GET - estatísticas
    @GetMapping("/estatistica")
    public ResponseEntity<ApiResponseDto<Estatisticas>> recebeEstatisticas(@RequestParam(value = "periodo", defaultValue = "60") int periodo){
        logger.info("Iniciando busca de estatísticas no período de {} segundos", periodo);
        try{
            Estatisticas estatisticas = transacaoService.recebeEstatisticas(periodo);

            ApiResponseDto<Estatisticas> response = new ApiResponseDto<>(true, "Estatísticas calculadas com sucesso", estatisticas);

            logger.info("Estatísticas no período de {} segundos retornadas com sucesso", periodo);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception e){ // caso ocorra algum erro inesperado
            ApiResponseDto<Estatisticas> response = new ApiResponseDto<>(false, "Erro ao calcular estatísticas: " + e.getMessage());

            logger.error("Erro inesperado ao buscar estatísticas: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // ENDPOINT GET - geral
    @GetMapping("/transacao")
    public ResponseEntity<ApiResponseDto<List<Transacao>>> recebeTransacoes() {
        logger.info("Inicando busca de todas as transações");
        try {
            List<Transacao> transacoes = transacaoService.recebeTransacoes();

            logger.debug("Total de transações recuperado: {}", transacoes.size());

            ApiResponseDto<List<Transacao>> response = new ApiResponseDto<>(true, "Transações efetuadas recebidas com sucesso", transacoes);

            logger.info("Transações retornadas com sucesso. Retornando {} registros", transacoes.size());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception e){
            ApiResponseDto<List<Transacao>> response = new ApiResponseDto<>(false, "Erro ao receber transações: " + e.getMessage());

            logger.error("Erro inesperado ao buscar transações: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // ENDPOINT DELETE
    @DeleteMapping("/transacao")
    public ResponseEntity<ApiResponseDto<Void>> deletaTransacoes(){
        logger.info("Inicando deleção de todas as transações");
        try {
            transacaoService.deletaTransacoes();

            ApiResponseDto<Void> response = new ApiResponseDto<>(true, "Transações deletadas com sucesso!");

            logger.info("Transações deletadas com sucesso");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception e){ // caso ocorra algum erro inesperado
            ApiResponseDto<Void> response = new ApiResponseDto<>(false, "Erro ao deletar transaç]oes: " + e.getMessage());

            logger.error("Erro inesperado ao deletar transações: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
