/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.services;

import com.foodlab.foodlab.models.MetodoPago;

import com.foodlab.foodlab.models.Usuario;
import com.foodlab.foodlab.repositories.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * @author BryanVanegas
 */
@Service
public class MetodoPagoService {

    private final MetodoPagoRepository metodoRepo;
    private final UsuarioService usuarioService;

    public MetodoPagoService(MetodoPagoRepository metodorespo, UsuarioService usuarioService) {
        this.metodoRepo = metodorespo;
        this.usuarioService = usuarioService;
        //initSampleData();
    }

    private void initSampleData() {
        MetodoPago metP = new MetodoPago(1234123412341234L, "Debito", "Visa", 1234L);
        metodoRepo.save(metP);
    }

    public MetodoPago findByUsuarioId(Integer id) {
        Optional<Usuario> existingUser = usuarioService.findById(id);

        if (existingUser.isPresent()) {
            Usuario existing = existingUser.get();

            if (existing.getMetodoPago() != null) {
                Integer idMetodo = existing.getMetodoPago().getId();
                return metodoRepo.findById(idMetodo).get();
            }
        }
        return null;
    }

    public MetodoPago saveMetodo(MetodoPago metodo) {
        return metodoRepo.save(metodo);
    }

    public MetodoPago findById(Integer id) {
        return metodoRepo.findById(id).get();
    }

    public void deleteMetodo(Integer id) {
        metodoRepo.deleteById(id);
    }
}
