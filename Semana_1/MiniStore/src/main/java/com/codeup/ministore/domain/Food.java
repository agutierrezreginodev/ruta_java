/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.ministore.domain;

/**
 *
 * @author Adrián Gutiérrez
 */
public class Food extends Product {

    private String category;
    private String expirationDate;

    public Food(int id, String name, double price, String category, String expirationDate) {
        super(id, name, price);

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category field can't be empty or has a null value");
        }
        this.category = category;

        if (expirationDate == null || expirationDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Expiration date field can't be empty or has a null value");
        }
        this.expirationDate = expirationDate;
    }

    public String getCategory() {
        return category;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category field can't be empty or has a null value");
        }
        this.category = category;
    }

    public void setExpirationDate(String expirationDate) {
        if (expirationDate == null || expirationDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Expiration date field can't be empty or has a null value");
        }
        this.expirationDate = expirationDate;
    }

    @Override
    public String getDescription() {
        return getName() + " - $" + getPrice() + " | Category: " + getCategory() + " | Expiration date: " + getExpirationDate();
    }

}
