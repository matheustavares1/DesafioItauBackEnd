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
import java.util.UUID;

@Service
public class TransactionService {


    private final RepositoryTransaction repository = RepositoryTransaction.getInstance();

    //Metodo para receber tranascoes
    public TransactionDTO postTransaction(TransactionValueDTO transactionValueDTO) {
        //Momento da transacao
        OffsetDateTime now = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);

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
}
