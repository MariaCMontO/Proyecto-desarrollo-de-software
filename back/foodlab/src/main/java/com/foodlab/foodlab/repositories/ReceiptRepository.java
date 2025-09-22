/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.repositories;

import com.foodlab.foodlab.models.Receipt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BryanVanegas
 */
@Repository
public class ReceiptRepository {

    private final Map<String, Receipt> db = new HashMap<>();

    //Guarda factura
    public Receipt save(Receipt receipt) {
        db.put(receipt.getIdReceipt(), receipt);
        return receipt;
    }

    //Listar todas las facturas (Solo para el consumo/prueba)
    public List<Receipt> findAll() {
        return new ArrayList<>(db.values());
    }

    //Busca factura por ID (Solo para el consumo/prueba)
    public Receipt findById(String id) {
        return db.get(id);
    }

    //Buscar factura por OrderId
    public Receipt findByOrderId(String idOrder) {
        for (Receipt receipt : db.values()) {
            if (receipt.getOrder() != null
                    && receipt.getOrder().getIdOrder() != null
                    && receipt.getOrder().getIdOrder().equalsIgnoreCase(idOrder)) {
                return receipt;
            }
        }
        return null;
    }

    //Listar facturas por fecha
    public List<Receipt> findByDate(String date) {
        return db.values().stream()
                .filter(receipt
                        -> receipt.getDate().toLowerCase().contains(date.toLowerCase()))
                .collect(Collectors.toList());
    }
}
