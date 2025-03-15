package com.fabricioaquiles.desafioitau.web.controller;

import com.fabricioaquiles.desafioitau.domain.service.TransactionService;
import com.fabricioaquiles.desafioitau.web.response.StatisticsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/estatistica")
public class StatisticsController {

    private final TransactionService transactionService;

    public StatisticsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar as estastiscas das transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<StatisticsResponse> findStatistics(@RequestParam(value = "searchInterval", required = false, defaultValue = "60") Integer interval) {
        StatisticsResponse summaryStatistics = transactionService.findStatistics(interval);
        return ResponseEntity.ok(summaryStatistics);
    }
}
