package store.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {
    private final List<Product> stock = new ArrayList<>();

    public Store(Scanner productScanner, PromotionRepository promotionRepository){
        // 첫 줄 제외
        productScanner.nextLine();
        while (productScanner.hasNextLine()) {
            setStock(productScanner.nextLine(), promotionRepository);
        }
    }

    private void setStock(String product, PromotionRepository promotionRepository){
        stock.add(new Product(product, promotionRepository));
    }
}
