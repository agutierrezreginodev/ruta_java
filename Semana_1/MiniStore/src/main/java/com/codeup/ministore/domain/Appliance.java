/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.ministore.domain;

/**
 *
 * @author Adrián Gutiérrez
 */
public class Appliance extends Product {

    private String category;
    private int warrantyMonths;

    public Appliance(int id, String name, double price, String category, int warrantyMonths) {
        super(id, name, price);

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category field can't be empty or has a null value");
        }
        this.category = category;

        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Months of warranty must be a positive numeric value");
        }
        this.warrantyMonths = warrantyMonths;
    }

    public String getCategory() {
        return category;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category field can't be empty or has a null value");
        }
        this.category = category;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Months of warranty must be a positive numeric value");
        }
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getDescription() {
        return getName() + " - $" + getPrice() + " | Category: " + getCategory() + " | Warranty: " + getWarrantyMonths() + " months";
    }
}
