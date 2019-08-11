package com.shopstockproject;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        createStockList();
        System.out.println(stockList);
        System.out.println();

        Basket myBasket = new Basket("Mr MOHAN");
        adjustBasket(myBasket, "cake", 5);
        adjustBasket(myBasket, "cup", 3);
        adjustBasket(myBasket, "vase", 10);
        System.out.println("\n************* ADD IN BASKET *************" + myBasket);
        System.out.println(stockList);


        adjustBasket(myBasket, "bread", 7);
        removeItem(myBasket, "cake", 3);
        removeItem(myBasket, "cup", 1);
        System.out.println("\n************* REMOVE/ADD *************" + myBasket);
        System.out.println(stockList);


        checkOut(myBasket);
        System.out.println("************* CHECK -> PURCHASE *************" + myBasket);

    }

    private static void adjustBasket(Basket basket, String name, int quantity) {
        if (basket != null && quantity > 0) {
            StockItem inStock = stockList.getStockItem(name);
            if (inStock == null) {
                System.out.println("\nWe don't sell " + name);
                return;
            } else if (stockList.reserveStock(name, quantity) != 0) {
                basket.addToBasket(inStock, quantity);
            }
        }
    }

    private static void removeItem(Basket basket, String name, int quantity) {
        if (basket != null && quantity > 0) {
            StockItem inStock = stockList.getStockItem(name);
            if (inStock == null) {
                System.out.println("\nWe don't sell " + name);
                return;
            } else if (stockList.unReserveStock(name, quantity) != 0) {
                basket.removeFromBasket(inStock, quantity);
            }
        }
    }

    private static void checkOut(Basket basket) {
        for (Map.Entry<StockItem, Integer> inBasket : basket.Item().entrySet()) {
            if (stockList.sellStock(inBasket.getKey()) == 0) {
                System.out.println(inBasket.getKey().getName() + " doesn't exist in the list");
            }
        }
        basket.clearBasket();
    }


    public static void createStockList() {
        StockItem item = new StockItem("bread", 1, 10);
        stockList.addItemInStock(item);

        item = new StockItem("cake", 2, 10);
        stockList.addItemInStock(item);

        item = new StockItem("car", 12, 10);
        stockList.addItemInStock(item);

        item = new StockItem("chair", 62, 10);
        stockList.addItemInStock(item);

        item = new StockItem("cup", 1, 10);
        stockList.addItemInStock(item);

        item = new StockItem("door", 72, 10);
        stockList.addItemInStock(item);

        item = new StockItem("juice", 2, 10);
        stockList.addItemInStock(item);

        item = new StockItem("phone", 96, 10);
        stockList.addItemInStock(item);

        item = new StockItem("towel", 2, 10);
        stockList.addItemInStock(item);

        item = new StockItem("vase", 8, 10);
        stockList.addItemInStock(item);
    }
}
