package com.test.shop;

import com.computer.shop.Checkout;
import com.computer.shop.Info;
import com.computer.shop.Prod;
import com.computer.shop.modal.RetailPrice;
import com.computer.shop.modal.SKU;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountDealsTest {
    private static Map<SKU, Prod> itemInfo;
    public static HashMap<SKU, RetailPrice> priceInfo;
    Info info = Info.getInstance();

    /**
     * given quantity and price per unit.
     * check if discount deals are applied correctly and discounted price is received correctly
     * testing 3 for 2 offer here
     * SKUs Scanned: atv, atv, atv, vga Total expected: $249.00
     */
    @Test
    public void testScenario1() {

        Checkout co = new Checkout(info.getRules(), info.getItemInfo());
        priceInfo = new HashMap<SKU, RetailPrice>();
        RetailPrice rp1 = new RetailPrice();
        rp1.setQuantity(3);
        rp1.setPrice(109.50); //price per unit
        RetailPrice rp2 = new RetailPrice();
        rp2.setQuantity(1);
        rp2.setPrice(30.0); //price per unit
        priceInfo.put(SKU.atv,rp1);
        priceInfo.put(SKU.vga,rp2);

        Double amount = co.calculateDiscount(priceInfo);
        assertEquals(249,amount);
    }

    /**
     * offer- one vga free on each mbp. But if customer buys additional vga, it must be paid for
     * checking this scenario for vga and mbp
     * SKUs Scanned: mbp,mbp,vga,vga,vga Total expected: $2829.98
     */
    @Test
    public void testScenario2() {
        Checkout co = new Checkout(info.getRules(), info.getItemInfo());
        priceInfo = new HashMap<SKU, RetailPrice>();
        RetailPrice rp1 = new RetailPrice();
        rp1.setQuantity(2);
        rp1.setPrice(1399.99); //price per unit
        RetailPrice rp2 = new RetailPrice();
        rp2.setQuantity(3);
        rp2.setPrice(30); //price per unit
        priceInfo.put(SKU.mbp,rp1);
        priceInfo.put(SKU.vga,rp2);

        Double amount = co.calculateDiscount(priceInfo);
        assertEquals(2829.98,amount);
    }

    /**
     * testing bulk discount here.
     * if customer buys more than 4 ipads then price per unit is reduced to 499.99
     * check if correct amount received after discount
     * SKUs Scanned: atv, ipd, ipd, atv, ipd, ipd, ipd Total expected: $2718.95
     */
    @Test
    public void testScenario3() {

        Checkout co = new Checkout(info.getRules(), info.getItemInfo());
        priceInfo = new HashMap<SKU, RetailPrice>();
        RetailPrice rp1 = new RetailPrice();
        rp1.setQuantity(5);
        rp1.setPrice(549.99);
        RetailPrice rp2 = new RetailPrice();
        rp2.setQuantity(2);
        rp2.setPrice(109.50);
        priceInfo.put(SKU.ipd,rp1);
        priceInfo.put(SKU.atv,rp2);

        Double amount = co.calculateDiscount(priceInfo);
        assertEquals(2718.95,amount);
    }

    /**
     * testing discount- free vga on each mbp
     * SKUs Scanned: mbp, vga, ipd Total expected: $1949.98
     */
    @Test
    public void testScenario4() {

        Checkout co = new Checkout(info.getRules(), info.getItemInfo());
        priceInfo = new HashMap<SKU, RetailPrice>();
        RetailPrice rp1 = new RetailPrice();
        rp1.setQuantity(1);
        rp1.setPrice(1399.99);
        RetailPrice rp2 = new RetailPrice();
        rp2.setQuantity(1);
        rp2.setPrice(30);
        RetailPrice rp3 = new RetailPrice();
        rp3.setQuantity(1);
        rp3.setPrice(549.99);
        priceInfo.put(SKU.mbp,rp1);
        priceInfo.put(SKU.vga,rp2);
        priceInfo.put(SKU.ipd,rp3);

        Double amount = co.calculateDiscount(priceInfo);
        assertEquals(1949.98,amount);
    }
}
