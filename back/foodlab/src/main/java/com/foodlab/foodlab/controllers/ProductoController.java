/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.controllers;

import com.foodlab.foodlab.models.Producto;
import com.foodlab.foodlab.services.ProductoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BryanVanegas
 */
@RestController
@RequestMapping("/foodlab/productos")

public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> usuarios = productoService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Buscar por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> getProductoByQuery(@RequestParam String nombre) {
        List<Producto> usuarios = productoService.findByNombre(nombre);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    // Cabeceras

    @GetMapping("/cabecera")
    public ResponseEntity<String> getAgentInfo(@RequestHeader("User-Agent") String userAgent) {
        String info = "Información del cliente (User-Agent): " + userAgent;
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable String idProducto) {
        Producto producto = productoService.findById(idProducto);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto newUsuario = productoService.save(producto);
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);

    }

    // Actualizar un producto existente (reemplazo completo)
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable String idProducto, @RequestBody Producto producto) {
        Producto existingProducto = productoService.findById(idProducto);
        if (existingProducto != null) {
            producto.setId(idProducto);
            Producto updatedUsuario = productoService.update(producto);
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar solo una parte de los datos
    @PatchMapping("/{id}")
    public ResponseEntity<Producto> patchUpdateProducto(@PathVariable String idProducto, @RequestBody Map<String, Object> updates) {
        Producto updatedProducto = productoService.patch(idProducto, updates);
        if (updatedProducto != null) {
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable String idProducto) {
        Producto existingUsuario = productoService.findById(idProducto);
        if (existingUsuario != null) {
            productoService.deleteById(idProducto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
