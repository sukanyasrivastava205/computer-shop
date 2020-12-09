package com.computer.shop;

import com.computer.shop.modal.SKU;
import com.computer.shop.discount.*;

import java.util.HashMap;
import java.util.Map;

public class Info {
    private static Info instance = new Info();

    private  Info(){
       //cant be instantiated
    }
    public static Info getInstance(){
        return instance;
    }

    private Map<SKU, DiscountDeal> discountDeal = new HashMap<>();
    Map<SKU,Prod> items = new HashMap<SKU,Prod>();

    public Map<SKU, DiscountDeal> getRules(){
        discountDeal.put(SKU.atv, new AtvDiscountDeal());
        discountDeal.put(SKU.ipd, new IpadDiscountDeal());
        discountDeal.put(SKU.mbp, new MbpDiscountDeal());
        discountDeal.put(SKU.vga, new VgaDiscountDeal());
        return discountDeal;
    }
    public Map<SKU,Prod> getItemInfo(){
        items.put(SKU.ipd, new Prod(SKU.ipd,"Super iPad", 549.99));
        items.put(SKU.mbp, new Prod(SKU.mbp,"MacBook Pro",1399.99));
        items.put(SKU.atv, new Prod(SKU.atv,"Apple TV", 109.50));
        items.put(SKU.vga, new Prod(SKU.vga,"VGA adapter",30.00));
        return items;
    }

}
