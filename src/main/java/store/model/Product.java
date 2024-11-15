package store.model;

import store.constant.CompareContext;
import store.constant.ErrorMessage;
import store.constant.StoreGuide;

import java.util.List;
import java.util.Objects;

public class Product {
    private final String name;
    private final Integer price;

    private Promotion promotion;
    private Integer quantity;
    private Integer promotionalQuantity;

    public Product(List<String> productInfo, PromotionRepository promotionRepository) {
        final int INDEX_NAME = 0;
        final int INDEX_PRICE = 1;
        final int INDEX_QUANTITY = 2;
        final int INDEX_PROMOTION = 3;

        this.name = productInfo.get(INDEX_NAME);
        this.price = getPrice(productInfo.get(INDEX_PRICE));
        this.quantity = getPrice(productInfo.get(INDEX_QUANTITY));
        this.promotion = promotionRepository.getPromotion(productInfo.get(INDEX_PROMOTION));
        this.promotionalQuantity = 0;
        if (!Objects.equals(productInfo.get(INDEX_PROMOTION), "null")) {
            this.promotionalQuantity = getPrice(productInfo.get(INDEX_QUANTITY));
        }
    }

    public void update(String quantity, Promotion promotion) {
        this.quantity += getPrice(quantity);
        if (promotion != null) {
            this.promotion = promotion;
            this.promotionalQuantity = getPrice(quantity);
        }
    }

    public void updatePromotionalQuantity(Integer promotionalQuantity){
        this.quantity = promotionalQuantity;
        this.promotionalQuantity = promotionalQuantity;
    }

    public Product(String name, Integer price, Integer quantity, Promotion promotion, Integer promotionalQuantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
        this.promotionalQuantity = promotionalQuantity;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getAdditionalFreeProductCount(Integer quantity) {
        if (promotion != null){
            return promotion.getAdditionalFreeProductCount(quantity);
        }
        return 0;
    }

    public boolean validateAdditionalQuantity(Integer quantity) {
        return quantity <= this.promotionalQuantity;
    }

    public void validateOrderQuantity(Integer quantity) throws IllegalArgumentException {
        if (this.quantity < quantity) {
            throw new IllegalArgumentException(ErrorMessage.OVER_QUANTITY.getMessage());
        }
    }

    // 프로모션 적용 가능한 수량
    public Integer availableProductQuantity(Integer quantity) {
        if (promotion == null) return null;
        return promotion.getDiscountedProductCount(getPromotionalProductQuantity(quantity));
    }

    public void checkPromotionPeriod() {
        if (promotion == null || !promotion.inPeriod()) {
            promotionalQuantity = 0;
        }
    }

    // 주문 받은 수량 중 프로모션 적용할 상품의 개수
    private Integer getPromotionalProductQuantity(Integer quantity) {
        if (promotionalQuantity < quantity) return promotionalQuantity;
        return quantity;
    }

    public Integer getPromotionDiscount() {
        if (promotion == null) return 0;
        return promotion.getFreeProductCount(promotionalQuantity) * price;
    }

    public Integer getMembershipDiscount() {
        if (promotion == null) return quantity * price;
        return (quantity - promotion.getDiscountedProductCount(promotionalQuantity)) * price;
    }

    public String getProductReceipt() {
        return String.format("%s\t\t%,d\t%,d", name, quantity, quantity * price);
    }

    public String getPromotionReceipt() {
        if (promotion == null) return null;
        Integer freeQuantity = promotion.getFreeProductCount(promotionalQuantity);
        if (freeQuantity == 0) return null;
        return String.format("%s\t\t%,d", name, freeQuantity);
    }

    public String getProductInformation() {
        if (promotion != null) {
            return getPromotionalProductInformation()+getGeneralProductInformation();
        }
        return getGeneralProductInformation();
    }

    private String getGeneralProductInformation(){
        String quantityContext = CompareContext.NO_STOCK.getContext();
        if (0 < quantity - promotionalQuantity){
            quantityContext = Integer.toString(quantity - promotionalQuantity)+"개";
        }
        return String.format(StoreGuide.PRODUCT.getContext(), name, price, quantityContext, CompareContext.NULL_STRING.getContext());
    }

    private String getPromotionalProductInformation() {
        String quantityContext = CompareContext.NO_STOCK.getContext();
        if (0 < promotionalQuantity){
            quantityContext = Integer.toString(promotionalQuantity)+"개";
        }
        return String.format(StoreGuide.PRODUCT.getContext(), name, price, quantityContext, promotion.getName());
    }

    public void sold(Integer count) {
        if (promotion != null) {
            promotionalQuantity -= availableProductQuantity(count);
        }
        quantity -= count;
    }

    public Product getSoldProduct(Integer count) {
        Integer promotionProductQuantity = availableProductQuantity(count);
        if (promotion == null) promotionProductQuantity = 0;
        return new Product(name, price, count, promotion, promotionProductQuantity);
    }

    private Integer getPrice(String priceContext) {
        validatePrice(priceContext);
        return Integer.parseInt(priceContext);
    }

    private void validatePrice(String priceContext) {
        if (!priceContext.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("product 재고 등록 가격은 숫자로만 구성해야 합니다");
        }
    }
}