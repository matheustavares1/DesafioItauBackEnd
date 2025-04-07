package com.DesafioItau.DesafioBackEndItau.controllers;

import com.DesafioItau.DesafioBackEndItau.dtos.StatisticsResponse;
import com.DesafioItau.DesafioBackEndItau.services.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/statistics")
public class StatisticsController {


    private final StatisticsService statisticsService;

     public StatisticsController(StatisticsService statisticsService) {
         this.statisticsService = statisticsService;
     }


     @Operation(summary = "Gerar Estatísticas",
                description = "Realiza os cálculos das estatísticas das transações nos últimos 60 segundos. Caso não forem encontradas transações no último minuto todos os valores retornarão 0",
                tags = "Estatísticas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estatísticas calculadas com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!")
    })
    @GetMapping("/estatistica")
    public ResponseEntity<StatisticsResponse> generatedStatistics() {
        return ResponseEntity.ok().body(statisticsService.statistics());
    }
}
