package com.DesafioItau.DesafioBackEndItau.controllers;

import com.DesafioItau.DesafioBackEndItau.dtos.TransactionDTO;
import com.DesafioItau.DesafioBackEndItau.dtos.TransactionValueDTO;
import com.DesafioItau.DesafioBackEndItau.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TransactionController {

    @Autowired
    private  TransactionService transactionService;


    @PostMapping("/transacao")
    public ResponseEntity<TransactionDTO> postTransaction(@RequestBody TransactionValueDTO transactionDTO) {
        return ResponseEntity.ok().body(transactionService.postTransaction(transactionDTO));

    }
}
