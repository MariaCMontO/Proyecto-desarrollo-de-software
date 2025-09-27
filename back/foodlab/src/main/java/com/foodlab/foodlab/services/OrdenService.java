/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.services;

import com.foodlab.foodlab.models.OrdenProducto;
import com.foodlab.foodlab.models.Order;
import com.foodlab.foodlab.repositories.OrdenRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author camim
 */
@Service
public class OrdenService {
    
    @Autowired
    private OrdenRepository repository;
    private ProductoService productoService;
    
    public OrdenService() {
    }
    
    public void save(Order orden) {
        repository.save(orden);
    }
    
    public List<Order> getAllOrder() {
        return repository.getAllOrder();
    }
    
    public Order getOrderById(String id) {
        return repository.getOrderById(id);
    }
    
    public List<Order> getOrdenByUser(String idUser) {
        return repository.getOrdenByUser(idUser);
    }
    
    public void removeOrder(String id) {
        repository.removeOrder(id);
    }
    
    public Order updateOrderState(String idOrden, String estado) {
        return repository.updateOrderState(idOrden, estado);
    }
}
