/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.controllers;

import com.foodlab.foodlab.models.Receipt;
import com.foodlab.foodlab.services.PdfService;
import com.foodlab.foodlab.services.ReceiptService;
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

/**
 *
 * @author BryanVanegas
 */
@RestController
@RequestMapping("/foodlab/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;
    private final PdfService pdfService;

    @Autowired
    public ReceiptController(ReceiptService receiptService, PdfService pdfService) {
        this.receiptService = receiptService;
        this.pdfService = pdfService;
    }

    @GetMapping
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        List<Receipt> receipts = receiptService.findAll();
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable String id) {
        Receipt receipt = receiptService.findById(id);
        if (receipt != null) {
            return new ResponseEntity<>(receipt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Receipt> getReceiptByOrderId(@PathVariable String orderId) {
        Receipt receipt = receiptService.findByOrderId(orderId);
        if (receipt != null) {
            return new ResponseEntity<>(receipt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear Receipt 
    @PostMapping
    public ResponseEntity<Receipt> createReceipt(@RequestBody Receipt receipt) {
        Receipt newReceipt = receiptService.save(receipt);
        return new ResponseEntity<>(newReceipt, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadReceiptPdf(@PathVariable String id) {
        Receipt receipt = receiptService.findById(id);
        if (receipt == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] pdfBytes = pdfService.generateReceiptPdf(receipt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "factura_" + id + ".pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
