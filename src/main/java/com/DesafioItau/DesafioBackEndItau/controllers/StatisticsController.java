package com.DesafioItau.DesafioBackEndItau.controllers;

import com.DesafioItau.DesafioBackEndItau.dtos.StatisticsResponse;
import com.DesafioItau.DesafioBackEndItau.services.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/statistics")
public class StatisticsController {


    private final StatisticsService statisticsService;

     public StatisticsController(StatisticsService statisticsService) {
         this.statisticsService = statisticsService;
     }

    @GetMapping("/estatistica")
    public ResponseEntity<StatisticsResponse> generatedStatistics() {
        return ResponseEntity.ok().body(statisticsService.statistics());
    }
}
