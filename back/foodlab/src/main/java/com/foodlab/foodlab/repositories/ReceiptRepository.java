package com.foodlab.foodlab.repositories;

import com.foodlab.foodlab.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

    public Receipt findByOrderIdOrder(Integer idOrder);
}
