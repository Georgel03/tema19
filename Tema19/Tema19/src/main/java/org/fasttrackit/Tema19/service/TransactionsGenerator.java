package org.fasttrackit.Tema19.service;

import org.fasttrackit.Tema19.model.Transaction;
import org.fasttrackit.Tema19.model.Type;

import java.util.List;

public class TransactionsGenerator {

    public static List<Transaction> generateTransactions() {

        Transaction transactionOne = new Transaction("Boxa", Type.BUY, 384.90D);
        Transaction transactionTwo = new Transaction("Camasa", Type.BUY, 579.99D);
        Transaction transactionThree = new Transaction("Cercei", Type.SELL, 1195.99D);
        Transaction transactionFour = new Transaction("Laptop", Type.SELL, 3950.99D);
        Transaction transactionFive = new Transaction("Carte", Type.BUY, 129.99D);
        return List.of(transactionOne, transactionTwo, transactionThree, transactionFour, transactionFive);
    }
}
