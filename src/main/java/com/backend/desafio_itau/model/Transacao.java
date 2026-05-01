package com.backend.desafio_itau.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transacao {
    private BigDecimal valor;
    private OffsetDateTime dataHora;
    
    // GETTERS
    public BigDecimal getValor(){
        return this.valor;
    }
    public OffsetDateTime getDataHora(){
        return this.dataHora;
    }

    // SETTERS
    public void setValor(BigDecimal valor){
        this.valor = valor;
    }
    public void setDataHora(OffsetDateTime dataHora){
        this.dataHora = dataHora;
    }
}
