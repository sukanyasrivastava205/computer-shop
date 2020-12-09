package com.computer.shop.discount;

import com.computer.shop.modal.RetailPrice;
import com.computer.shop.modal.SKU;

import java.util.Map;

public class MbpDiscountDeal implements DiscountDeal {

    @Override
    public double getTotalAmount(RetailPrice retailPrice, Map<SKU, RetailPrice> priceInfo) {
        return retailPrice.getQuantity() * retailPrice.getPrice();
    }
}
