package com.foodlab.foodlab.controllers;

import com.foodlab.foodlab.models.Preferencia;
import com.foodlab.foodlab.services.PreferenciaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/foodlab/preferencias")
public class PreferenciaController {

    private final PreferenciaService preferenciaService;

    public PreferenciaController(PreferenciaService preferenciaService) {
        this.preferenciaService = preferenciaService;
    }

    @GetMapping
    public List<Preferencia> findAll() {
        return preferenciaService.findAll();
    }

    @GetMapping("/{id}")
    public Preferencia findById(@PathVariable Integer id) {
        return preferenciaService.findById(id);
    }
}
