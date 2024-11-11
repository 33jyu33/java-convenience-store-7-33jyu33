package store.view;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    public static LinkedHashMap<String, Integer> getOrder(){
        while(true) {
            try {
                String[] inputOrder = readLine().strip().split(",");
                return mapOrder(inputOrder);
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void checkNotApplyPromotion(String name, Integer quantity){
        System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n", name, quantity);
        String input = readLine();
        validateYN(input);
        if (input.equals("N")){
            throw new IllegalArgumentException("주문을 취소했습니다.");
        };
    }

    public static Boolean askMembership(){
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        String input = readLine();
        validateYN(input);
        return input.equals("Y");
    }

    public static Boolean askAdditionalPurchase(){
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        String input = readLine();
        validateYN(input);
        return input.equals("Y");
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
        input = input.replaceAll("[\\[\\]]", "");
        return input.split("-");
    }

    private static void validateOrder(String order) throws IllegalArgumentException{
        if(!order.matches("^(\\[)[^\\[^\\]]+\\-[0-9]+(\\])$")){
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    private static void validateYN(String answer){
        if(!answer.matches("^[Y|N]{1}$")){
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
        }
    }
}
