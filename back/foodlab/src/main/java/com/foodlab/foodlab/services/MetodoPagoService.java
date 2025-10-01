/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.services;

import com.foodlab.foodlab.models.MetodoPago;
import com.foodlab.foodlab.models.Usuario;
import com.foodlab.foodlab.repositories.MetodoPagoRepository;
import com.foodlab.foodlab.repositories.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author BryanVanegas
 */
@Service
public class MetodoPagoService {

    private final MetodoPagoRepository metodoRepo;
    private final UsuarioService usuarioService;

    @Autowired

    public MetodoPagoService(MetodoPagoRepository metodorespo, UsuarioService usuarioService) {
        this.metodoRepo = metodorespo;
        this.usuarioService = usuarioService;
        initSampleData();
    }

    private void initSampleData() {
        MetodoPago metP = new MetodoPago("Tarjeta de Credito", 1234456654321L, "Visa", 1234L);
        metodoRepo.saveMetodo(metP);
    }

    public MetodoPago searchMetodoPagoUsuario(String id) {
        Usuario existingUser = usuarioService.findById(id);

        if (existingUser != null) {
            Long numeroMetodo = existingUser.getMetodoPago().getNumero();
            return metodoRepo.searchMetodoById(numeroMetodo);
        } else {
            return null;
        }
    }

    public MetodoPago saveMetodo(MetodoPago metodo) {
        return metodoRepo.saveMetodo(metodo);
    }

    public MetodoPago searchMetodoById(Long numero) {
        return metodoRepo.searchMetodoById(numero);
    }

    public void deleteMetodo(Long numero) {
        metodoRepo.deleteMetodo(numero);
    }
}
