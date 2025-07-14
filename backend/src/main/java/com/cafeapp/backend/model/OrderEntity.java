package com.cafeapp.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tableId;
    private double totalPrice;
    private boolean paid;

    public OrderEntity(boolean paid) {
        this.paid = paid;
    }
    public void setPaid(boolean b) {

    }
    public Long getTableId() {
        return tableId;
    }
    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public boolean isPaid() {
        return paid;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
