package org.fasttrackit.Tema19.repository;

import org.fasttrackit.Tema19.model.Transaction;
import org.fasttrackit.Tema19.model.Type;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


public class TransactionsReader {

    public static List<Transaction> generateTransactions() {

        List<Transaction> transactionList = new ArrayList<>();

        Transaction transactionOne = new Transaction(1,"Boxa", Type.BUY, 384.90D);
        Transaction transactionTwo = new Transaction(2,"Camasa", Type.BUY, 579.99D);
        Transaction transactionThree = new Transaction(3,"Cercei", Type.SELL, 1195.99D);
        Transaction transactionFour = new Transaction(4,"Laptop", Type.SELL, 3950.99D);
        Transaction transactionFive = new Transaction(5, "Carte", Type.BUY, 129.99D);

        transactionList.add(transactionOne);
        transactionList.add(transactionTwo);
        transactionList.add(transactionThree);
        transactionList.add(transactionFour);
        transactionList.add(transactionFive);

        return transactionList;
    }
}
