/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.foodlab.foodlab.models;


/**
 *
 * @author camim
 */
public class OrdenProducto {

    private Producto producto;
    private int cantidad;
    private String nota;

    public OrdenProducto(Producto productos, int cantidad, String nota) {
        this.producto = productos;
        this.cantidad = cantidad;
        this.nota = nota;
    }

    public Producto getProductos() {
        return producto;
    }

    public void setProductos(Producto productos) {
        this.producto = productos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
    
    
}
