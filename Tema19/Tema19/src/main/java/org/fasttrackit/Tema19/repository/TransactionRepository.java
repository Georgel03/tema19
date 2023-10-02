package org.fasttrackit.Tema19.repository;

import org.fasttrackit.Tema19.model.Transaction;
import org.fasttrackit.Tema19.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


    List<Transaction> findAll();

    @Query("SELECT t FROM Transaction t WHERE t.type = :typeName")
    List<Transaction> findByType(@Param("typeName") Type type);

    @Query("SELECT t FROM Transaction t WHERE t.amount > :min")
    List<Transaction> findByMinAmount(@Param("min") Double minAmount);

    @Query("SELECT t FROM Transaction t WHERE t.amount < :max")
    List<Transaction> findByMaxAmount(@Param("max") Double maxAmount);

    @Query("SELECT c FROM Transaction c WHERE c.type = :typeName AND c.amount > :min")
    List<Transaction> findByTypeAndMin(@Param("typeName") Type type, @Param("min") Double minAmount);

    @Query("SELECT c FROM Transaction c WHERE c.type = :typeName AND c.amount < :max")
    List<Transaction> findByTypeAndMax(@Param("typeName") Type type, @Param("max") Double maxAmount);

    @Query("SELECT c FROM Transaction c WHERE c.amount > :min AND c.amount < :max")
    List<Transaction> findByMinAndMax(@Param("min") Double minAmount, @Param("max") Double maxAmount);

    @Query("SELECT c FROM Transaction c WHERE c.type = :typeName AND c.amount > :min AND c.amount < :max")
    List<Transaction> findByTypeAndMinAndMax(@Param("typeName") Type type, @Param("min") Double minAmount, @Param("max") Double maxAmount);


}
