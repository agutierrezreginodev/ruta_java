/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.codeup.ministore;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * Main class
 */
public class MiniStore {

    // Data modelling
    ArrayList<String> productsNames = new ArrayList<>();        // Products names array
    double[] productsPrices = new double[1];                    // Products price array
    HashMap<String, Integer> productsStock = new HashMap<>();   // Products units in stock

    ArrayList<String> cartProducts = new ArrayList<>();         // Products added to the cart
    ArrayList<Integer> cartQuantities = new ArrayList<>();      // Quantity of products required
    ArrayList<Double> cartPrices = new ArrayList<>();           // Total price by product
    double totalBill = 0.0;                                     // Total cost of the purchase

    public static void main(String[] args) {
        MiniStore ministore = new MiniStore();                      // Instance of the "MiniStore" class

        boolean continueLoop = true;

        // Main menu
        String menu = """
                      *** MiniStore Menu *** 
                      1. Add products
                      2. Show inventory
                      3. Buy product
                      4. Statistics
                      5. Search product by name
                      6. Exit
                      
                      Select an option.""";

        while (continueLoop) {
            try {
                String optionStr = JOptionPane.showInputDialog(menu);

                // If user push cancel button, exits the program
                if (optionStr == null) {
                    System.exit(0);
                }

                int option = Integer.parseInt(optionStr);

                switch (option) {
                    case 1 -> {
                        ministore.addProduct();
                    }
                    case 2 -> {
                        ministore.showInventory();
                    }
                    case 3 -> {
                        ministore.buyProducts();
                    }

                    case 4 -> {
                        ministore.showStatistics();
                    }

                    case 5 -> {
                        ministore.searchProduct();
                    }

                    case 6 -> {
                        continueLoop = false;
                    }
                    default ->
                        JOptionPane.showMessageDialog(null, "Select a valid option");

                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "You must input a numeric value");
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Something went wrong:\n" + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

    }

    /**
     * Add product to the ArrayList of products
     */
    public void addProduct() {
        try {
            String name = JOptionPane.showInputDialog("Input product's name");

            if (name == null) {
                JOptionPane.showMessageDialog(null, "Product addition canceled");
            } else if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error, you submitted an empty field");
            }

            double price = Double.parseDouble(JOptionPane.showInputDialog("Input product's price"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Input units to stock"));

            // Call the expandPrices method before add the product's price
            expandPrices();

            productsNames.add(name);
            productsPrices[productsPrices.length - 1] = price;
            productsStock.put(name, stock);
            JOptionPane.showMessageDialog(null, "Product added successfully");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "You must input a numeric value");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Something went wrong:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Add a new space to the productsPrices array
     */
    public void expandPrices() {
        // Create a new array with an additional space in comparison with the actual
        double[] newProductsPrices = new double[productsPrices.length + 1];

        // Copy all the old array's elements to the new array
        for (int i = 0; i < productsPrices.length; i++) {
            newProductsPrices[i] = productsPrices[i];
        }

        // Replace the original array with the expanded
        productsPrices = newProductsPrices;
    }

    /**
     * Show the total inventory with price and units in stock
     */
    public void showInventory() {
        if (productsStock.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products in inventory");
            return;
        }

        StringBuilder inventory = new StringBuilder();
        inventory.append("*** Inventory ***\n\n");

        for (HashMap.Entry<String, Integer> entry : productsStock.entrySet()) {
            String productName = entry.getKey();
            int productStock = entry.getValue();

            // Encontrar el precio correspondiente
            int index = productsNames.indexOf(productName);
            if (index != -1) {
                double productPrice = productsPrices[index];

                inventory.append(String.format("Product: %-10s | Price: $%6.2f | Stock: %3d\n",
                        productName, productPrice, productStock));
            }
        }

        JOptionPane.showMessageDialog(null, inventory.toString());
    }

    /**
     * Buy product functionality with shopping cart
     */
    public void buyProducts() {
        if (productsNames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products available in store");
            return;
        }

        boolean continueShopping = true;

        while (continueShopping) {
            try {
                // Mostrar productos disponibles
                StringBuilder availableProducts = new StringBuilder();
                availableProducts.append("Available Products:\n\n");

                for (int i = 0; i < productsNames.size(); i++) {
                    String name = productsNames.get(i);
                    double price = productsPrices[i];
                    int stock = productsStock.get(name);

                    availableProducts.append(String.format("%d. %s - $%.2f (Stock: %d)\n",
                            i + 1, name, price, stock));
                }

                availableProducts.append("\nEnter product name to buy:");

                String productName = JOptionPane.showInputDialog(availableProducts.toString());

                if (productName == null) {
                    break;
                }

                // Paso 1: Verificar si el producto existe
                if (!productsNames.contains(productName)) {
                    JOptionPane.showMessageDialog(null,
                            "Product '" + productName + "' not found in inventory");
                    continue;
                }

                // Obtener información del producto
                int productIndex = productsNames.indexOf(productName);
                double productPrice = productsPrices[productIndex];
                int currentStock = productsStock.get(productName);

                // Mostrar información del producto seleccionado
                String productInfo = String.format(
                        "Product: %s\nPrice: $%.2f\nAvailable Stock: %d units\n\nHow many units do you want to buy?",
                        productName, productPrice, currentStock
                );

                String quantityStr = JOptionPane.showInputDialog(productInfo);

                if (quantityStr == null) {
                    continue; // Usuario canceló, volver al menú de productos
                }

                int requestedQuantity = Integer.parseInt(quantityStr);

                // Validar cantidad positiva
                if (requestedQuantity <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a positive quantity");
                    continue;
                }

                // Paso 2: Verificar si hay suficiente stock
                if (requestedQuantity > currentStock) {
                    JOptionPane.showMessageDialog(null,
                            String.format("Insufficient stock!\nRequested: %d units\nAvailable: %d units",
                                    requestedQuantity, currentStock));
                    continue;
                }

                // Paso 3: Calcular total y añadir al carrito
                double productTotal = productPrice * requestedQuantity;

                // Añadir al carrito
                cartProducts.add(productName);
                cartQuantities.add(requestedQuantity);
                cartPrices.add(productPrice);
                totalBill += productTotal;

                // Paso 4: Actualizar stock
                productsStock.put(productName, currentStock - requestedQuantity);

                // Confirmación de añadido al carrito
                String confirmation = String.format(
                        "Added to cart:\n%s x %d = $%.2f\n\nCurrent total: $%.2f\n\nDo you want to continue shopping?",
                        productName, requestedQuantity, productTotal, totalBill
                );

                int continueChoice = JOptionPane.showConfirmDialog(null, confirmation,
                        "Continue Shopping?",
                        JOptionPane.YES_NO_OPTION);

                if (continueChoice == JOptionPane.NO_OPTION) {
                    continueShopping = false;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "You must input a numeric value");
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Something went wrong:\n" + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        // Paso 5: Mostrar factura final si hay productos en el carrito
        if (!cartProducts.isEmpty()) {
            showBill();
            clearCart();
        }
    }

    /**
     * Show the final bill with all purchased items
     */
    public void showBill() {
        StringBuilder bill = new StringBuilder();
        bill.append("========== PURCHASE RECEIPT ==========\n\n");

        for (int i = 0; i < cartProducts.size(); i++) {
            String product = cartProducts.get(i);
            int quantity = cartQuantities.get(i);
            double price = cartPrices.get(i);
            double subtotal = price * quantity;

            bill.append(String.format("%-15s x %2d @ $%6.2f = $%8.2f\n",
                    product, quantity, price, subtotal));
        }

        bill.append("\n=====================================\n");
        bill.append(String.format("TOTAL TO PAY: $%.2f\n", totalBill));
        bill.append("=====================================\n");
        bill.append("Thank you for your purchase!");

        JOptionPane.showMessageDialog(null, bill.toString());
    }

    /**
     * Clear the shopping cart after purchase
     */
    public void clearCart() {
        cartProducts.clear();
        cartQuantities.clear();
        cartPrices.clear();
        totalBill = 0.0;
    }

    public void showStatistics() {
        if (productsNames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products to show statistics");
            return;
        }

        StringBuilder stats = new StringBuilder();
        stats.append("========== STORE STATISTICS ==========\n\n");

        // Total productos
        int totalProducts = productsNames.size();
        stats.append("Total Products: ").append(totalProducts).append("\n");

        // Valor total del inventario
        double totalInventoryValue = 0;
        int totalUnits = 0;

        for (int i = 0; i < productsNames.size(); i++) {
            String name = productsNames.get(i);
            double price = productsPrices[i];
            int stock = productsStock.get(name);

            totalInventoryValue += (price * stock);
            totalUnits += stock;
        }

        stats.append("Total Units in Stock: ").append(totalUnits).append("\n");
        stats.append(String.format("Total Inventory Value: $%.2f\n", totalInventoryValue));

        // Producto más caro y más barato
        if (totalProducts > 0) {
            double maxPrice = productsPrices[0];
            double minPrice = productsPrices[0];
            String mostExpensive = productsNames.get(0);
            String cheapest = productsNames.get(0);

            for (int i = 1; i < productsNames.size(); i++) {
                double price = productsPrices[i];
                if (price > maxPrice) {
                    maxPrice = price;
                    mostExpensive = productsNames.get(i);
                }
                if (price < minPrice) {
                    minPrice = price;
                    cheapest = productsNames.get(i);
                }
            }

            stats.append(String.format("\nMost Expensive: %s ($%.2f)\n", mostExpensive, maxPrice));
            stats.append(String.format("Cheapest: %s ($%.2f)\n", cheapest, minPrice));
        }

        stats.append("\n=====================================");

        JOptionPane.showMessageDialog(null, stats.toString());
    }

    public void searchProduct() {
        if (productsNames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products in inventory");
            return;
        }

        String searchName = JOptionPane.showInputDialog("Enter product name to search:");

        if (searchName == null || searchName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Search canceled or empty");
            return;
        }

        // Buscar producto (búsqueda parcial - case insensitive)
        StringBuilder results = new StringBuilder();
        results.append("========== SEARCH RESULTS ==========\n\n");

        boolean found = false;
        for (int i = 0; i < productsNames.size(); i++) {
            String name = productsNames.get(i);

            // Búsqueda que no distingue mayúsculas/minúsculas y permite coincidencias parciales
            if (name.toLowerCase().contains(searchName.toLowerCase())) {
                double price = productsPrices[i];
                int stock = productsStock.get(name);

                results.append(String.format("Product: %s\n", name));
                results.append(String.format("Price: $%.2f\n", price));
                results.append(String.format("Stock: %d units\n", stock));
                results.append("-------------------------\n");
                found = true;
            }
        }

        if (!found) {
            results.append("No products found matching '").append(searchName).append("'");
        }

        JOptionPane.showMessageDialog(null, results.toString());
    }
}
