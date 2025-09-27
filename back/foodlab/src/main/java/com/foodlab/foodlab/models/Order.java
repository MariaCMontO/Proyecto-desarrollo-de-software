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
    private ArrayList<OrdenProducto> products;
    private Usuario user;
    private String state;

    public Order(String fecha, Usuario user) {
        this.idOrder = UUID.randomUUID().toString();
        this.date = fecha;
        this.products = new ArrayList<>();
        this.user = user;
        this.state="confirmada";
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

    public ArrayList<OrdenProducto> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<OrdenProducto> products) {
        this.products = products;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
    public void calcularTotal() {
        double total=products.stream()
                .mapToDouble(pro -> pro.getProductos().getPrecio() * pro.getCantidad())
                .reduce(0, (a, b) -> a + b);
        this.total=total;
        
    }

}
