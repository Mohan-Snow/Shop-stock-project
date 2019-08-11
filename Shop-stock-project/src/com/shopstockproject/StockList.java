package com.shopstockproject;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> stockList;

    public StockList() {
        this.stockList = new LinkedHashMap<>();
    }

    public int addItemInStock(StockItem item) {
        if (item != null) {
            StockItem inStock = stockList.getOrDefault(item.getName(), item);
            if (!inStock.equals(item)) {
                item.adjustStock(inStock.availableQuantity());
            }
            stockList.put(item.getName(), item);
            return item.availableQuantity();
        }
        return 0;
    }

    public int reserveStock(String name, int quantity) {
        StockItem inStock = stockList.get(name);

        if (inStock != null && quantity > 0) {
            return inStock.reserveStock(quantity);
        }
        return 0;
    }

    public int unReserveStock(String name, int quantity) {
        StockItem inStock = stockList.get(name);

        if (inStock != null && quantity > 0) {
            return inStock.unreserveStock(quantity);
        }
        return 0;
    }

    public int sellStock(StockItem item) {
        StockItem inStock = stockList.get(item.getName());

        if (inStock != null) {
            return inStock.finalizeQuantity();
        }
        return 0;
    }

    public StockItem getStockItem(String key) {
        return stockList.get(key);
    }


    public Map<String, StockItem> Item() {
        return Collections.unmodifiableMap(stockList);
    }

    public Map<String, Integer> PriceList() {
        Map<String, Integer> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item : stockList.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    @Override
    public String toString() {
        String line = "\nStock List\n";
        double totalCost = 0.0;

        for (Map.Entry<String, StockItem> item : stockList.entrySet()) {
            StockItem sItem = item.getValue();

            double itemValue = sItem.getPrice() * sItem.availableQuantity();

            line = line + sItem + ". There are " + sItem.availableQuantity() + " in stock\n";
            line = line + "\tValue of items: " + String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;
        }
        return line + "Total Stock Value = " + String.format("%.2f", totalCost);
    }
}
