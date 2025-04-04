package com.DesafioItau.DesafioBackEndItau.repository;

import com.DesafioItau.DesafioBackEndItau.entities.Transaction;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositoryTransaction {


    private static  List<Transaction> transactions;

    private RepositoryTransaction() {
        transactions = new ArrayList<>();
    }


    //buscar todas as transacoes
    public List<Transaction> getTransactions() {
        return transactions;
    }

    //adicionar transacao
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    //Buscas transacoes do ultimo minuto
    public List<Transaction> findByLast60s() {
        OffsetDateTime agora = OffsetDateTime.now().minusSeconds(60);
        return transactions.stream()
                .filter(transaction -> transaction.getDataHora().isAfter(agora))
                .collect(Collectors.toList());
    }

    //Remove Transacoes
    public void deleteTransaction() {
        transactions.clear();
    }
}
