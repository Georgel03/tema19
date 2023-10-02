package org.fasttrackit.Tema19.controller;

import org.fasttrackit.Tema19.model.Transaction;
import org.fasttrackit.Tema19.model.Type;
import org.fasttrackit.Tema19.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("transactions")
public class TransactionsController {

    private TransactionService service;

    @Autowired
    public TransactionsController(TransactionService service) {
        this.service = service;
    }


   @GetMapping("/filter")
    public List<Transaction> filterTransactions(
            @RequestParam(required = false) Type type,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount
    ) {
        return service.getAllTransactions(type,minAmount, maxAmount);
    }

    @GetMapping("{id}")
    public Transaction getTransactionById(@PathVariable Integer id) {
        return service.getTransactionById(id);
    }

    @PostMapping
    public Transaction postTransaction(@RequestBody Transaction transaction) {
        return service.addTransaction(transaction);
    }

    @PutMapping
    public Transaction putTransaction(@RequestBody Transaction newTransaction) {
        return service.putTransaction(newTransaction);
    }

    @PatchMapping("{id}")
    public Transaction patchTransaction(@PathVariable int id, @RequestParam(required = false) String product, @RequestParam(required = false) double newAmount) {
        return service.replaceTransaction(id, product, newAmount);
    }

    @DeleteMapping("{id}")
    public String deleteTransaction(@PathVariable int id) {
        return service.deleteTransaction(id);
    }

    @GetMapping("/reports/type")
    public Map<Type, Double> reportsByType() {
        return service.getMapFromTypeToSumOfAmount();
    }

    @GetMapping("/reports/product")
    public Map<String, Double> reportsByProduct() {
        return service.getMapFromProductToSumOfAmount();
    }


    }
