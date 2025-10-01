/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.repositories;

import com.foodlab.foodlab.models.MetodoPago;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BryanVanegas
 */

@Repository
public class MetodoPagoRepository {
    
    private Map<Long, MetodoPago> metodos = new HashMap<>();
    
    
    //Guardar MetodoPago
    public MetodoPago saveMetodo(MetodoPago metodo) {
        this.metodos.put(metodo.getNumero(), metodo);
        return metodo;
    }
    
    //Obtener todos los metodos de pago
    public List<MetodoPago> findMetodoPagoUsuario() {
        return new ArrayList<>(metodos.values());
    }
    
    //Buscar MetodoPago por número
    public MetodoPago searchMetodoById(Long numero) {
        return metodos.get(numero);
    }
    
    //Eliminar MetodoPago
    public void deleteMetodo(Long numero) {
        metodos.remove(numero);
    }

}
