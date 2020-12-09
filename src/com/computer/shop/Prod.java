package com.computer.shop;

import com.computer.shop.modal.SKU;

public class Prod {

    double price;
    SKU sku;
    String name;

    public double getPrice() {
        return price;
    }

    public SKU getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Prod(final SKU sku, final String name, final double price) {
        this.price = price;
        this.sku = sku;
        this.name = name;
    }

}
