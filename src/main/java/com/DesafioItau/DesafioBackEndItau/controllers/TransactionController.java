package com.DesafioItau.DesafioBackEndItau.controllers;

import com.DesafioItau.DesafioBackEndItau.dtos.TransactionDTO;
import com.DesafioItau.DesafioBackEndItau.dtos.TransactionValueDTO;
import com.DesafioItau.DesafioBackEndItau.entities.Transaction;
import com.DesafioItau.DesafioBackEndItau.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class TransactionController {


    private  final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @Operation(summary = "Fazer Transação.",
            description = "Realiza Transações com valores positivos. Transações com números negativos ou número igual a 0 não serão aceitas.",
            tags = "Transações"

    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação realizada com sucesso."),
            @ApiResponse(responseCode = "422", description = "Transação não aceita, valores inválidos!"),
            @ApiResponse(responseCode = "400", description = "Requisicão inválida.")
    })
    @RequestMapping(value = "/transacao", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<TransactionDTO> postTransaction(@RequestBody TransactionValueDTO transactionDTO) {
        TransactionDTO transaction = transactionService.postTransaction(transactionDTO);
        return ResponseEntity.status(201).body(transaction);

    }

    @Operation(summary = "Listar",description = "Busca todas as transações realizadas.",  tags = "Transações")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Transaction>>getTransactions() {
        return ResponseEntity.ok().body(transactionService.getTransaction());
    }

    @Operation(summary = "Deletar",description = "Apaga todas as transações",  tags = "Transações")
    @ApiResponse(responseCode = "200", description = "Todas as informações foram apagadas com sucesso.")
    @DeleteMapping("/transacao")
    public void removeTransactions(){
        transactionService.removeTransactions();
    }
}
