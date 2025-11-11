/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 *
 * @author BryanVanegas
 */
@Entity
@Table(name = "facturas")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReceipt;
    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();
    @Column(nullable = false)
    private final double delivery = 5000;
    @Column(nullable = false)
    private double discount;
    @Column(nullable = false)
    private double total;

    @OneToOne(mappedBy = "factura")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "factura", "user", "products"})
    private Order order;

    public Receipt() {

    }

    public Integer getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(Integer idReceipt) {
        this.idReceipt = idReceipt;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
