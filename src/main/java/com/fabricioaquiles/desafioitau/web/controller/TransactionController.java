package com.fabricioaquiles.desafioitau.web.controller;

import com.fabricioaquiles.desafioitau.domain.model.Transaction;
import com.fabricioaquiles.desafioitau.web.request.TransactionRequest;
import com.fabricioaquiles.desafioitau.domain.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @Operation(description = "Endpoint responsavel por adicionar novas transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação adicionada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Transação inválida (futuro, valor negativo, etc.)"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionRequest transactionRequest) {
        transactionService.addTransaction(Transaction.builder()
                    .valor(transactionRequest.valor())
                    .dataHora(transactionRequest.dataHora())
                .build()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsavel por limpar as transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista de transações limpas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteAllTransactions() {
        transactionService.clearTransactions();
        return ResponseEntity.noContent().build();
    }
}
