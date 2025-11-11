package com.foodlab.foodlab.repositories;

import com.foodlab.foodlab.models.Preferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenciaRepository extends JpaRepository<Preferencia, Integer> {

    public Preferencia findByUsuarioId(Integer userId);
}
