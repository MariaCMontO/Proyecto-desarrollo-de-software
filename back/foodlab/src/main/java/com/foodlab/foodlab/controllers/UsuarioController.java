/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.controllers;

import com.foodlab.foodlab.models.Usuario;
import com.foodlab.foodlab.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BryanVanegas
 */
@RestController
@RequestMapping("api/foodlab/usuarios")
@Tag(name = "Usuarios", description = "API para la gestion de usuarios")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
/**
 *
 * @author MATEO
 */
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @GetMapping
    @Operation(summary = "Obtiene todos los usuarios registrados", description = "Devuelve en una lista todos los usuarios registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida con exito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    @Operation(summary = "Buscar usuarios por nombre", description = "Buscar todos los usuarios por ese nombre completo o parcial")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuarios encontrados con exito"),
        @ApiResponse(responseCode = "404", description = "Usuario NO encontrado con ese nombre")
    })
    public ResponseEntity<List<Usuario>> getUsuariosByQuery(
            @Parameter(description = "Nombre del usuario a buscar") @RequestParam String nombre) {
        List<Usuario> usuarios = usuarioService.findByNombreContainingIgnoreCase(nombre);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    @GetMapping("/cabecera")
    @Operation(summary = "Obtiene informacion del cliente desde el header User-Agent", description = "Obtiene informacion del cliente que esta en la cabecera")
    @ApiResponse(responseCode = "200", description = "Informacion obtenida con exito")
    public ResponseEntity<String> getAgentInfo(
            @Parameter(description = "Header User-Agent del cliente") @RequestHeader("User-Agent") String userAgent) {
        String info = "Información del cliente (User-Agent): " + userAgent;
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
    
    //Filtrar por email y contrasenia
    @GetMapping("/login")
    @Operation(summary = "Buscar usuario por email y contraseña.Hacer el login", description = "Buscar un usuario en especifico por su email y contraseña")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado con exito"),
        @ApiResponse(responseCode = "404", description = "Usuario NO encontrado")
    })
    public ResponseEntity<Usuario> login (
            @Parameter(description = "Email del usuario a buscar") @RequestParam(required = true) String email, 
            @Parameter(description = "Contraseña del usuario a buscar") @RequestParam(required = true) String password) {

        Usuario usuario = usuarioService.login(email, password);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuario por ID", description = "Buscar un usuario en especifico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado con exito"),
        @ApiResponse(responseCode = "404", description = "Usuario NO encontrado con exito")
    })
    public ResponseEntity<Usuario> getUsuarioById(
            @Parameter(description = "ID del usuario a buscar") @PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    @Operation(summary = "Registrar un nuevo usuario", description = "Crear un nuevo usuario para guadarlo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    public ResponseEntity<Usuario> createUsuario(
            @Parameter(description = "Datos del nuevo usuario") @RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos de un usuario", description = "Actualizar todos los datos de un usuario")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario NO encontrado")
    })
    public ResponseEntity<Usuario> updateUsuario(
            @Parameter(description = "ID del usuario a actualizar") @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del usuario") @RequestBody Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioService.findById(id);
        if (existingUsuario.isPresent()) {
            usuario.setId(id);
            Usuario updatedUsuario = usuarioService.update(usuario);
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar los datos de un usuario", description = "Actualizar parcialmente algunos datos del usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos actualizados parcialmente exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario NO encontrado")
    })
    public ResponseEntity<Usuario> patchUpdateUsuario(
            @Parameter(description = "ID del usuario a actualizar") @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del usuario") @RequestBody Map<String, Object> updates) {
        Usuario updatedUsuario = usuarioService.patch(id, updates);
        if (updatedUsuario != null) {
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario", description = "Eliminar un usuario en especifico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario NO encontrado")
    })
    public ResponseEntity<Void> deleteUsuario(
            @Parameter(description = "ID del usuario a eliminar") @PathVariable Integer id) {
        if (usuarioService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

