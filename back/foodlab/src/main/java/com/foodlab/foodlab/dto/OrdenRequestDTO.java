/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.dto;

import java.util.List;

/**
 *
 * @author camim
 */
public class OrdenRequestDTO {
    private Integer userId;
    private List<OrdenProductoDTO> productos;

    public OrdenRequestDTO() {
    }

    public OrdenRequestDTO(Integer userId, List<OrdenProductoDTO> productos) {
        this.userId = userId;
        this.productos = productos;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<OrdenProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<OrdenProductoDTO> productos) {
        this.productos = productos;
    }

}
