package com.DesafioItau.DesafioBackEndItau.services;

import com.DesafioItau.DesafioBackEndItau.dtos.TransactionDTO;
import com.DesafioItau.DesafioBackEndItau.dtos.TransactionValueDTO;
import com.DesafioItau.DesafioBackEndItau.entities.Transaction;
import com.DesafioItau.DesafioBackEndItau.exceptions.excep.TransactionError;
import com.DesafioItau.DesafioBackEndItau.repository.RepositoryTransaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {


    private final RepositoryTransaction repository = RepositoryTransaction.getInstance();

    //Metodo para receber tranascoes
    public TransactionDTO postTransaction(TransactionValueDTO transactionValueDTO) {
        //Momento da transacao
        OffsetDateTime now = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);

        //Criando transacao
        Transaction dto = new Transaction(
          transactionValueDTO.valor(), now, UUID.randomUUID().toString()
        );

        //Verificando valor da transacao
        if((transactionValueDTO.valor() == 0) || (transactionValueDTO.valor() < 0)) {
            throw new TransactionError("Valor invalido!");
        }

        //Adicionando na lista
        repository.addTransaction(dto);

        return new TransactionDTO(
                transactionValueDTO.valor(), now
        );

    }

    //Retorna uma lista com as transacoes nos ultimos 60s
    public List<Transaction> getTransactionDTO() {
        return repository.getTransactions();
    }

    //Gera estatisticas
    public DoubleSummaryStatistics statistics(){
        List<Transaction> transactions = RepositoryTransaction.findByLast60s();
        for(Transaction transaction : transactions){
            if(transaction.getValor() == 0){
                return new DoubleSummaryStatistics();
            }
        }
        return transactions.stream()
                .collect(Collectors.summarizingDouble(Transaction::getValor));
    }
}
