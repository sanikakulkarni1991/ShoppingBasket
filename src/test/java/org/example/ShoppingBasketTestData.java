package org.example;

import java.util.List;

public class ShoppingBasketTestData {
    public static List<String> getEmptyBasket() {
        return List.of();
    }

    public static List<String> getOfferLessItems() {
        return List.of("Apple", "Apple", "Banana", "Banana");
    }

    public static List<String> getEvenItemsToAvailBuyOneGetOneFreeOffer() {
        return List.of("Melon", "Melon", "Melon", "Melon");
    }

    public static List<String> getOddItemsToAvailBuyOneGetOneFreeOffer() {
        return List.of("Melon", "Melon", "Melon");
    }

    public static List<String> getExactThreeItemsToAvailThreeForTwoOffer() {
        return List.of("Lime", "Lime", "Lime");
    }

    public static List<String> getInsufficientItemsToAvailThreeForTwoOffer() {
        return List.of("Lime", "Lime");
    }

    public static List<String> getSufficientItemsToAvailThreeForTwoOffer() {
        return List.of("Lime", "Lime", "Lime", "Lime", "Lime", "Lime", "Lime");
    }

    public static List<String> getItemsToAvailMultipleOffers() {
        return List.of("Apple", "Banana", "Lime", "Lime", "Melon", "Melon", "Melon", "Melon", "Melon");
    }

    public static List<String> getThreeBeers(){
        return List.of("Beer", "Beer", "Beer");
    }
    public static List<String> getTwoBeers(){
        return List.of("Beer", "Beer");
    }
    public static List<String> getFiveBeers(){
        return List.of("Beer", "Beer","Beer", "Beer","Beer");
    }
}
