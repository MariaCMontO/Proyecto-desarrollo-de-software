/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.controllers;

import com.foodlab.foodlab.models.MetodoPago;
import com.foodlab.foodlab.services.MetodoPagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BryanVanegas
 */
@RestController
@RequestMapping("foodlab/metodopago")
@Tag(name = "Métodos de Pago", description = "API para la gestión de los métodos de pago")
public class MetodoPagoController {

    private final MetodoPagoService metodoService;

    @Autowired
    public MetodoPagoController(MetodoPagoService metodoService) {
        this.metodoService = metodoService;
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtener Método pago con usuarioId", description = "Devuelve método de pago del usuario usando el Id del usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Método de pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    public ResponseEntity<MetodoPago> getMetodoPagoUsuario(@PathVariable @Parameter(description = "Id del usuario") String userId) {
        MetodoPago existingMetodo = metodoService.searchMetodoPagoUsuario(userId);
        if (existingMetodo != null) {
            return new ResponseEntity<>(existingMetodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(existingMetodo, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{numero}")
    @Operation(summary = "Obtener Método pago con número de métodoPago", description = "Busca un método de pago con su número y lo devuelve.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Método de pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    public ResponseEntity<MetodoPago> getMetodoPagoUsuario(@PathVariable @Parameter(description = "Número del método de pago a buscar") Long numero) {
        MetodoPago existingMetodo = metodoService.searchMetodoById(numero);
        if (existingMetodo != null) {
            return new ResponseEntity<>(existingMetodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(existingMetodo, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Guarda método de pago", description = "Guarda un nuevo método de pago ingresado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Método guardado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),})
    public ResponseEntity<MetodoPago> saveMetodoPago(
            @Parameter(description = "Datos del método de pago") @RequestBody MetodoPago metodo) {
        MetodoPago nuevoMetodo = metodoService.saveMetodo(metodo);
        return new ResponseEntity<>(nuevoMetodo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{numero}")
    @Operation(summary = "Eliminar método de pago", description = "Se busca el método de pago con su número y se elimina")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se eliminó el método de pago"),
        @ApiResponse(responseCode = "404", description = "Método de pago no encotrado")
    })
    public ResponseEntity<Void> deleteMetodo(
            @Parameter(description = "Número del método de pago a eliminar") @PathVariable Long numero) {

        MetodoPago existingMetodo = metodoService.searchMetodoById(numero);
        if (existingMetodo != null) {
            metodoService.deleteMetodo(numero);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
