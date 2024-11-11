package store.view;

import store.constant.CompareContext;
import store.constant.ErrorMessage;
import store.constant.StoreGuide;

import java.util.LinkedHashMap;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    public static LinkedHashMap<String, Integer> getOrder(){
        while(true) {
            try {
                System.out.println(StoreGuide.GET_ORDER.getContext());
                String[] inputOrder = readLine().strip().split(CompareContext.COMMA.getContext());
                return mapOrder(inputOrder);
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean checkAdditionalFreeProduct(String name, Integer count){
        System.out.printf(StoreGuide.ASK_FREE.getContext(), name, count);
        String input = readLine();
        validateYN(input);
        return input.equals(CompareContext.YES.getContext());
    }

    public static void checkNotApplyPromotion(String name, Integer quantity){
        System.out.printf(StoreGuide.ASK_NOT_APPLY_PROMOTION.getContext(), name, quantity);
        String input = readLine();
        validateYN(input);
        if (input.equals(CompareContext.NO.getContext())){
            throw new IllegalArgumentException(StoreGuide.CANCEL_ORDER.getContext());
        }
    }

    public static Boolean askMembership(){
        System.out.println(StoreGuide.ASK_MEMBERSHIP.getContext());
        String input = readLine();
        validateYN(input);
        return input.equals(CompareContext.YES.getContext());
    }

    public static Boolean askAdditionalPurchase(){
        System.out.println(StoreGuide.ASK_MORE.getContext());
        String input = readLine();
        validateYN(input);
        return input.equals(CompareContext.YES.getContext());
    }

    private static LinkedHashMap<String, Integer> mapOrder(String[] inputOrder) throws IllegalArgumentException{
        LinkedHashMap<String, Integer> orderMap = new LinkedHashMap<>();
        for(String input : inputOrder){
            validateOrder(input);
            String[] order = splitOrder(input);
            orderMap.put(order[0], Integer.parseInt(order[1]));
        }
        return orderMap;
    }

    private static String[] splitOrder(String input){
        input = input.replaceAll(CompareContext.SQUARE_BRACKET.getContext(), CompareContext.NULL_STRING.getContext());
        return input.split(CompareContext.ORDER_SEPARATOR.getContext());
    }

    private static void validateOrder(String order) throws IllegalArgumentException{
        if(!order.matches(CompareContext.ORDER.getContext())){
            throw new IllegalArgumentException(ErrorMessage.INPUT_RULE.getMessage());
        }
    }

    private static void validateYN(String answer){
        if(!answer.matches(CompareContext.YES_OR_NO.getContext())){
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }
}
