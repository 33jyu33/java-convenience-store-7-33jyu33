package store.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Product {
    private final String name;
    private final Integer price;
    private final Promotion promotion;

    private Integer quantity;

    public Product(String productContext) {
        List<String> productInfo = contextToList(productContext);
        validateInfoSize(productInfo);
    }

    private List<String> contextToList(String context) {
        return Arrays.asList(context.strip().split(","));
    }

    private void validateInfoSize(List<String> productInfo) {
        if (productInfo.size() != 4) {
            throw new IllegalArgumentException("product 입력 형식에 문제 있음");
        }
    }


}