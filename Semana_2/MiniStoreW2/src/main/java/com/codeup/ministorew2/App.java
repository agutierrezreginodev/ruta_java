/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.codeup.ministorew2;

import com.codeup.ministorew2.domain.Appliance;
import com.codeup.ministorew2.domain.Food;
import com.codeup.ministorew2.domain.Inventory;
import javax.swing.JOptionPane;

/**
 *
 * @author Adrián Gutiérrez
 */
public class App {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        boolean continueLoop = true;

        while (continueLoop) {
            try {
                String menu = JOptionPane.showInputDialog("""
                                          ***      MiniStore V2.0      ***
                                          
                                          1. Add product
                                          2. Show inventory
                                          3. Buy products
                                          4. Statistics
                                          5. Search product by name
                                          6. Exit
                                                                
                                          Select an option.
                                          """);

                if (menu == null) {
                    continueLoop = false;
                }

                switch (menu) {
                    case "1":
                        try {
                            String name = JOptionPane.showInputDialog("Input product's name");

                            if (name == null || name.trim().isEmpty()) {
                                break;
                            }

                            String priceStr = JOptionPane.showInputDialog("Input product's price");
                            if (priceStr == null || priceStr.trim().isEmpty()) {
                                break;
                            }
                            double price = Double.parseDouble(priceStr);

                            if (price <= 0) {
                                JOptionPane.showMessageDialog(null, "Price has to be a positive value");
                                break;
                            }

                            String category = JOptionPane.showInputDialog("Product's category");

                            if (category == null || category.trim().isEmpty()) {
                                break;
                            }

                            String typeOfProduct = JOptionPane.showInputDialog("""
                                                                           Select a type of product
                                                                           
                                                                           1. Food
                                                                           2. Appliance
                                                                           
                                                                           Choose an option
                                                                           """);

                            if (typeOfProduct == null || typeOfProduct.trim().isEmpty()) {
                                break;
                            }

                            if (!typeOfProduct.equals("1") && !typeOfProduct.equals("2")) {
                                JOptionPane.showMessageDialog(null, "Choose a valid option");
                                break;
                            }

                            if (typeOfProduct.equals("1")) {
                                String expirationDate = JOptionPane.showInputDialog("Input " + name + "'s expiration date");
                                if (expirationDate == null || expirationDate.trim().isEmpty()) {
                                    break;
                                }

                                String stockStr = JOptionPane.showInputDialog("Units for stock");
                                if (stockStr == null || stockStr.trim().isEmpty()) {
                                    break;
                                }
                                int stock = Integer.parseInt(stockStr);

                                if (stock <= 0) {
                                    JOptionPane.showMessageDialog(null, "Stock has to be a positive value");
                                    break;
                                }

                                Food food = new Food(name, price, category, expirationDate);
                                boolean added = inventory.addProduct(food, stock);
                                if (added) {
                                    JOptionPane.showMessageDialog(null, "Product added successfully!");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Product already exists in inventory");
                                }
                                break;
                            } else if (typeOfProduct.equals("2")) {
                                String warrantyMonthsStr = JOptionPane.showInputDialog("Input " + name + "'s months of warranty");
                                if (warrantyMonthsStr == null || warrantyMonthsStr.trim().isEmpty()) {
                                    break;
                                }

                                int warrantyMonths = Integer.parseInt(warrantyMonthsStr);
                                if (warrantyMonths < 0) {
                                    JOptionPane.showMessageDialog(null, "Months of warranty has to be a positive value");
                                    break;
                                }

                                String stockStr = JOptionPane.showInputDialog("Units for stock");
                                if (stockStr == null || stockStr.trim().isEmpty()) {
                                    break;
                                }
                                int stock = Integer.parseInt(stockStr);

                                if (stock <= 0) {
                                    JOptionPane.showMessageDialog(null, "Stock has to be a positive value");
                                    break;
                                }

                                Appliance appliance = new Appliance(name, price, category, warrantyMonths);
                                boolean added = inventory.addProduct(appliance, stock);
                                if (added) {
                                    JOptionPane.showMessageDialog(null, "Product added successfully!");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Product already exists in inventory");
                                }
                                break;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid number format. Please enter a valid number.");
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                        }
                        break;

                    case "2":
                        String inventoryList = inventory.showInventory();
                        JOptionPane.showMessageDialog(null, inventoryList);
                        break;

                    case "3":
                        try {
                            String productName = JOptionPane.showInputDialog("Input product's name");
                            if (productName == null || productName.trim().isEmpty()) {
                                break;
                            }

                            String quantityStr = JOptionPane.showInputDialog("Enter quantity:");
                            if (quantityStr == null || quantityStr.trim().isEmpty()) {
                                break;
                            }

                            int quantity = Integer.parseInt(quantityStr);

                            if (quantity <= 0) {
                                JOptionPane.showMessageDialog(null, "Quantity must be positive");
                                break;
                            }

                            String purchase = inventory.buyProduct(productName, quantity);
                            JOptionPane.showMessageDialog(null, purchase);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid number format");
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                        }
                        break;

                    case "4":
                        String statistics = inventory.getStatistics();
                        JOptionPane.showMessageDialog(null, statistics);
                        break;

                    case "5":
                        String searchTerm = JOptionPane.showInputDialog("Enter product name to search:");
                        if (searchTerm == null || searchTerm.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Search cancelled");
                            break;
                        }

                        String searchResults = inventory.searchProduct(searchTerm);
                        JOptionPane.showMessageDialog(null, searchResults);
                        break;

                    case "6":
                        continueLoop = false;
                        String finalTicket = inventory.getFinalTicket();
                        JOptionPane.showMessageDialog(null, finalTicket);
                        JOptionPane.showMessageDialog(null, "Thank you for using MiniStore!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Choose a valid option from the menu");
                        continue;
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Input a valid option.  ");
            }
        }

    }
}
