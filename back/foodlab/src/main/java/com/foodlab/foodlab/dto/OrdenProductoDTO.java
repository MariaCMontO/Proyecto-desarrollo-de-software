/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.dto;

/**
 *
 * @author camim
 */
public class OrdenProductoDTO {
    private Integer productoId;
    private int cantidad;

    public OrdenProductoDTO() {
    }

    public OrdenProductoDTO(Integer productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
