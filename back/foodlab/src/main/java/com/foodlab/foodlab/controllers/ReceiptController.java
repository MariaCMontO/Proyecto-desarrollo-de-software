/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodlab.foodlab.models.Receipt;
import com.foodlab.foodlab.services.ReceiptService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 * @author BryanVanegas
 */
@RestController
@RequestMapping("/foodlab/receipts")
@Tag(name = "Facturas", description = "API para la gestión de las Facturas")

public class ReceiptController {

    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    //Obtener todas las facturas
    @GetMapping
    @Operation(summary = "Obtener todas las facturas", description = "Devuelve una lista con todas las facturas y la informacion contenida en ellas (orden, usuario).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de facturas obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        List<Receipt> receipts = receiptService.findAll();
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    //Buscar Receipt con receiptID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener factura por ID", description = "Devuelve una factura específica basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura encontrada"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<Receipt> getReceiptById(@PathVariable @Parameter(description = "ID de la factura") String id) {
        Receipt receipt = receiptService.findById(id);
        if (receipt != null) {
            return new ResponseEntity<>(receipt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Buscar Receipt con orderNumber
    @GetMapping("/order/{orderId}")
    @Operation(summary = "Obtener factura por ID de una orde especifica", description = "Devuelve una factura específica basado en el ID de su orden.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura encontrada"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<Receipt> getReceiptByOrderId(@PathVariable @Parameter(description = "ID de la orden") String orderId) {
        Receipt receipt = receiptService.findByOrderId(orderId);
        if (receipt != null) {
            return new ResponseEntity<>(receipt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear Receipt 
    @PostMapping
    @Operation(summary = "Crear una nueva factura", description = "Crea una nueva factura con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Factura creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Receipt> createReceipt(@RequestBody @Parameter(description = "Datos de la factura a crear") Receipt receipt) {
        Receipt newReceipt = receiptService.save(receipt);
        return new ResponseEntity<>(newReceipt, HttpStatus.CREATED);
    }
    
    //Generar PDF con receiptID
    @GetMapping("/{id}/pdf")
    @Operation(summary = "Obtener PDF de Factura con ID", description = "Genera un PDF con la información de la Factura, se usa el ID de la Factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pdf de la Factura obtenido con éxito"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<byte[]> downloadReceiptPdf(@PathVariable @Parameter(description = "ID de la Factura que se quiere descargar") String id) {
        Receipt receipt = receiptService.findById(id);
        if (receipt == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] pdfBytes = receiptService.generateReceiptPdf(receipt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "factura_" + id + ".pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
