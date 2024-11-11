package store.model;

import java.util.*;

public class Store {
    private final Map<String, Product> stock = new LinkedHashMap<>();

    public Store(Scanner productScanner, PromotionRepository promotionRepository) {
        // 첫 줄 제외
        productScanner.nextLine();
        while (productScanner.hasNextLine()) {
            setStock(productScanner.nextLine(), promotionRepository);
        }
    }

    private void setStock(String productInput, PromotionRepository promotionRepository) {
        // 콜라,1000,3,반짝할인
        List<String> productInfo = contextToList(productInput);

        if (stock.containsKey(productInfo.getFirst())) {
            stock.get(productInfo.getFirst()).update(productInfo.get(2), promotionRepository.getPromotion(productInfo.get(2)));
            return;
        }
        Product product = new Product(productInfo, promotionRepository);
        stock.put(product.getName(), product);
    }

    public String getProductInformation() {
        // 재고로 등록된 모든 상품의 정보를 한 문자열로 합친다.
        StringBuilder productInformation = new StringBuilder();
        for (Product product : stock.values()) {
            productInformation.append(product.getProductInformation());
        }
        return productInformation.toString();
    }

    // 프로모션 부족 수량 확인, 재고 수량 확인
    public Product getSaleProduct(String name, Integer count) throws IllegalArgumentException {
        Product saleProduct = stock.get(name);
        saleProduct.validateOrderQuantity(count);
        return saleProduct;
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
}
