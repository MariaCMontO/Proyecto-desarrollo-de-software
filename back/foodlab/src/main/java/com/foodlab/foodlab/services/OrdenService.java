/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.services;

import com.foodlab.foodlab.models.OrdenProducto;
import com.foodlab.foodlab.models.Order;
import com.foodlab.foodlab.models.Receipt;
import com.foodlab.foodlab.repositories.OrdenRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author camim
 */
@Service
public class OrdenService {
    
    @Autowired
    private OrdenRepository repository;
    private ProductoService productoService;
    
    public OrdenService() {
    }
    
    public void save(Order orden) {
        repository.save(orden);
    }
    
    public List<Order> getAllOrder() {
        return repository.getAllOrder();
    }
    
    public Order getOrderById(String id) {
        return repository.getOrderById(id);
    }
    
    public List<Order> getOrdenByUser(String idUser) {
        return repository.getOrdenByUser(idUser);
    }
    
    public void removeOrder(String id) {
        repository.removeOrder(id);
    }
    
    public Order updateOrderState(String idOrden, String estado) {
        return repository.updateOrderState(idOrden, estado);
    }
    
    public byte[] generateReceiptPdf(Order orden) {
        System.out.println("Si esta generando la factura");
        Receipt receipt=orden.getFactura();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document() {
        };
        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            //Info factura
            document.add(new Paragraph(" "));
            Paragraph title = new Paragraph("Foodlab");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("                                   ----------------------------------------------------------------------------"));
            document.add(new Paragraph(" "));

            Paragraph p1 = new Paragraph("Aquí encontrarás toda la información de tu factura.");
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("                                   ----------------------------------------------------------------------------"));
            document.add(new Paragraph(" "));

            Paragraph p2 = new Paragraph("Número de Factura: " + receipt.getIdReceipt());
            p2.setAlignment(Element.ALIGN_CENTER);
            document.add(p2);
            document.add(new Paragraph(" "));

            Paragraph p3 = new Paragraph("Número de Orden: " + receipt.getOrder().getIdOrder());
            p3.setAlignment(Element.ALIGN_CENTER);
            document.add(p3);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("                                   ----------------------------------------------------------------------------"));

            Paragraph p4 = new Paragraph("Fecha: " + receipt.getDate());
            p4.setAlignment(Element.ALIGN_CENTER);
            document.add(p4);
            Paragraph p5 = new Paragraph("Método de Pago: " + receipt.getOrder().getUser().getMetodoPago().getMetodo());
            p5.setAlignment(Element.ALIGN_CENTER);
            document.add(p5);

            String numCard = String.valueOf(receipt.getOrder().getUser().getMetodoPago().getNumero());
            Paragraph p6 = new Paragraph("Últimos 4 digitos: " + numCard.substring((numCard.length() - 4), numCard.length()));
            p6.setAlignment(Element.ALIGN_CENTER);
            document.add(p6);
            Paragraph p7 = new Paragraph("Nombre en tarjeta: " + receipt.getOrder().getUser().getMetodoPago().getNombreTarjeta());
            p7.setAlignment(Element.ALIGN_CENTER);
            document.add(p7);

            document.add(new Paragraph(" "));
            Paragraph p8 = new Paragraph("Productos:");
            p8.setAlignment(Element.ALIGN_CENTER);
            document.add(p8);
            for (OrdenProducto prod : receipt.getOrder().getProducts()) {
                Paragraph p9 = new Paragraph("    - " + prod.getProductos().getNombre() + "  $" + prod.getProductos().getPrecio());
                p9.setAlignment(Element.ALIGN_CENTER);
                document.add(p9);
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph("                                   ----------------------------------------------------------------------------"));
            Paragraph p10 = (new Paragraph("Subtotal: $" + receipt.getOrder().getTotal()));
            p10.setAlignment(Element.ALIGN_CENTER);
            document.add(p10);
            Paragraph p11 = new Paragraph("Delivery: $" + receipt.getDelivery());
            p11.setAlignment(Element.ALIGN_CENTER);
            document.add(p11);
            Paragraph p12 = new Paragraph("Descuento: $" + receipt.getDiscount());
            p12.setAlignment(Element.ALIGN_CENTER);
            document.add(p12);
            Paragraph p13 = new Paragraph("Total: $" + receipt.getTotal());
            p13.setAlignment(Element.ALIGN_CENTER);
            document.add(p13);
            document.add(new Paragraph(" "));

            document.add(new Paragraph("                                   ----------------------------------------------------------------------------"));
            document.add(new Paragraph(" "));
            Paragraph footer = new Paragraph("Gracias por usar nuestros servicios. Hasta la próxima!");
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(new Paragraph(footer));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
