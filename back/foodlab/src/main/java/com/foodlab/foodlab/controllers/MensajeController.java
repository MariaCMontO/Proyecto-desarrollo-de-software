package com.foodlab.foodlab.controllers;

import com.foodlab.foodlab.models.Mensaje;
import com.foodlab.foodlab.services.MensajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mensajes")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
@Tag(name = "Mensajes", description = "API para la gestión de mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    @Autowired
    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }
    
    @GetMapping
    @Operation(summary = "Obtener todos los mensajes", description = "Devuelve una lista de todos los mensajes registrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de mensajes obtenida con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Mensaje>> getAllMensajes() {
        List<Mensaje> mensajes = mensajeService.findAll();
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    @Operation(summary = "Obtener mensaje por Email", description = "Devuelve un mensaje específico basado en su Email.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mensaje encontrado"),
        @ApiResponse(responseCode = "404", description = "Mensaje no encontrado")
    })
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable @Parameter(description = "Email del mensaje") String email) {
        Mensaje mensaje = mensajeService.findByEmail(email);
        if (mensaje != null) {
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo mensaje", description = "Crea un nuevo mensaje con los datos proporcionados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Mensaje creado con éxito"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Mensaje> createMensaje(@RequestBody @Parameter(description = "Datos del mensaje a crear") Mensaje mensaje) {
        Mensaje newMensaje = mensajeService.save(mensaje);
        return new ResponseEntity<>(newMensaje, HttpStatus.CREATED);
    }


    @GetMapping("/buscar")
    @Operation(summary = "Buscar mensajes por filtros", description = "Busca mensajes por nombre, precio mínimo y máximo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mensajes encontrados"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválemailos")
    })
    public ResponseEntity<List<Mensaje>> buscarMensajes(
            @RequestParam(required = false) @Parameter(description = "Email del mensaje (parcial o completo)") String email,
            @RequestParam(required = false) @Parameter(description = "Mensaje (opcional)") String message,
            @RequestParam(required = false) @Parameter(description = "Respuesta (opcional)") String respuesta) {
        List<Mensaje> mensajes = mensajeService.buscarPorFiltros(email, message, respuesta);
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }
}
