package com.trade;

import java.util.*;

import java.io.FileWriter;
import java.io.IOException;

class Stock {
    String symbol;
    String name;
    double price;

    Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }
}

class User {

    String name;
    double balance = 10000;

    HashMap<String, Integer> portfolio = new HashMap<>();

    User(String name) {
        this.name = name;
    }
}

class Transaction {

    String type;
    String stock;
    int quantity;
    double price;

    Transaction(String type, String stock,
                int quantity, double price) {

        this.type = type;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
    }

    public String toString() {
        return type + " " + stock +
               " Quantity: " + quantity +
               " Price: " + price;
    }
}

public class StockTradingPlatform {

    static Scanner sc = new Scanner(System.in);

    static User user = new User("Rahul");

    static ArrayList<Stock> stocks = new ArrayList<>();

    static ArrayList<Transaction> transactions =
            new ArrayList<>();

    static ArrayList<Double> performanceHistory =
            new ArrayList<>();

    // Initialize Market
    static void initializeMarket() {

        stocks.add(new Stock("TCS", "Tata Consultancy", 3500));
        stocks.add(new Stock("INFY", "Infosys", 1800));
        stocks.add(new Stock("RELIANCE", "Reliance", 2500));
        stocks.add(new Stock("HDFC", "HDFC Bank", 1600));
        stocks.add(new Stock("WIPRO", "Wipro", 500));
    }

    // Display Market Data
    static void displayStocks() {

        System.out.println("\n===== STOCK MARKET =====");

        System.out.println("SYMBOL\tNAME\t\tPRICE");

        for (Stock s : stocks) {

            System.out.println(
                s.symbol + "\t" +
                s.name + "\t" +
                s.price
            );
        }
    }

    // Find Stock
    static Stock findStock(String symbol) {

        for (Stock s : stocks) {

            if (s.symbol.equalsIgnoreCase(symbol)) {
                return s;
            }
        }

        return null;
    }

    // Calculate Portfolio Value
    static double portfolioValue() {

        double total = 0;

        for (String symbol : user.portfolio.keySet()) {

            Stock s = findStock(symbol);

            if (s != null) {

                int quantity = user.portfolio.get(symbol);

                total += quantity * s.price;
            }
        }

        return total;
    }

    // Buy Stock
    static void buyStock() {

        displayStocks();

        System.out.print("\nEnter Stock Symbol: ");
        String symbol = sc.next().toUpperCase();

        Stock s = findStock(symbol);

        if (s == null) {
            System.out.println("Stock Not Found!");
            return;
        }

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("Invalid Quantity!");
            return;
        }

        double cost = s.price * quantity;

        if (cost > user.balance) {

            System.out.println("Insufficient Balance!");
            return;
        }

        user.balance -= cost;

        int oldQuantity =
            user.portfolio.getOrDefault(symbol, 0);

        user.portfolio.put(symbol, oldQuantity + quantity);

        transactions.add(
            new Transaction("BUY", symbol, quantity, s.price)
        );

        System.out.println("Stock Purchased Successfully!");

        savePerformance();
    }

    // Sell Stock
    static void sellStock() {

        System.out.print("\nEnter Stock Symbol: ");
        String symbol = sc.next().toUpperCase();

        Stock s = findStock(symbol);

        if (s == null) {
            System.out.println("Stock Not Found!");
            return;
        }

        int owned = user.portfolio.getOrDefault(symbol, 0);

        if (owned == 0) {
            System.out.println("You Don't Own This Stock!");
            return;
        }

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        if (quantity <= 0 || quantity > owned) {

            System.out.println("Invalid Quantity!");
            return;
        }

        double amount = s.price * quantity;

        user.balance += amount;

        user.portfolio.put(symbol, owned - quantity);

        transactions.add(
            new Transaction("SELL", symbol, quantity, s.price)
        );

        System.out.println("Stock Sold Successfully!");

        savePerformance();
    }

    // Display Portfolio
    static void displayPortfolio() {

        System.out.println("\n===== MY PORTFOLIO =====");

        System.out.println("User: " + user.name);

        System.out.println("Available Balance: " + user.balance);

        System.out.println("\nSTOCK\tQUANTITY\tVALUE");

        for (String symbol : user.portfolio.keySet()) {

            int quantity = user.portfolio.get(symbol);

            if (quantity > 0) {

                Stock s = findStock(symbol);

                System.out.println(
                    symbol + "\t" +
                    quantity + "\t\t" +
                    (quantity * s.price)
                );
            }
        }

        System.out.println(
            "\nTotal Portfolio Value: " + portfolioValue()
        );

        System.out.println(
            "Total Account Value: " +
            (user.balance + portfolioValue())
        );
    }

    // Transaction History
    static void showTransactions() {

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactions.isEmpty()) {
            System.out.println("No Transactions Found!");
            return;
        }

        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    // Save Portfolio Performance
    static void savePerformance() {

        double total = user.balance + portfolioValue();

        performanceHistory.add(total);
    }

    // Show Performance History
    static void showPerformance() {

        System.out.println("\n===== PERFORMANCE HISTORY =====");

        if (performanceHistory.isEmpty()) {
            System.out.println("No Performance Records!");
            return;
        }

        for (int i = 0; i < performanceHistory.size(); i++) {

            System.out.println(
                "Record " + (i + 1) +
                " : " + performanceHistory.get(i)
            );
        }
    }

    // Simulate Next Trading Day
    static void updateMarket() {

        Random random = new Random();

        System.out.println("\n===== MARKET UPDATE =====");

        for (Stock s : stocks) {

            double change = (random.nextDouble() * 0.10) - 0.05;

            s.price = s.price * (1 + change);

            System.out.println(
                s.symbol + " New Price: " +
                String.format("%.2f", s.price)
            );
        }

        savePerformance();

        System.out.println("Market Updated Successfully!");
    }

    // Save Transactions to File
    static void saveToFile() {

        try {

            FileWriter writer =
                new FileWriter("transactions.txt");

            writer.write("===== TRANSACTION HISTORY =====\n");

            for (Transaction t : transactions) {

                writer.write(t.toString() + "\n");
            }

            writer.close();

            System.out.println(
                "Transactions Saved Successfully!"
            );

        } catch (IOException e) {

            System.out.println("File Error: " + e.getMessage());
        }
    }

    // Main Method
    public static void main(String[] args) {

        initializeMarket();

        savePerformance();

        while (true) {

            System.out.println("\n================================");
            System.out.println("     STOCK TRADING PLATFORM");
            System.out.println("================================");

            System.out.println("1. Display Stock Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Transaction History");
            System.out.println("6. Performance History");
            System.out.println("7. Simulate Next Trading Day");
            System.out.println("8. Save Transactions");
            System.out.println("9. Exit");

            System.out.print("\nEnter Your Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayStocks();
                    break;

                case 2:
                    buyStock();
                    break;

                case 3:
                    sellStock();
                    break;

                case 4:
                    displayPortfolio();
                    break;

                case 5:
                    showTransactions();
                    break;

                case 6:
                    showPerformance();
                    break;

                case 7:
                    updateMarket();
                    break;

                case 8:
                    saveToFile();
                    break;

                case 9:
                    System.out.println("Thank You For Using Platform!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }


}

