package com.cafeapp.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    @Column(length = 1000)
    private String description;

    private String category;
    
    @Column(length = 1000)
    private String imageUrl;

    @Column(length = 1000)
    private String ingredients;

    private boolean available = true;

    private String targetPrinter; // MUTFAK, BAR, PASTA

    public MenuItem() {
    }

    public MenuItem(String name, double price, String description, String category, String imageUrl, String ingredients, boolean available, String targetPrinter) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
        this.available = available;
        this.targetPrinter = targetPrinter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getTargetPrinter() {
        return targetPrinter;
    }

    public void setTargetPrinter(String targetPrinter) {
        this.targetPrinter = targetPrinter;
    }
}