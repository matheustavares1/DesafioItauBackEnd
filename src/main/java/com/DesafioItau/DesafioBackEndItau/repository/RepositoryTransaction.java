package com.DesafioItau.DesafioBackEndItau.repository;

import com.DesafioItau.DesafioBackEndItau.dtos.TransactionDTO;
import com.DesafioItau.DesafioBackEndItau.entities.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RepositoryTransaction {

    private static RepositoryTransaction instance;
    private final List<Transaction> transactions;
    private RepositoryTransaction() {
        this.transactions = new ArrayList<>();
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
        this.transactions.add(transaction);
    }
}
