/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.ministorew2.domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Adrián Gutiérrez
 */
public class Inventory {

    private ArrayList<Product> products;
    private HashMap<String, Integer> stock;
    private double totalPurchases = 0.0;
    private ArrayList<String> purchaseHistory = new ArrayList<>();

    public Inventory() {
        products = new ArrayList<>();
        stock = new HashMap<>();
    }

    public boolean addProduct(Product product, int initialStock) {
        if (product == null) {
            throw new IllegalArgumentException("Can't add the product without its information");
        }

        if (initialStock <= 0) {
            throw new IllegalArgumentException("You need at least one unit to put in the stock");
        }

        String normalizedKey = product.getName().toLowerCase();

        if (stock.containsKey(normalizedKey)) {
            return false;
        }

        products.add(product);
        stock.put(normalizedKey, initialStock);
        return true;
    }

    public String showInventory() {
        StringBuilder sb = new StringBuilder("***      INVENTORY      ***\n\n");

        if (products.isEmpty()) {
            return "There are no products in the inventory";
        }

        for (Product product : products) {
            String hashKey = product.getName().toLowerCase();

            int stockUnits = stock.get(hashKey);

            String description = product.getDescription();

            sb.append(description).append(" | Stock: ").append(stockUnits).append("\n");
        }
        return sb.toString();
    }

    public String searchProduct(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name field's value can't be empty or null");
        }

        String normalizedName = name.trim().toLowerCase();
        StringBuilder sb = new StringBuilder("***      SEARCH RESULTS      ***\n\n");

        boolean found = false;

        for (Product product : products) {
            String productName = product.getName().toLowerCase();

            if (productName.contains(normalizedName)) {
                String hashKey = product.getName().toLowerCase();

                int stockUnits = stock.get(hashKey);

                sb.append(product.getDescription()).append(" | Stock: ").append(stockUnits).append("\n");

                found = true;
            }
        }
        if (!found) {
            return "Product not found in the inventory";
        }
        return sb.toString();
    }

    public Product getProductByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        String normalizedName = name.trim().toLowerCase();

        for (Product product : products) {
            if (product.getName().toLowerCase().equals(normalizedName)) {
                return product;
            }
        }
        return null;
    }

    public String buyProduct(String productName, int requiredQuantity) {

        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name field's value can't be empty or null");
        }

        if (requiredQuantity <= 0) {
            throw new IllegalArgumentException("Quantity field's value has to be a positive");
        }

        Product productFound = getProductByName(productName);

        if (productFound == null) {
            return "Product not found in the inventory";
        }

        String hashKey = productFound.getName().toLowerCase();

        int stockUnits = stock.get(hashKey);

        if (requiredQuantity > stockUnits) {
            return "Not enough units to make the transaction \nAvailable stock: " + stockUnits;
        }

        stock.put(hashKey, stockUnits - requiredQuantity);

        double subtotal = requiredQuantity * productFound.getPrice();

        totalPurchases += subtotal;
        purchaseHistory.add(productFound.getName() + " x" + requiredQuantity + " = $" + subtotal);

        StringBuilder ticket = new StringBuilder("***      PURCHASE TICKET      *** \n\n");

        ticket.append("Product: ").append(productFound.getName()).append("\n");
        ticket.append("Price: $").append(productFound.getPrice()).append("\n");
        ticket.append("Quantity: ").append(requiredQuantity).append("\n");
        ticket.append("Subtotal: ").append(subtotal).append("\n");
        ticket.append("Remaining stock: ").append(stock.get(hashKey));

        return ticket.toString();
    }

    public String getStatistics() {
        if (products.isEmpty()) {
            return "No products in the inventory";
        }

        Product mostExpensive = products.get(0);
        Product cheapest = products.get(0);

        for (Product product : products) {
            if (product.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = product;
            }
            if (product.getPrice() < cheapest.getPrice()) {
                cheapest = product;
            }
        }
        StringBuilder stats = new StringBuilder("***      STATISTICS      ***\n\n");
        stats.append("Most Expensive:\n  - ")
                .append(mostExpensive.getName())
                .append(": $").append(mostExpensive.getPrice())
                .append("\n\n");
        stats.append("Cheapest:\n  - ")
                .append(cheapest.getName())
                .append(": $").append(cheapest.getPrice())
                .append("\n");

        return stats.toString();
    }

    public String getFinalTicket() {
        if (purchaseHistory.isEmpty()) {
            return "No purchases made";
        }

        StringBuilder ticket = new StringBuilder("===== FINAL TICKET =====\n\n");
        for (String purchase : purchaseHistory) {
            ticket.append(purchase).append("\n");
        }
        ticket.append("\n-----------------------\n");
        ticket.append("TOTAL: $").append(totalPurchases);

        return ticket.toString();
    }
}
