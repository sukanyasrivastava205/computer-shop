package com.computer.shop;

import com.computer.shop.modal.RetailPrice;
import com.computer.shop.modal.SKU;
import com.computer.shop.discount.DiscountDeal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Checkout {
    private static Map<SKU, Prod> itemInfo;
    private Map<SKU, DiscountDeal> discountDeal;
    public static HashMap<SKU, RetailPrice> priceInfo;
    public Checkout(final Map<SKU, DiscountDeal> discountDeal, Map<SKU, Prod> itemInfo) {
        //when constructor is called the product skus, price and rules are loaded
        this.discountDeal = discountDeal;
        this.itemInfo = itemInfo;
    }

    public static void main(String args[]) {

        Info info = Info.getInstance();
        Checkout co = new Checkout(info.getRules(), info.getItemInfo());
        //instantiating the map here
        priceInfo = new HashMap<SKU, RetailPrice>();
        co.scan(SKU.atv);
        co.scan(SKU.atv);
        co.scan(SKU.atv);
        co.scan(SKU.vga);

        //calculate discount after we have all items scanned
        double finalPrice = co.calculateDiscount(priceInfo);
        System.out.println("Final price to be paid is $" + finalPrice);
    }

    /**
     * @param product
     */
    public void scan(SKU product) {

        if (itemInfo.get(product) != null) {
            //we have the sku now check if map is empty or not
            //if map is not null and sku is same then increase the quantity
            //if map(priceInfo) is null means its a new product so create a new instance of RetailPrice
            RetailPrice rp = priceInfo.get(product);
            rp = rp != null ? rp : new RetailPrice();

            rp.increaseQuantity(1);
            rp.setPrice(itemInfo.get(product).getPrice());

            //saving the product in the map with quantity and per unit price details
            priceInfo.put(product, rp);
        }
        else
            throw new RuntimeException("Product is not available in the shop");
    }

    /**
     * @param priceInfo
     * @return amount
     */
    public double calculateDiscount(Map<SKU, RetailPrice> priceInfo) {
        AtomicReference<Double> amount = new AtomicReference<>(0.0);
        //iterating the priceInfo Map to check discounts applicable on each item and getting discounted price
        priceInfo.forEach((sku, retailPrice) -> {
            amount.updateAndGet(v -> (double) (v + discountDeal.get(sku).getTotalAmount(retailPrice,priceInfo)));
        });
        //return the final amount
        return amount.get();
    }
}