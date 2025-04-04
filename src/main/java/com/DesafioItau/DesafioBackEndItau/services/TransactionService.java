package com.DesafioItau.DesafioBackEndItau.services;

import com.DesafioItau.DesafioBackEndItau.dtos.StatisticsResponse;
import com.DesafioItau.DesafioBackEndItau.dtos.TransactionDTO;
import com.DesafioItau.DesafioBackEndItau.dtos.TransactionValueDTO;
import com.DesafioItau.DesafioBackEndItau.entities.Transaction;
import com.DesafioItau.DesafioBackEndItau.exceptions.excep.TransactionError;
import com.DesafioItau.DesafioBackEndItau.repository.RepositoryTransaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {



    private final RepositoryTransaction repositoryTransaction;

    public TransactionService(RepositoryTransaction repositoryTransaction) {
        this.repositoryTransaction = repositoryTransaction;
    }

    //Metodo para receber tranascoes
    public TransactionDTO postTransaction(TransactionValueDTO transactionValueDTO) {
        //Momento da transacao
        OffsetDateTime now = OffsetDateTime.now( ZoneOffset.UTC);

        //Criando transacao
        Transaction dto = new Transaction(
          transactionValueDTO.valor(), now, UUID.randomUUID().toString()
        );

        //Verificando valor da transacao
        if((transactionValueDTO.valor() <= 0)) {
            throw new TransactionError("Valor invalido!");
        }

        //Adicionando na lista
        repositoryTransaction.addTransaction(dto);

        return new TransactionDTO(
                transactionValueDTO.valor(), now
        );

    }

    //Retorna uma lista com as transacoes
    public List<Transaction> getTransaction() {
        return repositoryTransaction.getTransactions();
    }

    //Remove todas as transacoes da lista
    public void removeTransactions() {
        repositoryTransaction.deleteTransaction();
    }
}
