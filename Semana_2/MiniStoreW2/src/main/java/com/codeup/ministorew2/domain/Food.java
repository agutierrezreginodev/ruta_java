/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.ministorew2.domain;

/**
 *
 * @author Adrián Gutiérrez
 */
public class Food extends Product {

    private String expirationDate;

    // Constructor
    public Food(String name, double price, String category, String expirationDate) {
        super(name, price, category);

        if (expirationDate == null || expirationDate.trim().isEmpty()) {
            throw new IllegalArgumentException("The expiration date input cannot be null or empty.");
        }

        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        if (expirationDate == null || expirationDate.trim().isEmpty()) {
            throw new IllegalArgumentException("The expiration date input cannot be null or empty.");
        }
        this.expirationDate = expirationDate;
    }

    // Overrided methods
    @Override
    public String getDescription() {
        return "ID: " + getId() + " | " + getName() + " - $" + getPrice() + " | Category: " + getCategory() + " | Expiration date: " + getExpirationDate();
    }
}
