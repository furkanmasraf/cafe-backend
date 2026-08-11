package com.cafeapp.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long menuItemId;
    private String menuItemName;
    private double unitPrice;
    private int quantity;

    @Column(length = 1000)
    private String customerNote; // Örn: "Soğansız, bol soslu"

    private String targetPrinter; // MUTFAK, BAR vb.

    public OrderItem() {
    }

    public OrderItem(Long menuItemId, String menuItemName, double unitPrice, int quantity, String customerNote, String targetPrinter) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.customerNote = customerNote;
        this.targetPrinter = targetPrinter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public String getTargetPrinter() {
        return targetPrinter;
    }

    public void setTargetPrinter(String targetPrinter) {
        this.targetPrinter = targetPrinter;
    }
}
