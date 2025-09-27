/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.services;

import com.foodlab.foodlab.models.MetodoPago;
import com.foodlab.foodlab.models.Order;
import com.foodlab.foodlab.models.Receipt;
import com.foodlab.foodlab.models.Usuario;
import com.foodlab.foodlab.repositories.ReceiptRepository;
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
        
        Usuario user1 = new Usuario("123", "Juan", "Email", "contra", "123", "direccion");
        MetodoPago metP = new MetodoPago("Tarjeta de Credito", 1234456654321L, "Visa", 1234L);
        user1.setMetodoPago(metP);
        
        Order o1 = new Order("Hoy", user1);
        Order o2 = new Order("Viernes", user1);
        Order o3 = new Order("Sabado", user1);
        Order o4 = new Order("Domingo", user1);
        
        save(new Receipt(o1));
        save(new Receipt(o2));
        save(new Receipt(o3));
        save(new Receipt(o4));
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
}

