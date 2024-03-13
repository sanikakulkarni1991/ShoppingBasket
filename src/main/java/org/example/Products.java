package org.example;

import java.util.ArrayList;
import java.util.List;

public class Products {
    List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product getProductByName(String productName) {
        return products.stream().filter(x -> x.getName().equals(productName)).findFirst().orElse(null);
    }
}
