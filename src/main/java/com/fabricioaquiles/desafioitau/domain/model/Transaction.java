package com.fabricioaquiles.desafioitau.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class Transaction {

    private double valor;
    private OffsetDateTime dataHora;
}
