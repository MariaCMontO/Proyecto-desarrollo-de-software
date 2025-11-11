package com.foodlab.foodlab.services;

import com.foodlab.foodlab.models.Preferencia;
import com.foodlab.foodlab.repositories.PreferenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenciaService {

    private final PreferenciaRepository preferenciaRepository;

    public PreferenciaService(PreferenciaRepository preferenciaRepository) {
        this.preferenciaRepository = preferenciaRepository;
    }

    public List<Preferencia> findAll() {
        return preferenciaRepository.findAll();
    }

    public Preferencia findById(Integer id) {
        return preferenciaRepository.findById(id).get();
    }

    public Preferencia findByUserId(Integer userId) {
        return findByUserId(userId);
    }

}
