package com.DesafioItau.DesafioBackEndItau.repository;

import com.DesafioItau.DesafioBackEndItau.entities.Transaction;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryTransaction {

    private static RepositoryTransaction instance;
    private static  List<Transaction> transactions;

    private RepositoryTransaction() {
        transactions = new ArrayList<>();
    }

    public static RepositoryTransaction getInstance() {
        if (instance == null) {
            instance = new RepositoryTransaction();
        }
        return instance;
    }

    //buscar todas as transacoes
    public List<Transaction> getTransactions() {
        return transactions;
    }

    //adicionar transacao
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    //Buscas transacoes do ultimo minutos
    public static List<Transaction> findByLast60s() {
        OffsetDateTime agora = OffsetDateTime.now(ZoneOffset.UTC);
        return transactions.stream()
                .filter(transaction -> transaction.getDataHora().isBefore(agora.minusSeconds(60)))
                .collect(Collectors.toList());
    }
}
