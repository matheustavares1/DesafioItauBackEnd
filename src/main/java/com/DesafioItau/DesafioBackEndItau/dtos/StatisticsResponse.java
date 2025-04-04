package com.DesafioItau.DesafioBackEndItau.dtos;

public record StatisticsResponse(
        long count, double sum, double min, double max, double average
) {
}
