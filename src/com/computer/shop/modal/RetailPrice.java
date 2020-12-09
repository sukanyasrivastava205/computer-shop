package com.computer.shop.modal;

import java.util.Objects;

public class RetailPrice {

    int quantity;
    double price;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetailPrice that = (RetailPrice) o;
        return quantity == that.quantity &&
                Double.compare(that.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, price);
    }

    @Override
    public String toString() {
        return "RetailPrice{" +
                "quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
