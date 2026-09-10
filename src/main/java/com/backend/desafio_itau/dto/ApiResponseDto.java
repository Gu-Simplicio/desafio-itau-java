package com.backend.desafio_itau.dto;

public class ApiResponseDto<T> {
    private boolean sucesso;    
    private String mensagem;
    private T dados;

    // RESPOSTA COM TODOS OS DADOS 
    public ApiResponseDto(boolean s, String m, T d){
        this.sucesso = s;
        this.mensagem = m;
        this.dados = d;
    }

    // RESPOTA SEM DADOS
    public ApiResponseDto(boolean s, String m) {
        this.sucesso = s;
        this.mensagem = m;
        this.dados = null;
    }

    // GETTERS e SETTERS
    public boolean getSucesso() { return this.sucesso; }
    public String getMensagem() { return this.mensagem; }
    public T getDados() { return this.dados; }

    public void setSucesso(boolean s) { this.sucesso = s; }
    public void setMensagem(String m) { this.mensagem = m; }
    public void setDados(T d) { this.dados = d; }
}