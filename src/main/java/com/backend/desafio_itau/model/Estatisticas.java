package com.backend.desafio_itau.model;

import java.util.DoubleSummaryStatistics;
import java.util.List;

public class Estatisticas {
    Double count;
    Double sum;
    Double avg;
    Double max;
    Double min;

    public Estatisticas(List<Double> valoresTransicionados){
        if(valoresTransicionados.size() == 0){
            this.setCount(0.0);
            this.setSum(0.0);
            this.setAvg(0.0);
            this.setMax(0.0);
            this.setMin(0.0);
        } else {
            // cria um obj de estatísticas e insere os valores (já separados pelo tempo)
            DoubleSummaryStatistics estatisticasValores = new DoubleSummaryStatistics();
            for(Double valor : valoresTransicionados) estatisticasValores.accept(valor);

            // salva todas as estatísticas
            this.setCount(Double.valueOf(estatisticasValores.getCount()));
            this.setSum(Double.valueOf(estatisticasValores.getSum()));
            this.setAvg(Double.valueOf(estatisticasValores.getAverage()));
            this.setMax(Double.valueOf(estatisticasValores.getMax()));
            this.setMin(Double.valueOf(estatisticasValores.getMin()));
        }
    }

    // GETTERS
    public Double getCount(){
        return this.count;
    }
    public Double getSum(){
        return this.sum;
    }
    public Double getAvg(){
        return this.avg;
    }
    public Double getMax(){
        return this.max;
    }
    public Double getMin(){
        return this.min;
    }

    // SETTERS
    public void setCount(Double count) {
        this.count = count;
    }
    public void setSum(Double sum) {
        this.sum = sum;
    }
    public void setAvg(Double avg) {
        this.avg = avg;
    }
    public void setMax(Double max) {
        this.max = max;
    }
    public void setMin(Double min) {
        this.min = min;
    }
}
