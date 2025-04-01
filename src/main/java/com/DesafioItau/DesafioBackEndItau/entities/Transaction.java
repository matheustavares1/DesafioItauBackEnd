package com.DesafioItau.DesafioBackEndItau.entities;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

public class Transaction {

    private Double valor;
    private OffsetDateTime dataHora;
    private String id;

    public Transaction() {}

    public Transaction(Double valor, OffsetDateTime dataHora, String id) {
        this.valor = valor;
        this.dataHora = dataHora;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
