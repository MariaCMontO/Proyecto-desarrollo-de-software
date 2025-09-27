/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.dtos;

import com.foodlab.foodlab.models.OrdenProducto;
import java.util.List;

/**
 *
 * @author camim
 */
public class OrdenRequestDTO {
    private String userId;
    private List<OrdenProductoDTO> productos;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrdenProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<OrdenProductoDTO> productos) {
        this.productos = productos;
    }
    
    
}
