package com.fabricioaquiles.desafioitau.domain.service;

import com.fabricioaquiles.desafioitau.domain.model.Transaction;
import com.fabricioaquiles.desafioitau.infrastructure.exceptions.UnprocessableEntity;
import com.fabricioaquiles.desafioitau.web.response.StatisticsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@Slf4j
public class TransactionService {

    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(Transaction transaction) {

        long startRequest = System.currentTimeMillis();

        log.info("Recebida requisição para adicionar transação: {}", transaction);
        if(transaction.getDataHora().isAfter(OffsetDateTime.now()) || transaction.getValor() <= 0) {
            log.error("Requisição inválida: data/hora inválida ou valor inválido");
            throw new UnprocessableEntity();
        }
        log.info("Adicionando transação: {}", transaction);
        transactions.add(transaction);
        long finishRequest = System.currentTimeMillis();

        System.out.println("POST /transaction request time: " + (finishRequest - startRequest) + " ms");

        log.info("Transação adicionada com sucesso");
    }

    public void clearTransactions() {
        log.info("Recebida requisição para limpar todas as transações");
        transactions.clear();
        log.info("Transações limpas com sucesso");
    }

    public StatisticsResponse findStatistics(long interval) {
        log.info("Recebida requisição para calcular estatísticas");

        long startRequest = System.currentTimeMillis();
        OffsetDateTime dateNow = OffsetDateTime.now();

        DoubleSummaryStatistics doubleSummaryStatistics = transactions.stream()
                .filter(t -> t.getDataHora().isAfter(dateNow.minusSeconds(interval)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();

        long finishRequest = System.currentTimeMillis();

        System.out.println("GET /estatistica request time: " + (finishRequest - startRequest) + " ms");

        log.info("Estatísticas calculadas com sucesso");
        return new StatisticsResponse(doubleSummaryStatistics);
    }
}
