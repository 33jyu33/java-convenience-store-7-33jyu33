package store.constant;

public enum StoreGuide {
    START_STORE("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다."),
    PRODUCT("- %s %,d원 %,d개 %s\n"),
    GET_ORDER("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    ASK_MEMBERSHIP("멤버십 할인을 받으시겠습니까? (Y/N)"),
    ASK_MORE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),
    ASK_NOT_APPLY_PROMOTION("현재 %s %,d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n"),
    ASK_FREE("현재 %s은(는) %,d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n");


    final private String context;

    StoreGuide(String context){
        this.context = context;
    }

    public String getContext(){
        return context;
    }
}
