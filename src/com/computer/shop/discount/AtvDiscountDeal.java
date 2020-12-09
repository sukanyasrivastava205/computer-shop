package com.computer.shop.discount;

import com.computer.shop.modal.RetailPrice;
import com.computer.shop.modal.SKU;

import java.util.Map;

public class AtvDiscountDeal implements DiscountDeal {

    int minimumQuantity = 3;
    @Override
    public double getTotalAmount(RetailPrice retailPrice, Map<SKU, RetailPrice> priceInfo){

        double price = retailPrice.getPrice();

        if(retailPrice.getQuantity()>=minimumQuantity){
            System.out.println("atv discount applicable");
            int quantity = retailPrice.getQuantity() - retailPrice.getQuantity()/3;
            return price*quantity;
        }
        else {
            return price * retailPrice.getQuantity();
        }
    }
}
