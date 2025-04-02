package com.DesafioItau.DesafioBackEndItau.dtos;

import java.time.OffsetDateTime;

public record TransactionDTO(
        Double valor,
        OffsetDateTime dataHora

) {
}
