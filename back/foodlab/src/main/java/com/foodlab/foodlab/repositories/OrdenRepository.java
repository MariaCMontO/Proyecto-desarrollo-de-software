/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.repositories;

import com.foodlab.foodlab.models.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author camim
 */
@Repository
public class OrdenRepository {
    
    private Map<String, Order> ordenes= new HashMap<>();
    
    //Guardar orden
    public void save(Order orden){
        this.ordenes.put(orden.getIdOrder(), orden);
    }
    
    //Listar todas las ordenes
    public List<Order> getAllOrder(){
        return new ArrayList<>(ordenes.values());
    }
    
    //Buscar orden por ID
    public Order getOrderById(String id){
        return ordenes.get(id);
    }
    
    //Filtrar ordenes por cliente
    public List<Order> getOrdenByUser(String idUser){
        return ordenes.values().stream()
                .filter(or -> or.getUser().getId().equals(idUser))
                .collect(Collectors.toList());
    }
    
    //Eliminar orden 
    public void removeOrder(String id){
        ordenes.remove(id);
    }
    
    //Actualizar estado de la orden
    public Order updateOrderState(String idOrden, String estado){
        Order orden=getOrderById(idOrden);
        orden.setState(estado);
        return orden;
    }
}
