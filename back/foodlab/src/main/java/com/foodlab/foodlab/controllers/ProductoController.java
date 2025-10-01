/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.controllers;

import com.foodlab.foodlab.models.Producto;
import com.foodlab.foodlab.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/foodlab/productos")
@Tag(name = "Productos", description = "API para la gestion de productos")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})

public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista con todos los productos y la informacion contenida en ellos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> usuarios = productoService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar un producto por nombre", description = "Buscar un productos en especifico por su nombre parcial o completo")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida con exito"),
        @ApiResponse(responseCode = "404", description = "Productos no encontrados")
    })
    public ResponseEntity<List<Producto>> getProductoByQuery(
            @Parameter(description = "Nombre del producto a buscar") @RequestParam String nombre) {
        List<Producto> usuarios = productoService.findByNombre(nombre);
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

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un producto por su ID", description = "Buscar un producto en especifico por su ID")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "Producto encontrado con exito"),
        @ApiResponse(responseCode = "404", description = "Producto NO encontrado")
    })
    public ResponseEntity<Producto> getProductoById(
            @Parameter(description = "ID del producto a buscar") @PathVariable String idProducto) {
        Producto producto = productoService.findById(idProducto);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    @Operation(summary = "Crear un nuevo producto", description = "Crear y registrar un nuevo producto")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description = "Se creo y registro correctamente el producto"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    public ResponseEntity<Producto> createProducto(
            @Parameter(description = "Datos del nuevo producto a crear") @RequestBody Producto producto) {
        Producto newUsuario = productoService.save(producto);
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);

    }

    @PutMapping("/{idProducto}")
    @Operation(summary = "Actualizar los datos de un producto", description = "Actualizar todos los datos por completo de un producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se actualizo correctamente el producto"),
        @ApiResponse(responseCode = "404", description = "Producto NO encontrado")
    })
    public ResponseEntity<Producto> updateProducto(
            @Parameter(description = "ID del producto a actualizar") @PathVariable String idProducto, 
            @Parameter(description = "Datos actualizados del producto") @RequestBody Producto producto) {
        Producto existingProducto = productoService.findById(idProducto);
        if (existingProducto != null) {
            producto.setId(idProducto);
            Producto updatedUsuario = productoService.update(producto);
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar algunos datos de un producto", description = "Actualizar algunos datos parcialmente de un producto")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "Se actualizaron los datos correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto NO encontrado")
    })
    public ResponseEntity<Producto> patchUpdateProducto(
            @Parameter(description = "ID del producto a actualizar") @PathVariable String idProducto, 
            @Parameter(description = "Datos actualizados del producto") @RequestBody Map<String, Object> updates) {
        Producto updatedProducto = productoService.patch(idProducto, updates);
        if (updatedProducto != null) {
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idProducto}")
    @Operation(summary = "Eliminar un producto", description = "Eliminar un producto en especifico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto NO encontrado")
    })
    public ResponseEntity<Void> deleteProducto(
            @Parameter(description = "ID del usuario a eliminar") @PathVariable String idProducto) {
        Producto existingUsuario = productoService.findById(idProducto);
        if (existingUsuario != null) {
            productoService.deleteById(idProducto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
