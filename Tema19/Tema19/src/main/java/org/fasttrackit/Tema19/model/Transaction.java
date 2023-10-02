package org.fasttrackit.Tema19.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String product;

    private Type type;

    private double amount;

    public Transaction(int id, String product, Type type, double amount) {
        this.id = id;
        this.product = product;
        this.type = type;
        this.amount = amount;
    }

    public Transaction(String product, Type type, double amount) {
        this.product = product;
        this.type = type;
        this.amount = amount;
    }

    public Transaction() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
