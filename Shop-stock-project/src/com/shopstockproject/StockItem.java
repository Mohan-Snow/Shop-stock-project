package com.shopstockproject;

public class StockItem implements Comparable<StockItem> {
    private String name;
    private int price;
    private int quantityInStock;
    private int reserved = 0;

    public StockItem(String name, int price, int quantityInStock) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public int adjustStock(int quantity) {
        int newNum = quantityInStock + quantity;
        if (newNum > 0) {
            this.quantityInStock = newNum;
            return quantity;
        }
        return 0;
    }

    public int reserveStock(int quantity) {
        if (quantity <= availableQuantity()) {
            reserved += quantity;
            return quantity;
        }
        return 0;
    }

    public int unreserveStock(int quantity) {
        if (quantity <= reserved) {
            reserved -= quantity;
            return quantity;
        }
        return 0;
    }

    public int finalizeQuantity() {
        if (this.availableQuantity() >= 0) {
            quantityInStock -= reserved;
            reserved = 0;
            return 1;
        }
        return 0;
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int availableQuantity() {
        return quantityInStock - reserved;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem o) {
        if (this == o) {
            return 0;
        }

        if (o != null) {
            return this.name.compareTo(o.getName());
        }

        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " : price " + this.price;
    }
}
