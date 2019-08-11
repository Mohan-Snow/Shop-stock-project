package com.shopstockproject;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private Map<StockItem, Integer> basket;

    public Basket(String name) {
        this.name = name;
        this.basket = new TreeMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        if (item != null && quantity > 0) {
            int inBasket = basket.getOrDefault(item, 0);
            basket.put(item, inBasket + quantity);
            return quantity;
        }
        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity) {
        if (item != null && quantity > 0) {
            int inBasket = basket.getOrDefault(item, 0);
            int dif = inBasket - quantity;

            if (dif > 0) {
                basket.put(item, dif);
                return quantity;
            } else if (dif == 0) {
                basket.remove(item);
                return quantity;
            }
        }
        return 0;
    }

    public void clearBasket() {
        basket.clear();
    }


    public String getName() {
        return name;
    }

    public Map<StockItem, Integer> Item() {
        return Collections.unmodifiableMap(basket);
    }

    public int getQuantityInBasket(StockItem basketKey) {
        return basket.get(basketKey);
    }

    @Override
    public String toString() {
        String line = "\nShopping basket " + name + " contains " + basket.size() +
                (basket.size() == 1 ? " item" : " items") + "\n";
        double totalCost = 0.0;

        for (Map.Entry<StockItem, Integer> item : basket.entrySet()) {
            line = line + item.getKey() + " (" + item.getValue() + " reserved)\n";

            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return line + " Total Cost " + String.format("%.2f", totalCost);
    }
}
