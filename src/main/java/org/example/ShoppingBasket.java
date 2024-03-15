package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShoppingBasket {
    public BigDecimal calculateCost(List<String> items, Products products) {
        BigDecimal basketTotal = new BigDecimal("0.00");
        Map<String, Long> productQuantityMap = items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (Map.Entry item : productQuantityMap.entrySet()) {
            Product product = products.getProductByName(item.getKey().toString());
            long productQuantity = (Long) item.getValue();
            basketTotal = basketTotal.add(getProductPriceAfterDiscount(product, productQuantity));
        }
        return basketTotal;
    }

    private BigDecimal getProductPriceAfterDiscount(Product product, Long quantity) {
        return getProductPriceBeforeDiscount(product, quantity).subtract(getProductDiscount(product, quantity));
    }

    private BigDecimal getProductPriceBeforeDiscount(Product product, Long quantity) {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    private BigDecimal getProductDiscount(Product product, Long quantity) {
        long freeItems = 0;
        if (product.getOffer() != null) {
            switch (product.getOffer()) {
                case BUY_ONE_GET_ONE_FREE:
                    freeItems = quantity / 2;
                    break;
                case BUY_TWO_GET_ONE_FREE:
                    freeItems = quantity / 3;
                    break;
                case BUY_THREE_FOR_FIVE_POUNDS:
                    long quotient = quantity / 3;
                    BigDecimal discountPerThreeItems = product.getPrice().multiply(BigDecimal.valueOf(3)).subtract(BigDecimal.valueOf(5));
                    return discountPerThreeItems.multiply(BigDecimal.valueOf(quotient));
           }
        }
        return product.getPrice().multiply(BigDecimal.valueOf(freeItems));
    }
}
