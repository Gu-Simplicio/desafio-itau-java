package com.backend.desafio_itau.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.backend.desafio_itau.service.TransacaoService;

@WebMvcTest(TransacaoController.class)
public class TransacaoControllerTest {
    @Autowired 
    private MockMvc mockMvc;

    @MockitoBean 
    private TransacaoService transacaoService;

    // POST
    @Test 
    public void criaTransacao_IllegalArgument_dataHoraFutura() throws Exception {
        // caso o transacaoService retorne um erro, o mock irá avisar
        doThrow(new IllegalArgumentException("dataHora inválida!")) 
            .when(transacaoService).criaTransacao(any());

        // exemplo de json inválido (sem dataHora)
        String jsonPayload = "{\"valor\": 50.00, \"dataHora\": \"2060-09-12T10:00:00Z\"}";

        // executa e testa o resultado
        mockMvc.perform(post("/unibanco/transacao")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonPayload))
                        .andExpect(status().isUnprocessableContent());
    }

    @Test
    public void criaTransacao_BadRequest_semDataHora() throws Exception {
        // caso o transacaoService retorne um erro, o mock irá avisar
        doThrow(new Exception("dataHora inválida!")) 
            .when(transacaoService).criaTransacao(any());

        // exemplo de json inválido (sem dataHora)
        String jsonPayload = "{\"valor\": 50.00\"}";

        // executa e testa o resultado
        mockMvc.perform(post("/unibanco/transacao")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonPayload))
                        .andExpect(status().isBadRequest());
    }

    @Test 
    public void criaTransacao_Sucesso() throws Exception{
        // obriga o Mock a não fazer nada quando transacaoService for chamado
        doNothing().when(transacaoService).criaTransacao(any());

        // exemplo de json que será enviado
        String jsonPayload = "{\"valor\": 50.00, \"dataHora\": \"2026-09-12T10:00:00Z\"}";

        // executa e testa o resultado
        mockMvc.perform(post("/unibanco/transacao")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonPayload))
                        .andExpect(status().isCreated());
    }

    // GET 
    @Test 
    public void recebeTransacoes_Sucesso() throws Exception{
        mockMvc.perform(get("/unibanco/transacao"))
                    .andExpect(status().isOk());
    }

    @Test 
    public void recebeEstatisticas_IllegalArgument_periodoNegativo() throws Exception{
        doThrow(new IllegalArgumentException("período inválido"))
            .when(transacaoService).recebeEstatisticas(anyInt());

        mockMvc.perform(get("/unibanco/estatistica?periodo=-5"))
                    .andExpect(status().isUnprocessableContent());
    }

    @Test 
    public void recebeEstatisticas_Sucesso() throws Exception{
        mockMvc.perform(get("/unibanco/estatistica"))
                    .andExpect(status().isOk());
    }
}
