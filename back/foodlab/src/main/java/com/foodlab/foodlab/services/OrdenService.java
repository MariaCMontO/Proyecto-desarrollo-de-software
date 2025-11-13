/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.services;

import com.foodlab.foodlab.dto.OrdenProductoDTO;
import com.foodlab.foodlab.dto.OrdenRequestDTO;
import com.foodlab.foodlab.models.*;
import com.foodlab.foodlab.repositories.OrdenRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author camim
 */
@Service
public class OrdenService {

    private final OrdenRepository repository;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final ReceiptService receiptService;

    public OrdenService(OrdenRepository repository, ProductoService productoService, UsuarioService usuarioService, ReceiptService receiptService) {
        this.repository = repository;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.receiptService = receiptService;
        //initSampleData();
    }

    private void initSampleData() {
        OrdenProductoDTO ordProdDTO1 = new OrdenProductoDTO(1, 5);
        OrdenProductoDTO ordProdDTO2 = new OrdenProductoDTO(2, 3);
        OrdenProductoDTO ordProdDTO3 = new OrdenProductoDTO(3, 1);

        List<OrdenProductoDTO> productos = new ArrayList<>();
        productos.add(ordProdDTO1);
        productos.add(ordProdDTO2);
        productos.add(ordProdDTO3);

        OrdenRequestDTO orderReqDTO = new OrdenRequestDTO(3, productos);
        this.realizarPedido(orderReqDTO);
    }

    // El "CREATE" de Pedido es la lógica de negocio principal
    @Transactional // ¡Importante! Si algo falla, revierte todo.
    public Order realizarPedido(OrdenRequestDTO orderRequest) {

        // 1. Validar que el usuario exista
        Usuario usuario =
                usuarioService.findById(orderRequest.getUserId())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + orderRequest.getUserId()));

        if (usuario.getMetodoPago() == null) {
            return null;
        }

        // 2. Crear la cabecera del Pedido
        Order nuevaOrden = new Order();
        nuevaOrden.setUser(usuario);
        //nuevaOrden.setState("PENDIENTE");

        List<OrdenProducto> ordenProductoList = new ArrayList<>();
        int cantidad = 0;
        // 3. Procesar cada item del carrito (DTO)
        for (OrdenProductoDTO productoDTO : orderRequest.getProductos()) {

            // 3.1 Validar que el producto exista
            Producto producto = productoService.findById(productoDTO.getProductoId());

            if(producto == null) {
                return null;
            }

            // 3.2 Crear el ItemPedido (la entidad de la tabla intermedia)
            OrdenProducto ordenProducto = new OrdenProducto();
            ordenProducto.setProducto(producto);
            ordenProducto.setCantidad(productoDTO.getCantidad());
            ordenProducto.setPrecio(producto.getPrecio()); //Guarda el precio del momento
            ordenProducto.setOrder(nuevaOrden); // Vincula este itemPedido al nuevo pedido

            cantidad += productoDTO.getCantidad();

            ordenProductoList.add(ordenProducto);
        }

        // 4. Asignar la lista de items al pedido
        // Gracias al CascadeType.ALL en Pedido.java,
        // JPA guardará los items automáticamente al guardar el pedido.
        nuevaOrden.setProducts(ordenProductoList);
        nuevaOrden.setTotal(calcularTotal(ordenProductoList));
        // 5. Guardar el Pedido (y sus items, en cascada)

        Receipt factura = new Receipt();
        nuevaOrden.setFactura(factura);
        factura.setOrder(nuevaOrden);
        factura.setDiscount(receiptService.calculateDiscount(cantidad));
        factura.setTotal(receiptService.calculateTotal(factura, nuevaOrden));

        return repository.save(nuevaOrden);
    }

    public double calcularTotal(List<OrdenProducto> productos) {
        double retornoTotal = 0;

        for (OrdenProducto ordenProducto : productos) {
            retornoTotal += ordenProducto.getPrecio() * ordenProducto.getCantidad();
        }
        return retornoTotal;
    }

    public void save(Order orden) {
        repository.save(orden);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Optional<Order> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Order> findByUserId(Integer idUser) {
        usuarioService.findById(idUser).orElseThrow(() ->
                new RuntimeException("No se encotro el usuario"));
        return repository.findByUserId(idUser);
    }

    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Order updateOrderState(Integer idOrden, String estado) {
        return repository.findById(idOrden)
                .map(order -> {
                    order.setStatus(estado);
                    return repository.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con id: " + idOrden));
    }

//    public byte[] generateReceiptPdf(Order orden) {
//        System.out.println("Si esta generando la factura");
//        Receipt receipt=orden.getFactura();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        Document document = new Document() {
//        };
//        try {
//            PdfWriter.getInstance(document, baos);
//            document.open();
//
//            //Info factura
//            document.add(new Paragraph(" "));
//            Paragraph title = new Paragraph("Foodlab");
//            title.setAlignment(Element.ALIGN_CENTER);
//            document.add(title);
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph("                                   ----------------------------------------------------------------------------"));
//            document.add(new Paragraph(" "));
//
//            Paragraph p1 = new Paragraph("Aquí encontrarás toda la información de tu factura.");
//            p1.setAlignment(Element.ALIGN_CENTER);
//            document.add(p1);
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph("                                   ----------------------------------------------------------------------------"));
//            document.add(new Paragraph(" "));
//
//            Paragraph p2 = new Paragraph("Número de Factura: " + receipt.getIdReceipt());
//            p2.setAlignment(Element.ALIGN_CENTER);
//            document.add(p2);
//            document.add(new Paragraph(" "));
//
//            Paragraph p3 = new Paragraph("Número de Orden: " + receipt.getOrder().getIdOrder());
//            p3.setAlignment(Element.ALIGN_CENTER);
//            document.add(p3);
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph("                                   ----------------------------------------------------------------------------"));
//
//            Paragraph p4 = new Paragraph("Fecha: " + receipt.getDate());
//            p4.setAlignment(Element.ALIGN_CENTER);
//            document.add(p4);
////            Paragraph p5 = new Paragraph("Método de Pago: " + receipt.getOrder().getUser().getMetodoPago().getMetodo());
////            p5.setAlignment(Element.ALIGN_CENTER);
////            document.add(p5);
////
////            String numCard = String.valueOf(receipt.getOrder().getUser().getMetodoPago().getNumero());
////            Paragraph p6 = new Paragraph("Últimos 4 digitos: " + numCard.substring((numCard.length() - 4), numCard.length()));
////            p6.setAlignment(Element.ALIGN_CENTER);
////            document.add(p6);
////            Paragraph p7 = new Paragraph("Nombre en tarjeta: " + receipt.getOrder().getUser().getMetodoPago().getNombreTarjeta());
////            p7.setAlignment(Element.ALIGN_CENTER);
////            document.add(p7);
//
//            document.add(new Paragraph(" "));
//            Paragraph p8 = new Paragraph("Productos:");
//            p8.setAlignment(Element.ALIGN_CENTER);
//            document.add(p8);
//            for (OrdenProducto prod : receipt.getOrder().getProducts()) {
//                Paragraph p9 = new Paragraph("    - " + prod.getProductos().getNombre() + "  $" + prod.getProductos().getPrecio());
//                p9.setAlignment(Element.ALIGN_CENTER);
//                document.add(p9);
//            }
//
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph("                                   ----------------------------------------------------------------------------"));
//            Paragraph p10 = (new Paragraph("Subtotal: $" + receipt.getOrder().getTotal()));
//            p10.setAlignment(Element.ALIGN_CENTER);
//            document.add(p10);
//            Paragraph p11 = new Paragraph("Delivery: $" + receipt.getDelivery());
//            p11.setAlignment(Element.ALIGN_CENTER);
//            document.add(p11);
//            Paragraph p12 = new Paragraph("Descuento: $" + receipt.getDiscount());
//            p12.setAlignment(Element.ALIGN_CENTER);
//            document.add(p12);
//            Paragraph p13 = new Paragraph("Total: $" + receipt.getTotal());
//            p13.setAlignment(Element.ALIGN_CENTER);
//            document.add(p13);
//            document.add(new Paragraph(" "));
//
//            document.add(new Paragraph("                                   ----------------------------------------------------------------------------"));
//            document.add(new Paragraph(" "));
//            Paragraph footer = new Paragraph("Gracias por usar nuestros servicios. Hasta la próxima!");
//            footer.setAlignment(Element.ALIGN_CENTER);
//            document.add(new Paragraph(footer));
//
//            document.close();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        return baos.toByteArray();
//    }
}
