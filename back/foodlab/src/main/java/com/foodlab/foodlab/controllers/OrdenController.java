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
import com.foodlab.foodlab.models.Receipt;
import com.foodlab.foodlab.models.Usuario;
import com.foodlab.foodlab.services.OrdenService;
import com.foodlab.foodlab.services.ProductoService;
import com.foodlab.foodlab.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author camim
 */
@RestController
@RequestMapping("/foodlab/ordenes")
@Tag(name="Ordenes", description = "API para la gestion de ordenes")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})

public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Obtener todas las ordenes", description = "Devuelve una lista con todas las ordenes y su informacion")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "Lista de ordenes obtenida con exito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Order>> obtenerOrdenes() {
        return new ResponseEntity<>(ordenService.getAllOrder(), HttpStatus.OK);
    }
    
    @GetMapping("/{idUser}")
    @Operation(summary = "Obtener las ordenes de un cliente", description = "Obtener una lista de las ordenes de un cliente en especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de ordenes obtenida con exito"),
        @ApiResponse(responseCode = "404", description = "Cliente NO encontrado")
    })
    public ResponseEntity<List<Order>> obtenerOrdenesPorUsuario(
            @Parameter(description = "ID del cliente para buscar sus ordenes") @PathVariable String idUser) {
        return new ResponseEntity<>(ordenService.getOrdenByUser(idUser), HttpStatus.OK);
    }
    
    @PatchMapping("/{idOrden}")
    @Operation(summary = "Actualizar estado de la orden", description = "Actualizar el estado parcialmente de la orden")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado de la orden actualizado con exito"),
        @ApiResponse(responseCode = "404", description = "Orden NO encontrada")
    })
    public ResponseEntity<Order> updateOrderState(
            @Parameter(description = "ID de la orden a actualizar") @PathVariable String idOrden, 
            @Parameter(description = "Nuevo estado de la orden") @RequestParam(required = true) String estado) {
        return new ResponseEntity<>(ordenService.updateOrderState(idOrden, estado), HttpStatus.CREATED);
    }
    
    @PostMapping
    @Operation(summary="Añadir una orden", description = "Añadir una nueva orden con los productos proporcionados")
    @ApiResponse(responseCode = "200", description = "Se añadio correctamente la orden")
    public ResponseEntity<Order> añadirOrden(
            @Parameter(description = "La orden nueva para guardarla") @RequestBody OrdenRequestDTO ordenRequest) {

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
        
        // 5. Generar factura
        orden.setFactura(new Receipt(orden));

        //Guardamos en la base de datos
        ordenService.save(orden);
        return new ResponseEntity<>(orden, HttpStatus.CREATED);
    }
    
    //Generar PDF con receiptID
    @GetMapping("/{id}/pdf")
    @Operation(summary = "Obtener PDF de Factura con ID", description = "Genera un PDF con la información de la Factura, se usa el ID de la Factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pdf de la Factura obtenido con éxito"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<byte[]> downloadReceiptPdf(@PathVariable @Parameter(description = "ID de la orden que se quiere descargar su factura") String id) {
        Order orden = ordenService.getOrderById(id);
        if (orden == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        byte[] pdfBytes = ordenService.generateReceiptPdf(orden);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("factura_" + id + ".pdf").build());
        
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    
    
}
