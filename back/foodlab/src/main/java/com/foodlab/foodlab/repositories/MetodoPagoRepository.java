package com.foodlab.foodlab.repositories;

import com.foodlab.foodlab.models.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {

    public MetodoPago findByUsuarioId(Integer usuarioId);
}
