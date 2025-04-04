package com.DesafioItau.DesafioBackEndItau.controllers;

import com.DesafioItau.DesafioBackEndItau.dtos.StatisticsResponse;
import com.DesafioItau.DesafioBackEndItau.dtos.TransactionDTO;
import com.DesafioItau.DesafioBackEndItau.dtos.TransactionValueDTO;
import com.DesafioItau.DesafioBackEndItau.entities.Transaction;
import com.DesafioItau.DesafioBackEndItau.services.TransactionService;
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

    @RequestMapping(value = "/transacao", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<TransactionDTO> postTransaction(@RequestBody TransactionValueDTO transactionDTO) {
        return ResponseEntity.ok().body(transactionService.postTransaction(transactionDTO));

    }

    @GetMapping("/getall")
    public ResponseEntity<List<Transaction>>getTransactions() {
        return ResponseEntity.ok().body(transactionService.getTransaction());
    }

    @DeleteMapping("/transacao")
    public void removeTransactions(){
        transactionService.removeTransactions();
    }
}
