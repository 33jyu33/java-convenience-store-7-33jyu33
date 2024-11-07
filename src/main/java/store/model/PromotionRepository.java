package store.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PromotionRepository {
    private final HashMap<String, Promotion> promotions = new HashMap<>();

    public PromotionRepository(Scanner promotionScanner){
        while(promotionScanner.hasNext()){
            setPromotions(promotionScanner.nextLine());
        }
    }

    public Promotion getPromotion(String name){
        return promotions.get(name);
    }

    private void setPromotions(String promotionContext){
        List<String> promotionInfo = contextToList(promotionContext);
        String promotionName = promotionInfo.removeFirst();
        this.promotions.put(promotionName, new Promotion(promotionInfo));
    }

    private List<String> contextToList(String context) {
        List<String> promotionInfo = Arrays.asList(context.strip().split(","));
        validateInfoSize(promotionInfo);
        return promotionInfo;
    }

    private void validateInfoSize(List<String> promotionInfo) {
        if (promotionInfo.size() != 5) {
            throw new IllegalArgumentException("promotion 등록 형식에 문제 있음");
        }
    }
}
