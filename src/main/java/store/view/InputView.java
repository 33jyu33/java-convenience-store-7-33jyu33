package store.view;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
}
