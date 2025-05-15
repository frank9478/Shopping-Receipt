package com.shoppingreceipt;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingreceipt.model.Goods;
import com.shoppingreceipt.model.ShoppingInput;

public class ShoppingReceipt {

    public static void main(String[] args) throws IOException {
    
    	 // 用 Jackson 讀取 JSON 檔案並自動轉成 Java 物件
        ObjectMapper mapper = new ObjectMapper();
        ShoppingInput input = mapper.readValue(Paths.get("input1.json").toFile(), ShoppingInput.class);

        String location = input.getLocation();
        List<Goods> goods = input.getGoods();
        // 呼叫收據方法
        printReceipt(goods, location);
    }
    
    
    public static void printReceipt(List<Goods> goods, String location) {
        double subtotal = 0.0;
        double totalTax = 0.0;

        System.out.println("Items Purchased:");
        
        // 計算 商品價格*數量
        // 判斷 物品種類
        // 再分地區各種類商品課徵銷售稅
        // 最後將金額無條件進位到最到下個0.05倍數
        // for迴圈重複加總商品
        for (Goods good : goods) {
        	
            double itemTotal = good.getPrice() * good.getQuantity(); 
            String category = getCategory(good.getName());
            double taxRate = getTaxRate(location, category);
            double itemTax = roundUpToNearest005(itemTotal * taxRate);

            subtotal += itemTotal;
            totalTax += itemTax;

            System.out.printf("%-20s x%d  @ %.2f = %.2f\n", good.getName(), good.getQuantity(), good.getPrice(), itemTotal);
        }

        double total = subtotal + totalTax;

        System.out.printf("\nSubtotal: %.2f\n", subtotal);
        System.out.printf("Sales Tax: %.2f\n", totalTax);
        System.out.printf("Total: %.2f\n", total);
    }

    //取得各地區的不同類別所需課徵的交易稅
    public static double getTaxRate(String location, String category) {
        location = location.toUpperCase();
        switch (location) {
            case "CA":
                return category.equals("food") ? 0.0 : 0.0975;
            case "NY":
                return (category.equals("food") || category.equals("clothing")) ? 0.0 : 0.08875;
            default:
                return 0.0;
        }
    }


    // 用map的key去找商品種類表，可回種商品種類後再getTaxRate()取得稅率
    public static String getCategory(String itemName) {
        String lowerName = itemName.toLowerCase();
        for (Map.Entry<String, String> entry : keywordCategoryMap.entrySet()) {
            if (lowerName.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "other";
    }
    
    //商品種類表
     private static final Map<String, String> keywordCategoryMap = Map.of(
    	    "potato chips", "food",
    	    "shirt", "clothing"
    	);
    
    //將價格*20後再無條件進位，最後再除回20 即可得到0.05倍數的值
     public static double roundUpToNearest005(double value) {
        return Math.ceil(value * 20.0) / 20.0;
     }
}

