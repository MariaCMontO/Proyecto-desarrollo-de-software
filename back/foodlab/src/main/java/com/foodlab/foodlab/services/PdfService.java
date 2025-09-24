/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.services;

import com.foodlab.foodlab.models.Receipt;
import java.io.ByteArrayOutputStream;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;


/**
 *
 * @author BryanVanegas
 */
@Service
public class PdfService {
    
 public byte[] generateReceiptPdf(Receipt receipt) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document() {};
        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            //Info factura
            document.add(new Paragraph("Factura Nº " + receipt.getIdReceipt()));
            document.add(new Paragraph("Fecha: " + receipt.getDate()));
            document.add(new Paragraph("Número de Orden: " + receipt.getOrder().getIdOrder()));
            document.add(new Paragraph("Método de Pago: " + receipt.getOrder().getUser().getMetodoPago().toString()));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Productos:"));
            for (Integer prod : receipt.getOrder().getProducts()) {
                document.add(new Paragraph("- Producto ID: " + prod));
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Subtotal: $" + receipt.getOrder().getTotal()));
            document.add(new Paragraph("Delivery: $" + receipt.getDelivery()));
            document.add(new Paragraph("Descuento: $" + receipt.getDiscount()));
            document.add(new Paragraph("Total: $" + receipt.getTotal()));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}