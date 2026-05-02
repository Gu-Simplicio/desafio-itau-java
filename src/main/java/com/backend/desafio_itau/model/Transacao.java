package com.backend.desafio_itau.model;

import java.time.OffsetDateTime;

public class Transacao {
    private Double valor;
    private OffsetDateTime dataHora;
    
    // GETTERS
    public Double getValor(){
        return this.valor;
    }
    public OffsetDateTime getDataHora(){
        return this.dataHora;
    }

    // SETTERS
    public void setValor(double valor){
        this.valor = Double.valueOf(valor);
    }
    public void setDataHora(OffsetDateTime dataHora){
        this.dataHora = dataHora;
    }
}
