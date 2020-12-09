package com.computer.shop.discount;

import com.computer.shop.modal.RetailPrice;
import com.computer.shop.modal.SKU;
import java.util.Map;

public class VgaDiscountDeal implements DiscountDeal {

    @Override
    public double getTotalAmount(RetailPrice retailPrice, Map<SKU, RetailPrice> priceInfo){
        double price = 0.0;

        /* if there is no macbook in the map means vga is purchased separately
        * so it must be billed.*/
        if(priceInfo.get(SKU.mbp)==null){
            price = retailPrice.getPrice() * retailPrice.getQuantity();
        }

        /*if mbp ang vga are both present in the map then check if number of vga is
        more than discounted item. If vga is more than mbp then it must be billed.*/
        if(priceInfo.get(SKU.mbp)!=null && priceInfo.get(SKU.vga)!=null){
            price = checkForFreeItems(priceInfo);
        }
        return price;
    }

    /**
     * if quantity of vga and mbp is same then don't bill vga as it is discounted
     * @param priceInfo
     * @return amount
     */
    private double checkForFreeItems(Map<SKU, RetailPrice> priceInfo){
        double amount=0.0;
        if(priceInfo.get(SKU.mbp).getQuantity()==priceInfo.get(SKU.vga).getQuantity()){
            amount= 0;
        }
        else{
            //if number of vga scanned is more than mpb then it must be paid for
            int quantity = priceInfo.get(SKU.vga).getQuantity()-priceInfo.get(SKU.mbp).getQuantity();
            amount = quantity * priceInfo.get(SKU.vga).getPrice();
        }
        return amount;
    }

}
