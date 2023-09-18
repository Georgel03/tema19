package org.fasttrackit.Tema19.service;

import org.fasttrackit.Tema19.exceptions.EntityNotFoundException;
import org.fasttrackit.Tema19.model.Transaction;
import org.fasttrackit.Tema19.model.Type;
import org.fasttrackit.Tema19.repository.TransactionsReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private List<Transaction> transactions;

    @Autowired
    public TransactionService() {

        this.transactions = TransactionsReader.generateTransactions();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }


    public Transaction getTransactionById(int id) {

        return transactions.stream()
                .filter(transaction -> transaction.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Nu a fost gasita tranzactia cu id " + id, id));
    }

    public List<Transaction> getTransactionByProduct(String product) {

        return transactions.stream()
                .filter(transaction -> transaction.getProduct().equals(product))
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionByType(Type type) {

        return transactions.stream()
                .filter(transaction -> transaction.getType().equals(type))
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionByMinAmount(Integer minAmount) {
        int finalMinAmount = minAmount == null ? 0 : minAmount;
        return transactions.stream()
                .filter(transaction -> transaction.getAmount() >= finalMinAmount)
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionByMaxAmount(Integer maxAmount) {
        int finalMaxAmount = maxAmount == null ? 0 : maxAmount;
        return transactions.stream()
                .filter(transaction -> transaction.getAmount() <= finalMaxAmount)
                .collect(Collectors.toList());
    }

    public Transaction addTransaction(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }

    public Transaction replaceTransaction(int id, Transaction newTransaction) {

        transactions = transactions.stream()
                .map(transaction -> {
                    if (transaction.getId() == id) {
                        return newTransaction;
                    } else {
                        return transaction;
                    }
                })
                .collect(Collectors.toList());

        return newTransaction;

    }

    public Transaction deleteTransaction(int id) {

        transactions.stream()
                .filter(transaction -> transaction.getId() == id)
                .findFirst()
                .map(transaction -> transactions.remove(transaction))
                .orElseThrow(() -> new EntityNotFoundException("Nu a fost gasita tranzactia cu id " + id, id));

        return null;
    }

    public Map<Type, List<Transaction>> getMapFromTypeToListOfTransactionsOfThatType(Type searchedType) {

        Map<Type, List<Transaction>> myMap = new HashMap<>();
        List<Transaction> transactionList = transactions.stream()
                .filter(transaction -> transaction.getType().equals(searchedType))
                .collect(Collectors.toList());

        myMap.put(searchedType, transactionList);

        return myMap;
    }

    public Map<String, List<Transaction>> getMapFromProductToListOfTransactionsOfThatProduct(String product) {

        Map<String, List<Transaction>> myMap = new HashMap<>();
        List<Transaction> transactionList = transactions.stream()
                .filter(transaction -> transaction.getProduct().equals(product))
                .collect(Collectors.toList());

        myMap.put(product, transactionList);

        return myMap;
    }


}
