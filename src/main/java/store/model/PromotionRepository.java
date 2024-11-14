package store.model;

import java.util.*;

public class PromotionRepository {
    private final HashMap<String, Promotion> promotions = new HashMap<>();

    public PromotionRepository(Scanner promotionScanner){
        // 첫 줄 제외
        promotionScanner.nextLine();

        while(promotionScanner.hasNext()){
            setPromotions(promotionScanner.nextLine());
        }
    }

    public Promotion getPromotion(String name){
        return promotions.get(name);
    }

    private void setPromotions(String promotionContext){
        List<String> promotionInfo = new ArrayList<>(contextToList(promotionContext));
        String promotionName = promotionInfo.getFirst();
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
