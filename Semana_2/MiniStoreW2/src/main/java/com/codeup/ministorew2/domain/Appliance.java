/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.ministorew2.domain;

/**
 *
 * @author Adrián Gutiérrez
 */
public class Appliance extends Product {

    private String category;
    private int warrantyMonths;

    // Constructor
    public Appliance(String name, double price, String category, int warrantyMonths) {
        super(name, price, category);

        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("The warranty months quantity cannot be negative.");
        }

        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("The warranty months quantity cannot be negative.");
        }
        this.warrantyMonths = warrantyMonths;
    }

    // Overrided methods
    @Override
    public String getDescription() {
        return "ID: " + getId() + " | " + getName() + " - $" + getPrice() + " | Category: " + getCategory() + " | Warranty: " + getWarrantyMonths() + " months";
    }
}
