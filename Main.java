package com.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        FoodOrderDAO dao = new FoodOrderDAO(factory);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Food Order Management ===");
            System.out.println("1. Place order");
            System.out.println("2. View all orders");
            System.out.println("3. Find order by ID");
            System.out.println("4. Update order status");
            System.out.println("5. Cancel (delete) order");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1 -> {
                    System.out.print("Customer name: ");
                    String name = sc.nextLine();
                    System.out.print("Food item: ");
                    String item = sc.nextLine();
                    System.out.print("Quantity: ");
                    int qty = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Price per item: ");
                    double price = Double.parseDouble(sc.nextLine().trim());
                    dao.save(new FoodOrder(name, item, qty, price));
                    System.out.println("Order placed.");
                }
                case 2 -> {
                    List<FoodOrder> orders = dao.findAll();
                    if (orders.isEmpty()) System.out.println("No orders yet.");
                    orders.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Order ID: ");
                    FoodOrder o = dao.findById(Long.parseLong(sc.nextLine().trim()));
                    System.out.println(o != null ? o : "Order not found.");
                }
                case 4 -> {
                    System.out.print("Order ID: ");
                    Long id = Long.parseLong(sc.nextLine().trim());
                    System.out.print("New status (PLACED/PREPARING/DELIVERED): ");
                    String status = sc.nextLine().trim().toUpperCase();
                    System.out.println(dao.updateStatus(id, status) ? "Status updated." : "Order not found.");
                }
                case 5 -> {
                    System.out.print("Order ID: ");
                    Long id = Long.parseLong(sc.nextLine().trim());
                    System.out.println(dao.delete(id) ? "Order cancelled." : "Order not found.");
                }
                case 6 -> {
                    factory.close();
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
