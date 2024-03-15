package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

/**
 * Unit test for Shopping Basket.
 */
public class ShoppingBasketTest {
    ShoppingBasket shoppingBasket = new ShoppingBasket();
    static Products products = new Products();

    @BeforeAll
    public static void init() {
        Product apple = new Product("Apple", new BigDecimal("0.35"));
        Product banana = new Product("Banana", new BigDecimal("0.20"));
        Product melon = new Product("Melon", new BigDecimal("0.50"), Offer.BUY_ONE_GET_ONE_FREE);
        Product lime = new Product("Lime", new BigDecimal("0.15"), Offer.BUY_TWO_GET_ONE_FREE);
        Product beer = new Product("Beer", new BigDecimal("2.59"), Offer.BUY_THREE_FOR_FIVE_POUNDS);

        products.addProduct(apple);
        products.addProduct(banana);
        products.addProduct(melon);
        products.addProduct(lime);
        products.addProduct(beer);
    }

    @DisplayName("Test NO offers")
    @ParameterizedTest
    @MethodSource("generateArgumentsStreamToTestNoOffer")
    public void testNoOffer(List<String> cart, BigDecimal result) {
        Assertions.assertEquals(result, shoppingBasket.calculateCost(cart, products));
    }

    @DisplayName("Test BUY_ONE_GET_ONE_FREE offer")
    @ParameterizedTest
    @MethodSource("generateArgumentsStreamToTestBuyOneGetOneOffer")
    public void testBuyOneGetOneOffer(List<String> cart, BigDecimal result) {
        Assertions.assertEquals(result, shoppingBasket.calculateCost(cart, products));
    }

    @DisplayName("Test GET_THREE_FOR_TWO offer")
    @ParameterizedTest
    @MethodSource("generateArgumentsStreamToTestGetThreeForTwoOffer")
    public void testGetThreeForTwoOffer(List<String> cart, BigDecimal result) {
        Assertions.assertEquals(result, shoppingBasket.calculateCost(cart, products));
    }

    @DisplayName("Test MULTIPLE offers")
    @ParameterizedTest
    @MethodSource("generateArgumentsStreamToTestMultipleOffers")
    public void testMultipleOffers(List<String> cart, BigDecimal result) {
        Assertions.assertEquals(result, shoppingBasket.calculateCost(cart, products));
    }

    @DisplayName("Test BUY_THREE_FOR_FIVE_POUNDS offers")
    @ParameterizedTest
    @MethodSource("generateArgumentsStreamToTestGetFiveForFivePoundsOffer")
    public void testGetFiveForFivePoundsOffer(List<String> cart, BigDecimal result) {
        Assertions.assertEquals(result, shoppingBasket.calculateCost(cart, products));
    }
    private static Stream<Arguments> generateArgumentsStreamToTestGetFiveForFivePoundsOffer() {
        List<Arguments> listOfArgument = new LinkedList<>();
        listOfArgument.add(Arguments.of(ShoppingBasketTestData.getThreeBeers(), new BigDecimal("5.00")));
        listOfArgument.add(Arguments.of(ShoppingBasketTestData.getTwoBeers(), new BigDecimal("5.18")));
        listOfArgument.add(Arguments.of(ShoppingBasketTestData.getFiveBeers(), new BigDecimal("10.18")));
        return listOfArgument.stream();
    }
    private static Stream<Arguments> generateArgumentsStreamToTestNoOffer() {
        List<Arguments> listOfArgument = new LinkedList<>();
        listOfArgument.add(Arguments.of(ShoppingBasketTestData.getEmptyBasket(), new BigDecimal("0.00")));
        listOfArgument.add(Arguments.of(ShoppingBasketTestData.getOfferLessItems(), new BigDecimal("1.10")));
        return listOfArgument.stream();
    }
    private static Stream<Arguments> generateArgumentsStreamToTestBuyOneGetOneOffer() {
        List<Arguments> listOfArgument = new LinkedList<>();
        listOfArgument.add(Arguments.of(ShoppingBasketTestData.getEvenItemsToAvailBuyOneGetOneFreeOffer(), new BigDecimal("1.00")));
        listOfArgument.add(Arguments.of(ShoppingBasketTestData.getOddItemsToAvailBuyOneGetOneFreeOffer(), new BigDecimal("1.00")));
        return listOfArgument.stream();
    }
    private static Stream<Arguments> generateArgumentsStreamToTestGetThreeForTwoOffer() {
        List<Arguments> listOfArgument = new LinkedList<>();
        listOfArgument.add(Arguments.of(ShoppingBasketTestData.getExactThreeItemsToAvailThreeForTwoOffer(), new BigDecimal("0.30")));
        listOfArgument.add(Arguments.of(ShoppingBasketTestData.getInsufficientItemsToAvailThreeForTwoOffer(), new BigDecimal("0.30")));
        listOfArgument.add(Arguments.of(ShoppingBasketTestData.getSufficientItemsToAvailThreeForTwoOffer(), new BigDecimal("0.75")));
        return listOfArgument.stream();
    }
    private static Stream<Arguments> generateArgumentsStreamToTestMultipleOffers() {
        List<Arguments> listOfArgument = new LinkedList<>();
        listOfArgument.add(Arguments.of(ShoppingBasketTestData.getItemsToAvailMultipleOffers(), new BigDecimal("2.35")));
        return listOfArgument.stream();
    }
}
