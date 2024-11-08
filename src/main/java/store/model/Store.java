package store.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {
    private final List<Product> stock = new ArrayList<>();

    public Store(Scanner productScanner, PromotionRepository promotionRepository) {
        // 첫 줄 제외
        productScanner.nextLine();
        while (productScanner.hasNextLine()) {
            setStock(productScanner.nextLine(), promotionRepository);
        }
    }

    private void setStock(String product, PromotionRepository promotionRepository) {
        stock.add(new Product(product, promotionRepository));
    }

    public String getProductInformation() {
        // 재고로 등록된 모든 상품의 정보를 한 문자열로 합친다.
        StringBuilder productInformation = new StringBuilder();
        for (Product product : stock) {
            productInformation.append(product.getProductInformation());
        }
        return productInformation.toString();
    }
}
