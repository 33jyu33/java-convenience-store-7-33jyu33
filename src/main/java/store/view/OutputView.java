package store.view;

import store.constant.StoreGuide;
import store.model.Product;
import store.model.Store;

public class OutputView {
    public static void productInformation(Store store){
        System.out.println(StoreGuide.START_STORE.getContext());
        changeLine();
        System.out.println(store.getProductInformation());
    }

    public static void receipt(String products, String promotions, String result){
        System.out.println("\n==============W 편의점================");
        System.out.println("상품명\t\t수량\t금액");
        System.out.println(products);
        System.out.println("=============증\t정===============");
        System.out.println(promotions);
        System.out.println("====================================");
        System.out.println(result);
    }

    public static void changeLine(){
        System.out.println();
    }
}
