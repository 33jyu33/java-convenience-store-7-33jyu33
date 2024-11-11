package store.constant;

public enum CompareContext {
    NULL_STRING(""),
    SQUARE_BRACKET("[\\[\\]]"),
    ORDER("^(\\[)[^\\[^\\]]+\\-[0-9]+(\\])$"),
    ORDER_SEPARATOR("-"),
    YES_OR_NO("^[Y|N]{1}$"),
    YES("Y"),
    NO("N"),
    COMMA(","),
    NO_STOCK("재고 없음")
    ;

    final private String context;

    CompareContext(String context){
        this.context = context;
    }

    public String getContext(){
        return context;
    }
}
