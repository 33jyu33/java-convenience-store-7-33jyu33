package store.model;

import java.util.*;

public class Store {
    private final HashMap<String, List<Product>> stock = new HashMap<>();

    public Store(Scanner productScanner, PromotionRepository promotionRepository) {
        // 첫 줄 제외
        productScanner.nextLine();
        while (productScanner.hasNextLine()) {
            setStock(productScanner.nextLine(), promotionRepository);
        }
    }

    private void setStock(String productInput, PromotionRepository promotionRepository) {
        List<Product> productType = new ArrayList<>();
        Product product = new Product(productInput, promotionRepository);
        if (stock.containsKey(product.getName())) {
            productType.addAll(stock.get(product.getName()));
        }
        productType.add(product);
        stock.put(product.getName(), productType);
    }

    public String getProductInformation() {
        // 재고로 등록된 모든 상품의 정보를 한 문자열로 합친다.
        StringBuilder productInformation = new StringBuilder();
        for (List<Product> products : stock.values()) {
            for (Product product : products)
                productInformation.append(product.getProductInformation());
        }
        return productInformation.toString();
    }
}
