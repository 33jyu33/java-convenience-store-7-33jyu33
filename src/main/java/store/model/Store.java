package store.model;

import java.util.*;

public class Store {
    private final Map<String, List<Product>> stock = new LinkedHashMap<>();

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

    // 프로모션 부족 수량 확인, 재고 수량 확인
    public List<Product> getSaleProduct(String name, Integer count) throws IllegalArgumentException {
        Integer availableSale = 0;
        Integer orderCount = count;
        List<Product> saleProduct = new ArrayList<>();

        for (Product product : stock.get(name)) {
            saleProduct.add(product);
            availableSale += product.getAvailableQuantity(orderCount);
            if (availableSale.equals(count)) break;
            orderCount -= availableSale;
        }
        isAvailableSale(availableSale, count);
        return saleProduct;
    }

    private void isAvailableSale(Integer productCount, Integer orderCount){
        if (!productCount.equals(orderCount)) {
            throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
    }
}
