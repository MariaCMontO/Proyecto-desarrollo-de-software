/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.controllers;

import com.foodlab.foodlab.dtos.OrdenProductoDTO;
import com.foodlab.foodlab.dtos.OrdenRequestDTO;
import com.foodlab.foodlab.models.OrdenProducto;
import com.foodlab.foodlab.models.Order;
import com.foodlab.foodlab.models.Producto;
import com.foodlab.foodlab.models.Usuario;
import com.foodlab.foodlab.services.OrdenService;
import com.foodlab.foodlab.services.ProductoService;
import com.foodlab.foodlab.services.UsuarioService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author camim
 */
@RestController
@RequestMapping("/foodlab/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Order>> obtenerOrdenes() {
        return new ResponseEntity<>(ordenService.getAllOrder(), HttpStatus.OK);
    }
    
    @GetMapping("/{idUser}")
    public ResponseEntity<List<Order>> obtenerOrdenesPorUsuario(@PathVariable String idUser) {
        return new ResponseEntity<>(ordenService.getOrdenByUser(idUser), HttpStatus.OK);
    }
    
    @PatchMapping("/{idOrden}")
    public ResponseEntity<Order> updateOrderState(@PathVariable String idOrden, @RequestParam(required = true) String estado) {
        return new ResponseEntity<>(ordenService.updateOrderState(idOrden, estado), HttpStatus.CREATED);
    }
    
    @PostMapping
    public ResponseEntity<Order> añadirOrden(@RequestBody OrdenRequestDTO ordenRequest) {

//        Recibimos la orden como:
//        userId:1232,
//        productos:[
//          {productoId:1212, cantidad:2, nota:Sin pan},
//          {productoId:1212, cantidad:2, nota:Sin pan}
//        ]

        //Armamos un objeto de tipo orden con esta informacion
        
        // 1. Buscar usuario
        Usuario usuario = usuarioService.findById(ordenRequest.getUserId());

        // 2. Creamos la orden
        String fecha = LocalDate.now().toString();
        Order orden = new Order(fecha, usuario);

        // 3. Buscar todos los productos y añadirlos al arreglo de productos
        for (OrdenProductoDTO p : ordenRequest.getProductos()) {
            Producto producto = productoService.findById(p.getProductoId());
            if (producto != null) {
                OrdenProducto ordenProducto = new OrdenProducto(producto, p.getCantidad(), p.getNota());
                orden.getProducts().add(ordenProducto);
            }
        }
        
        // 4. Calcular total
        orden.calcularTotal();

        //Guardamos en la base de datos
        ordenService.save(orden);
        return new ResponseEntity<>(orden, HttpStatus.CREATED);
    }
    
    
}
