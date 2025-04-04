package com.DesafioItau.DesafioBackEndItau.services;

import com.DesafioItau.DesafioBackEndItau.dtos.StatisticsResponse;
import com.DesafioItau.DesafioBackEndItau.entities.Transaction;
import com.DesafioItau.DesafioBackEndItau.repository.RepositoryTransaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class StatisticsService {

    private final RepositoryTransaction repositoryTransaction;
    public StatisticsService(RepositoryTransaction repositoryTransaction) {
        this.repositoryTransaction = repositoryTransaction;
    }

    //Gera estatisticas
    public StatisticsResponse statistics(){

        OffsetDateTime now = OffsetDateTime.now().minusSeconds(60);
        //Gerando lista com as transacoes nos ultimos 60segundos
        List<Transaction> transactions = repositoryTransaction.findByLast60s().stream()
                .filter(transaction -> transaction.getDataHora().isAfter(now)).toList();
        //Pegando apenas os valores das transacos e gerando estatistivas
        DoubleSummaryStatistics statistics = transactions.stream()
                .mapToDouble(Transaction::getValor).summaryStatistics();

        double min = transactions.isEmpty() ? 0 : statistics.getMin();
        double max = transactions.isEmpty() ? 0 : statistics.getMax();

        return new StatisticsResponse(
                statistics.getCount(),
                statistics.getSum(),
                statistics.getAverage(),
                min,
                max
        );
    }
}
