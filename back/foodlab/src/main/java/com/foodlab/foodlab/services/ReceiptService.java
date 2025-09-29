/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.services;

import com.foodlab.foodlab.models.MetodoPago;
import com.foodlab.foodlab.models.OrdenProducto;
import com.foodlab.foodlab.models.Order;
import com.foodlab.foodlab.models.Producto;
import com.foodlab.foodlab.models.Receipt;
import com.foodlab.foodlab.models.Usuario;
import com.foodlab.foodlab.repositories.ReceiptRepository;
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
 * @author BryanVanegas
 */
@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepo;

    @Autowired

    public ReceiptService(ReceiptRepository receiptRepo) {
        this.receiptRepo = receiptRepo;
        initSampleData();
    }

    private void initSampleData() {

        Usuario user1 = new Usuario("1", "Messi", "messi@eam.com", "123", "3285410320", "Armenia", "admin");
        MetodoPago metP = new MetodoPago("Tarjeta de Credito", 1234456654321L, "Visa", 1234L);
        user1.setMetodoPago(metP);

        Order o1 = new Order("Viernes 27 - Septiembre", user1);
        Order o2 = new Order("Domingo 28 - Septiembre", user1);
        Order o3 = new Order("Lunes 29 - Septiembre", user1);

        ArrayList<OrdenProducto> oP1 = fillOrderProd1();
        ArrayList<OrdenProducto> oP2 = fillOrderProd2();
        ArrayList<OrdenProducto> oP3 = fillOrderProd3();

        o1.setProducts(oP1);
        o1.setTotal(151.500);
        o2.setProducts(oP2);
        o2.setTotal(57.000);
        o3.setProducts(oP3);
        o3.setTotal(58.500);

        save(new Receipt(o1));
        save(new Receipt(o2));
        save(new Receipt(o3));
    }

    private ArrayList<OrdenProducto> fillOrderProd1() {
        ArrayList<OrdenProducto> op = new ArrayList<>();
        ArrayList<Producto> prods = initProd();
        int counter = 1;

        for (int i = 0; i < 5; i++) {
            op.add(new OrdenProducto(prods.get(i), counter, ""));
            counter++;
        }
        return op;
    }

    private ArrayList<OrdenProducto> fillOrderProd2() {
        ArrayList<OrdenProducto> op = new ArrayList<>();
        ArrayList<Producto> prods = initProd();
        int counter = 1;

        for (int i = 5; i < 7; i++) {
            op.add(new OrdenProducto(prods.get(i), counter, ""));
            counter++;
        }
        return op;
    }

    private ArrayList<OrdenProducto> fillOrderProd3() {
        ArrayList<OrdenProducto> op = new ArrayList<>();
        ArrayList<Producto> prods = initProd();
        int counter = 1;

        for (int i = 7; i < 9; i++) {
            op.add(new OrdenProducto(prods.get(i), counter, ""));
            counter++;
        }
        return op;
    }

    private ArrayList<Producto> initProd() {
        ArrayList<Producto> prods = new ArrayList<>();
        prods.add(new Producto("Hamburguesas", "Big Melt Supreme",
                "Carne jugosa, huevo frito, tocino crujiente y queso cheddar derretido.",
                30000.0, "/hamburguesas_01.jpg"));

        prods.add(new Producto("Hamburguesas", "Crispy Rancher",
                "Pechuga empanizada, queso cheddar, tomate y lechuga fresca.",
                28500.0, "/hamburguesas_02.jpg"));

        prods.add(new Producto("Hamburguesas", "BBQ Sunset",
                "Hamburguesa de pollo a la parrilla con queso suizo y mermelada de tocineta al BBQ.",
                30000.0, "/hamburguesas_03.jpg"));

        prods.add(new Producto("Hamburguesas", "Green Garden Burger",
                "Carne premium con vegetales frescos y toque de queso suave.",
                31000.0, "/hamburguesas_04.jpg"));

        prods.add(new Producto("Hamburguesas", "Cheese Lava Burger",
                "Carne jugosa bañada en queso derretido y vegetales frescos.",
                32000.0, "/hamburguesas_05.jpg"));

        prods.add(new Producto("Hamburguesas", "Double Cheddar Blast",
                "Doble carne, doble queso cheddar y vegetales crujientes.",
                30000.0, "/hamburguesas_06.jpg"));

        prods.add(new Producto("Hamburguesas", "Texas BBQ",
                "Pan brioche, carne de res, queso cheddar, cebolla caramelizada, tocineta y salsa BBQ.",
                27000.0, "/hamburguesas_07.jpg"));

        prods.add(new Producto("Hamburguesas", "Mediterránea Burger",
                "Pan rústico, carne de res, queso feta, tomate seco, rúgula y mayonesa de ajo.",
                30000.0, "/hamburguesas_08.jpg"));

        prods.add(new Producto("Hamburguesas", "Volcán de Queso",
                "Pan artesanal, doble carne de res, mezcla de quesos fundidos, pepinillos y salsa especial.",
                28500.0, "/hamburguesas_09.jpg"));

        prods.add(new Producto("Perros", "Urban Dog",
                "Pan artesanal, salchicha americana, cebolla caramelizada, salsa tártara, mostaza Dijon y perejil fresco.",
                22000.0, "/perros_01.jpg"));

        prods.add(new Producto("Perros", "Tex-Mex Bite",
                "Salchicha parrillera, guacamole cremoso, jalapeños frescos, pico de gallo y salsa cheddar.",
                23500.0, "/perros_02.jpg"));

        prods.add(new Producto("Perros", "Crispy Onion Dog",
                "Salchicha ahumada, mayonesa de ajo, cebolla crujiente frita, lechuga fresca y salsa BBQ ligera.",
                24000.0, "/perros_03.jpg"));

        prods.add(new Producto("Perros", "BBQ Smash Dog",
                "Salchicha tipo Frankfurt, panceta ahumada, queso cheddar fundido, cebolla morada y abundante salsa BBQ.",
                23000.0, "/perros_04.jpg"));

        prods.add(new Producto("Perros", "Cheesy Lover Dog",
                "Salchicha alemana, doble queso fundido (cheddar y mozzarella), salsa de queso, paprika y cebollín.",
                23500.0, "/perros_05.jpg"));

        prods.add(new Producto("Perros", "Sweet & Spicy Dog",
                "Salchicha parrillera, miel natural, mostaza picante, pepinillos encurtidos y cebolla morada.",
                24000.0, "/perros_06.jpg"));

        prods.add(new Producto("Pizzas", "Mediterránea",
                "Tomate, mozzarella, aceitunas negras, pimientos, alcachofas y orégano.",
                25000.0, "/pizzas_01.jpg"));

        prods.add(new Producto("Pizzas", "Campesina",
                "Salsa de tomate, queso mozzarella, maíz tierno, champiñones, tocineta y cebolla morada.",
                24500.0, "/pizzas_02.jpg"));

        prods.add(new Producto("Pizzas", "Cuatro Quesos",
                "Mozzarella, gorgonzola, parmesano y queso crema.",
                24500.0, "/pizzas_03.jpg"));

        prods.add(new Producto("Pizzas", "Boscaiola",
                "Tomate, mozzarella, champiñones, salchicha italiana y cebolla caramelizada.",
                26000.0, "/pizzas_04.jpg"));

        prods.add(new Producto("Pizzas", "Ibérica",
                "Tomate, mozzarella, jamón serrano, rúgula y lascas de parmesano.",
                25500.0, "/pizzas_05.jpg"));

        prods.add(new Producto("Pizzas", "Rústica",
                "Salsa de tomate, mozzarella, chorizo español, pimientos rojos y aceitunas verdes.",
                23000.0, "/pizzas_06.jpg"));

        prods.add(new Producto("Pizzas", "Carbonara",
                "Salsa blanca, mozzarella, tocineta, cebolla y huevo.",
                23000.0, "/pizzas_07.jpg"));

        prods.add(new Producto("Pizzas", "Suprema",
                "Tomate, mozzarella, pepperoni, champiñones, pimientos y aceitunas negras.",
                25000.0, "/pizzas_08.jpg"));

        prods.add(new Producto("Pizzas", "Hawaiana Salada",
                "Tomate, mozzarella, jamón ahumado y piña asada sin azúcar.",
                24000.0, "/pizzas_09.jpg"));

        prods.add(new Producto("Pizzas", "Napolitana",
                "Tomate, mozzarella, anchoas, aceitunas negras y alcaparras.",
                23500.0, "/pizzas_10.jpg"));

        prods.add(new Producto("Pizzas", "Provenzal",
                "Tomate, mozzarella, calabacín, berenjena, ajo asado y orégano fresco.",
                22500.0, "/pizzas_11.jpg"));
        return prods;
    }

    public Receipt save(Receipt receipt) {
        return receiptRepo.save(receipt);
    }

    //Listar todas las facturas (Solo para el consumo/prueba)
    public List<Receipt> findAll() {
        return receiptRepo.findAll();
    }

    //Busca factura por ID (Solo para el consumo/prueba)
    public Receipt findById(String id) {
        return receiptRepo.findById(id);
    }

    //Buscar factura por OrderId
    public Receipt findByOrderId(String idOrder) {
        return receiptRepo.findByOrderId(idOrder);
    }

    //Listar facturas por fecha
    public List<Receipt> findByDate(String date) {
        return receiptRepo.findByDate(date);
    }

    public byte[] generateReceiptPdf(Receipt receipt) {
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
            Paragraph p5 = new Paragraph("Método de Pago: " + receipt.getOrder().getUser().getMetodoPago().getNombreTarjeta());
            p5.setAlignment(Element.ALIGN_CENTER);
            document.add(p5);

            String numCard = String.valueOf(receipt.getOrder().getUser().getMetodoPago().getNumero());
            Paragraph p6 = new Paragraph("Últimos 4 digitos: " + numCard.substring((numCard.length() - 4), numCard.length()));
            p6.setAlignment(Element.ALIGN_CENTER);
            document.add(p6);
            Paragraph p7 = new Paragraph("Tipo: " + receipt.getOrder().getUser().getMetodoPago().getNombreTarjeta());
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
