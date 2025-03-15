package com.fabricioaquiles.desafioitau.web.request;

import java.time.OffsetDateTime;

public record TransactionRequest(Double valor, OffsetDateTime dataHora) {
}
