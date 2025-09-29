/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.models;

import java.util.UUID;

/**
 *
 * @author BryanVanegas
 */
public class Receipt {

    private String idReceipt;
    private String date;
    private final double delivery = 3.000;
    private double discount;
    private double total;
    private Order order;

    public Receipt(Order order) {
        this.idReceipt = UUID.randomUUID().toString();

        this.order = order;

        this.date = order.getDate();
        this.discount = calculateDiscount(order);
        this.total = calculateTotal(order);
    }

    public final double calculateDiscount(Order order) {
        if (order.getProducts().size() > 4) {
            return 0.05;
        }
        return 0;
    }

    public final double calculateTotal(Order order) {
        return this.discount == 0 ? (this.order.getTotal() + this.delivery)
                : ((this.order.getTotal() - (this.order.getTotal() * this.discount)) + this.delivery);
    }

    public String getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(String idReceipt) {
        this.idReceipt = idReceipt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDelivery() {
        return delivery;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
