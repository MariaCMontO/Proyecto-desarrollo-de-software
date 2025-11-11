package com.foodlab.foodlab.repositories;

import com.foodlab.foodlab.models.Order;
import com.foodlab.foodlab.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenRepository extends JpaRepository<Order, Integer> {

    public List<Order> findByUserId(Integer userId);

    //Actualizar estado de la orden
    //public Order updateOrderState(Integer idOrden, String estado);
}
