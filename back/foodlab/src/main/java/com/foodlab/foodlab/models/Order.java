/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.models;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author BryanVanegas
 */
public class Order {

    private String idOrder;
    private String date;
    private double total;
    private ArrayList<Integer> products;

    public Order(String fecha, double total) {
        this.idOrder = UUID.randomUUID().toString();
        this.date = fecha;
        this.total = total;
        this.products = new ArrayList<>();
        fillProducts();
    }

    public final void fillProducts() {
        this.products.add(1);
        this.products.add(2);
        this.products.add(3);
        this.products.add(4);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<Integer> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Integer> products) {
        this.products = products;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

}

