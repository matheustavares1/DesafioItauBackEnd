package com.DesafioItau.DesafioBackEndItau.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

public record TransactionDTO(
        Double valor,
        OffsetDateTime dataHora

) {
}
