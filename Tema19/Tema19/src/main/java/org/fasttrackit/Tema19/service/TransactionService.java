package org.fasttrackit.Tema19.service;

import org.fasttrackit.Tema19.exceptions.EntityNotFoundException;
import org.fasttrackit.Tema19.model.Transaction;
import org.fasttrackit.Tema19.model.Type;
import org.fasttrackit.Tema19.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private TransactionRepository repository;

    @Autowired
    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAllTransactions(Type type, Double minAmount, Double maxAmount) {

        if (type != null) {
            if (minAmount != null) {
                if (maxAmount != null) {
                    return repository.findByTypeAndMinAndMax(type, minAmount, maxAmount);
                } else {
                    return repository.findByTypeAndMin(type, minAmount);
                }
            } else {
                if (maxAmount != null) {
                    return repository.findByTypeAndMax(type, maxAmount);
                } else {
                    return repository.findByType(type);
                }
            }
        } else {
            if (minAmount != null) {
                if (maxAmount != null) {
                    return repository.findByMinAndMax(minAmount, maxAmount);
                } else {
                    return repository.findByMinAmount(minAmount);
                }
            } else {
                if (maxAmount != null) {
                    return repository.findByMaxAmount(maxAmount);
                } else {
                    return repository.findAll();
                }
            }
        }

    }

    public static List<Transaction> generateTransactions() {

        Transaction transactionOne = new Transaction(1,"Boxa", Type.BUY, 384.90D);
        Transaction transactionTwo = new Transaction(2,"Camasa", Type.BUY, 579.99D);
        Transaction transactionThree = new Transaction(3,"Cercei", Type.SELL, 1195.99D);
        Transaction transactionFour = new Transaction(4,"Laptop", Type.SELL, 3950.99D);
        Transaction transactionFive = new Transaction( 5,"Carte", Type.BUY, 129.99D);

        List<Transaction> transactionList = new ArrayList<>();

        transactionList.add(transactionOne);
        transactionList.add(transactionTwo);
        transactionList.add(transactionThree);
        transactionList.add(transactionFour);
        transactionList.add(transactionFive);

        return transactionList;
    }

    public Transaction getTransactionById(int id) {

        Optional<Transaction> foundTransaction = repository.findById(id);
        if (foundTransaction.isPresent()) {
            return foundTransaction.get();
        } else {
            throw new EntityNotFoundException("Nu a fost gasita tranzactia cu id-ul " + id, id);
        }
    }

    public List<Transaction> getTransactionByType(Type type) {
        return repository.findByType(type);
    }

    public List<Transaction> getTransactionByMinAmount(Double minAmount) {
        minAmount = minAmount == null ? 0 : minAmount;
        return repository.findByMinAmount(minAmount);
    }

    public List<Transaction> getTransactionByMaxAmount(Double maxAmount) {
        maxAmount = maxAmount == null ? 0 : maxAmount;
        return repository.findByMaxAmount(maxAmount);
    }

    public List<Transaction> getTransactionsbyTypeAndMax(Type type, Double maxAmount) {
        type = type == null ? Type.SELL : type;
        maxAmount = maxAmount == null ? 0 : maxAmount;
        return repository.findByTypeAndMax(type, maxAmount);
    }

    public List<Transaction> getTransactionsbyTypeAndMin(Type type, Double minAmount) {
        type = type == null ? Type.SELL : type;
        minAmount = minAmount == null ? 0 : minAmount;
        return repository.findByTypeAndMin(type, minAmount);
    }

    public List<Transaction> getTransactionsbyMinAndMax(Double maxAmount, Double minAmount) {
        maxAmount = maxAmount == null ? 0 : maxAmount;
        minAmount = minAmount == null ? 0 : minAmount;
        return repository.findByMinAndMax(minAmount, maxAmount);
    }

    public List<Transaction> getTransactionsbyTypeAndMinAndMax(Type type, Double maxAmount, Double minAmount) {
        maxAmount = maxAmount == null ? 0 : maxAmount;
        minAmount = minAmount == null ? 0 : minAmount;
        type = type == null ? Type.SELL : type;
        return repository.findByTypeAndMinAndMax(type, minAmount, maxAmount);
    }

    public Transaction addTransaction(Transaction transaction) {
        Transaction savedTransaction = repository.save(transaction);
        return  savedTransaction;
    }

    public Transaction putTransaction(Transaction transaction) {

        Transaction finalTrans;
       Optional<Transaction> foundTransaction = repository.findById(transaction.getId());
       if (foundTransaction.isPresent()) {
           Transaction tranFromDB = foundTransaction.get();
           if (transaction.getProduct() != null) {
               tranFromDB.setProduct(transaction.getProduct());
           }
           if (transaction.getType() != null) {
               tranFromDB.setType(transaction.getType());
           }
           if (transaction.getAmount() != 0) {
               tranFromDB.setAmount(transaction.getAmount());
           }
           finalTrans = repository.save(tranFromDB);
       } else {
           finalTrans = repository.save(transaction);
       }
       return finalTrans;
    }

    public Transaction replaceTransaction(int id, String product, double newAmount) {

       Optional<Transaction> foundTransaction = repository.findById(id);

       if (foundTransaction.isPresent()) {
           Transaction newTransaction = foundTransaction.get();

           if (product != null) {
               newTransaction.setProduct(product);
           }

           if (newAmount > 0) {
               newTransaction.setAmount(newAmount);
           }

           return repository.save(newTransaction);
       }
       else {

           throw new EntityNotFoundException(String.format("Nu am gasit tranzactia cu %d id", id), id);
       }
    }

    public String deleteTransaction(int id) {

       repository.deleteById(id);
       return "Sters cu succes";

    }

    public Map<Type, List<Transaction>> getMapFromTypeToListOfTransactionsOfThatType(Type searchedType) {

        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getType));
    }

    public Map<String, List<Transaction>> getMapFromProductToListOfTransactionsOfThatProduct(String product) {

        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getProduct));
    }

    public Map<Type, Double> getMapFromTypeToSumOfAmount() {

        Map<Type, Double> typeDoubleMap = repository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getType, Collectors.summingDouble(Transaction::getAmount)));

        return typeDoubleMap;
    }

    public Map<String, Double> getMapFromProductToSumOfAmount() {

        Map<String, Double> stringDoubleMap = repository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getProduct, Collectors.summingDouble(Transaction::getAmount)));

        return stringDoubleMap;
    }


}
