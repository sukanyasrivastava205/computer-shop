package com.computer.shop.discount;

import com.computer.shop.modal.RetailPrice;
import com.computer.shop.modal.SKU;
import java.util.Map;

public interface DiscountDeal {

    double getTotalAmount(RetailPrice retailPrice, Map<SKU, RetailPrice> priceInfo);
}
