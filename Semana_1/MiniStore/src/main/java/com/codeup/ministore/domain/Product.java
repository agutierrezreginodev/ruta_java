/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.ministore.domain;

/**
 *
 * @author Adrián Gutiérrez
 */
public abstract class Product {

    private static int counterId = 0;
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name field can't be empty or has a null value");
        }
        this.name = name;

        if (price < 0.0) {
            throw new IllegalArgumentException("Price field has to contain a positive value");
        }
        this.price = price;

        counterId++;
        this.id = counterId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name field can't be empty or has a null value");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0.0) {
            throw new IllegalArgumentException("Price field has to contain a positive value");
        }
        this.price = price;
    }

    public abstract String getDescription();

}
