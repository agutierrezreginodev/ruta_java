/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.ministorew2.domain;

/**
 *
 * @author Adrián Gutiérrez
 */
public abstract class Product {

    private static int counterId = 0;
    private int id;
    private String name;
    private double price;
    private String category;

    // Constructor
    public Product(String name, double price, String category) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name input can't be empty or with a null value.");
        }

        if (price <= 0.0 || Double.isNaN(price)) {
            throw new IllegalArgumentException("The price has to be a positive number.");
        }

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("The category input cannot be null or empty.");
        }

        counterId++;
        this.id = counterId;
        this.name = name;
        this.price = price;
        this.category = category;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name input can't be empty or with a null value.");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0.0) {
            throw new IllegalArgumentException("The price has to be a positive value.");
        }
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("The category input cannot be null or empty.");
        }
        this.category = category;
    }

    public abstract String getDescription();
}
