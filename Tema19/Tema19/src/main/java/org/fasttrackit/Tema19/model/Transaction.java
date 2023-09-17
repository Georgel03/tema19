package org.fasttrackit.Tema19.model;

public class Transaction {

    private static int idCount = 1;
    private int id;

    private String product;

    private Type type;

    private double amount;

    public Transaction(String product, Type type, double amount) {
        this.id = idCount++;
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
