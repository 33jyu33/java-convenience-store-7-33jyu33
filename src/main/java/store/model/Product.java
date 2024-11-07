package store.model;

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
        final int INDEX_PROMOTION = 2;

        List<String> productInfo = contextToList(productContext);
        this.name = productInfo.get(INDEX_NAME);
        this.price = getPrice(productInfo.get(INDEX_PRICE));
        this.promotion = promotionRepository.getPromotion(productInfo.get(INDEX_PROMOTION));
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