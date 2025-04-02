package com.DesafioItau.DesafioBackEndItau.controllers;

import com.DesafioItau.DesafioBackEndItau.dtos.TransactionDTO;
import com.DesafioItau.DesafioBackEndItau.dtos.TransactionValueDTO;
import com.DesafioItau.DesafioBackEndItau.entities.Transaction;
import com.DesafioItau.DesafioBackEndItau.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@RestController
@RequestMapping("/")
public class TransactionController {

    @Autowired
    private  TransactionService transactionService;

    @RequestMapping(value = "/transacao", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<TransactionDTO> postTransaction(@RequestBody TransactionValueDTO transactionDTO) {
        return ResponseEntity.ok().body(transactionService.postTransaction(transactionDTO));

    }
    @GetMapping("/Get")
    public ResponseEntity<DoubleSummaryStatistics> generatedStatistics() {
        return ResponseEntity.ok().body(transactionService.statistics());
    }

    @GetMapping("/testGet")
    public ResponseEntity<List<Transaction>>getTransactions() {
        return ResponseEntity.ok().body(transactionService.getTransactionDTO());
    }
}
