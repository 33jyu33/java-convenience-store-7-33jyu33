package store.model;

import store.constant.StoreGuide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Product {
    private final String name;
    private final Integer price;
    private final Promotion promotion;

    private Integer quantity;

    public Product(String productContext, PromotionRepository promotionRepository) {
        final int INDEX_NAME = 0;
        final int INDEX_PRICE = 1;
        final int INDEX_QUANTITY = 2;
        final int INDEX_PROMOTION = 3;

        List<String> productInfo = contextToList(productContext);
        this.name = productInfo.get(INDEX_NAME);
        this.price = getPrice(productInfo.get(INDEX_PRICE));
        this.quantity = getPrice(productInfo.get(INDEX_QUANTITY));
        this.promotion = promotionRepository.getPromotion(productInfo.get(INDEX_PROMOTION));
    }

    public Product(String name, Integer price, Integer quantity, Promotion promotion){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public String getName(){
        return name;
    }

    public String getProductInformation(){
        String promotionName = "";
        if(promotion != null){
            promotionName = promotion.getName();
        }
        return String.format(StoreGuide.PRODUCT.getContext(), name, price, quantity,promotionName);
    }

    public Integer getAvailableQuantity(Integer count){
        if(count <= quantity) return count;
        return quantity;
    }

    public void sold(Integer count){
        quantity -= count;
    }

    public Product getSoldProduct(Integer count){
        return new Product(name, price, count, promotion);
    }

    private List<String> contextToList(String context) {
        List<String> productInfo = Arrays.asList(context.strip().split(","));
        validateInfoSize(productInfo);
        return productInfo;
    }

    private void validateInfoSize(List<String> productInfo) {
        if (productInfo.size() != 4) {
            throw new IllegalArgumentException("product 재고 등록 형식에 문제 있음");
        }
    }

    private Integer getPrice(String priceContext){
        validatePrice(priceContext);
        return Integer.parseInt(priceContext);
    }

    private void validatePrice(String priceContext){
        if (!priceContext.matches("^[0-9]+$")){
            throw new IllegalArgumentException("product 재고 등록 가격은 숫자로만 구성해야 합니다");
        }
    }
}