package store.view;

import java.util.HashMap;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    public static HashMap<String, Integer> getOrder(){
        while(true) {
            try {
                String[] inputOrder = readLine().strip().split(",");
                return mapOrder(inputOrder);
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static Boolean askMembership(){
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        String input = readLine();
        validateMembership(input);
        System.out.println(input.equals("Y"));
        return input.equals("Y");
    }

    private static HashMap<String, Integer> mapOrder(String[] inputOrder) throws IllegalArgumentException{
        HashMap<String, Integer> orderMap = new HashMap<>();
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

    private static void validateMembership(String answer){
        if(!answer.matches("^[Y|N]{1}$")){
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
        }
    }
}
