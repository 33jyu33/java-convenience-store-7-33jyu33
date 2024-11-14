package store.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static camp.nextstep.edu.missionutils.DateTimes.now;

public class Promotion {
    private final String name;
    private final Integer buy;
    private final Integer get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(List<String> promotionInfo) {
        final Integer INDEX_NAME = 0;
        final Integer INDEX_BUY = 1;
        final Integer INDEX_GET = 2;
        final Integer INDEX_START_DATE = 3;
        final Integer INDEX_END_DATE = 4;

        this.name = promotionInfo.get(INDEX_NAME);
        this.buy = getNumber(promotionInfo.get(INDEX_BUY));
        this.get = getNumber(promotionInfo.get(INDEX_GET));
        this.startDate = LocalDate.parse(promotionInfo.get(INDEX_START_DATE), DateTimeFormatter.ISO_DATE);
        this.endDate = LocalDate.parse(promotionInfo.get(INDEX_END_DATE), DateTimeFormatter.ISO_DATE);
        validateDate();
    }

    public String getName() {
        return this.name;
    }

    public Integer getFreeProductCount(Integer count) {
        return count / (get + buy) * get;
    }

    public Integer getDiscountedProductCount(Integer count) {
        return (count / (get + buy)) * (get + buy);
    }

    public Integer getAdditionalFreeProductCount(Integer count){
        if(count - getDiscountedProductCount(count) == buy){
            return get;
        }
        return 0;
    }

    public Boolean inPeriod() {
        LocalDate now = now().toLocalDate();
        return ((!now.isBefore(startDate)) && (!now.isAfter(endDate)));
    }

    private Integer getNumber(String context) {
        validateOnlyNumber(context);
        return Integer.parseInt(context);
    }

    private void validateOnlyNumber(String context) {
        if (!context.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("promotion get, buy는 숫자로만 구성해야 합니다");
        }
    }

    private void validateDate() {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("promotion 마감일이 시작일보다 빠를 수 없습니다.");
        }
    }
}
