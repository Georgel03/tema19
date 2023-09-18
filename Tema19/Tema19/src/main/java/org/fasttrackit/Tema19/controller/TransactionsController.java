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


    @GetMapping
    public List<Transaction> getAllTransactions() {
        return service.getTransactions();
    }

    @GetMapping("{productName}")
    public List<Transaction> getAllTransactionsByProduct(@PathVariable String productName) {
        return service.getTransactionByProduct(productName);
    }

    @GetMapping("{typeTransaction}")
    public List<Transaction> getAllTransactionsByProduct(@PathVariable Type typeTransaction) {
        return service.getTransactionByType(typeTransaction);
    }

    @GetMapping("{minAmount}")
    public List<Transaction> getTransactionByMinAmount(@PathVariable Integer minAmount) {
        return service.getTransactionByMinAmount(minAmount);
    }

    @GetMapping("{maxAmount}")
    public List<Transaction> getTransactionByMaxAmount(@PathVariable Integer maxAmount) {
        return service.getTransactionByMaxAmount(maxAmount);
    }

    @GetMapping("transactions/{id}")
    public Transaction getTransactionById(@PathVariable Integer id) {
        return service.getTransactionById(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return service.addTransaction(transaction);
    }

    @PutMapping("{id}")
    public Transaction replaceTransaction(@PathVariable int id, @RequestBody Transaction newTransaction) {
        return service.replaceTransaction(id, newTransaction);
    }

    @DeleteMapping("{id}")
    public Transaction deleteTransaction(@PathVariable int id) {
        return service.deleteTransaction(id);
    }

    @GetMapping("/reports/type/{searchedType}")
    public Map<Type, List<Transaction>> getMapFromTypeToListOfTransactionsOfThatType(@PathVariable Type searchedType) {
        return service.getMapFromTypeToListOfTransactionsOfThatType(searchedType);
    }

    @GetMapping("/reports/product/{product}")
    public Map<String, List<Transaction>> getMapFromProductToListOfTransactionsOfThatProduct(@PathVariable String product) {
        return service.getMapFromProductToListOfTransactionsOfThatProduct(product);
    }


    }
