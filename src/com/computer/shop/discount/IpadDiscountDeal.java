package com.computer.shop.discount;

import com.computer.shop.modal.RetailPrice;
import com.computer.shop.modal.SKU;

import java.util.Map;

public class IpadDiscountDeal implements DiscountDeal {

    int minimumQuantity = 4;

    @Override
    public double getTotalAmount(RetailPrice retailPrice, Map<SKU, RetailPrice> priceInfo){

        if(retailPrice.getQuantity()>minimumQuantity){
            System.out.println("ipad discount applicable");
            return 499.99*retailPrice.getQuantity();
        }
        else {
            return retailPrice.getQuantity() * retailPrice.getPrice();
        }
    }
}
